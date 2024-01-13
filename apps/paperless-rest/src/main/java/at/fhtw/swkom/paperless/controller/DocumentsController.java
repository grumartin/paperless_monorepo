package at.fhtw.swkom.paperless.controller;

import at.fhtw.swkom.paperless.minio.DocumentStorage;
import at.fhtw.swkom.paperless.persistence.entities.DocumentsDocument;
import at.fhtw.swkom.paperless.persistence.repos.DocumentsDocumentRepository;
import at.fhtw.swkom.paperless.rabbitmq.RabbitMQProducer;
import at.fhtw.swkom.paperless.services.SearchService;
import at.fhtw.swkom.paperless.services.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.OffsetDateTime;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/api/documents/")
public class DocumentsController implements ApiApi{
    private static final Logger logger = LoggerFactory.getLogger(DocumentsController.class);
    private final DocumentStorage documentStorage;
    private final RabbitMQProducer rabbitMQProducer;
    private final DocumentsDocumentRepository documentsRepository;

    private final SearchService searchService;

    @Autowired
    public DocumentsController(DocumentStorage documentStorage, RabbitMQProducer rabbitMQProducer, DocumentsDocumentRepository documentsRepository, SearchService searchService) {
        this.documentStorage = documentStorage;
        this.rabbitMQProducer = rabbitMQProducer;
        this.documentsRepository = documentsRepository;
        this.searchService = searchService;
    }


    /**
     * POST /api/documents/bulk_edit/
     *
     * @param bulkEditRequest  (optional)
     * @return Success (status code 200)
     */
    @Operation(
            operationId = "bulkEdit",
            tags = { "Documents" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success")
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "bulk_edit/",
            consumes = { "application/json" }
    )
    public ResponseEntity<Void> bulkEdit(
            @Parameter(name = "BulkEditRequest", description = "") @Valid @RequestBody(required = false) BulkEditRequest bulkEditRequest
    ) {
        List<Integer> ids = bulkEditRequest.getDocuments();
        for(Integer id : ids) {
            documentsRepository.deleteById(id);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * DELETE /api/documents/{id}/
     *
     * @param id  (required)
     * @return Success (status code 204)
     */
    @Operation(
            operationId = "deleteDocument",
            tags = { "Documents" },
            responses = {
                    @ApiResponse(responseCode = "204", description = "Success")
            }
    )
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "{id}/"
    )
    public ResponseEntity<Void> deleteDocument(
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id
    ) {
        this.documentsRepository.deleteById(id);
        logger.info("Deleted Document with id: " + id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * GET /api/documents/{id}/download/
     *
     * @param id  (required)
     * @param original  (optional)
     * @return Success (status code 200)
     */
    @Operation(
            operationId = "downloadDocument",
            tags = { "Documents" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = {
                            @Content(mediaType = "application/pdf", schema = @Schema(implementation = org.springframework.core.io.Resource.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "{id}/download/",
            produces = { "application/pdf" }
    )
    public ResponseEntity<org.springframework.core.io.Resource> downloadDocument(
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id,
            @Parameter(name = "original", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "original", required = false) Boolean original
    ) {
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * GET /api/documents/{id}/
     *
     * @param id  (required)
     * @param page  (optional)
     * @param fullPerms  (optional)
     * @return Success (status code 200)
     */
    @Operation(
            operationId = "getDocument",
            tags = { "Documents" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = GetDocument200Response.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "{id}/",
            produces = { "application/json" }
    )
    public ResponseEntity<GetDocument200Response> getDocument(
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id,
            @Parameter(name = "page", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "page", required = false) Integer page,
            @Parameter(name = "full_perms", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "full_perms", required = false) Boolean fullPerms
    ) {
        Optional<DocumentsDocument> optionalDoc = documentsRepository.findById(id);

        if (optionalDoc.isPresent()) {
            DocumentsDocument doc = optionalDoc.get();

            GetDocument200Response response = new GetDocument200Response();
            response.id(doc.getId());
            response.title(doc.getTitle());
            response.added(doc.getAdded().toString());
            response.created(doc.getCreated().toString());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            logger.error("Couldn't get Document");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * GET /api/documents/{id}/metadata/
     *
     * @param id  (required)
     * @return Success (status code 200)
     */
    @Operation(
            operationId = "getDocumentMetadata",
            tags = { "Documents" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = GetDocumentMetadata200Response.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "{id}/metadata/",
            produces = { "application/json" }
    )
    public ResponseEntity<GetDocumentMetadata200Response> getDocumentMetadata(
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"archive_size\" : 6, \"archive_metadata\" : [ { \"prefix\" : \"prefix\", \"namespace\" : \"namespace\", \"value\" : \"value\", \"key\" : \"key\" }, { \"prefix\" : \"prefix\", \"namespace\" : \"namespace\", \"value\" : \"value\", \"key\" : \"key\" } ], \"original_metadata\" : [ \"\", \"\" ], \"original_filename\" : \"original_filename\", \"original_mime_type\" : \"original_mime_type\", \"archive_checksum\" : \"archive_checksum\", \"original_checksum\" : \"original_checksum\", \"lang\" : \"lang\", \"media_filename\" : \"media_filename\", \"has_archive_version\" : true, \"archive_media_filename\" : \"archive_media_filename\", \"original_size\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.OK);

    }


    /**
     * GET /api/documents/{id}/preview/
     *
     * @param id  (required)
     * @return Success (status code 200)
     */
    @Operation(
            operationId = "getDocumentPreview",
            tags = { "Documents" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = {
                            @Content(mediaType = "application/pdf", schema = @Schema(implementation = org.springframework.core.io.Resource.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "{id}/preview/",
            produces = { "application/pdf" }
    )
    public ResponseEntity<org.springframework.core.io.Resource> getDocumentPreview(
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id
    ) {
        return new ResponseEntity<>(HttpStatus.OK);

    }


    /**
     * GET /api/documents/{id}/suggestions/
     *
     * @param id  (required)
     * @return Success (status code 200)
     */
    @Operation(
            operationId = "getDocumentSuggestions",
            tags = { "Documents" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = GetDocumentSuggestions200Response.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "{id}/suggestions/",
            produces = { "application/json" }
    )
    public ResponseEntity<GetDocumentSuggestions200Response> getDocumentSuggestions(
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"storage_paths\" : [ \"\", \"\" ], \"document_types\" : [ \"\", \"\" ], \"dates\" : [ \"\", \"\" ], \"correspondents\" : [ \"\", \"\" ], \"tags\" : [ \"\", \"\" ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.OK);

    }


    /**
     * GET /api/documents/{id}/thumb/
     *
     * @param id  (required)
     * @return Success (status code 200)
     */
    @Operation(
            operationId = "getDocumentThumb",
            tags = { "Documents" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = {
                            @Content(mediaType = "application/pdf", schema = @Schema(implementation = org.springframework.core.io.Resource.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "{id}/thumb/",
            produces = { "application/pdf" }
    )
    public ResponseEntity<org.springframework.core.io.Resource> getDocumentThumb(
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id
    ) {
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * GET /api/documents/
     *
     * @param page  (optional)
     * @param pageSize  (optional)
     * @param query  (optional)
     * @param ordering  (optional)
     * @param tagsIdAll  (optional)
     * @param documentTypeId  (optional)
     * @param storagePathIdIn  (optional)
     * @param correspondentId  (optional)
     * @param truncateContent  (optional)
     * @return Success (status code 200)
     */
    @Operation(
            operationId = "getDocuments",
            tags = { "Documents" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = GetDocuments200Response.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "",
            produces = { "application/json" }
    )
    public ResponseEntity<GetDocuments200Response> getDocuments(
            @Parameter(name = "Page", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "Page", required = false) Integer page,
            @Parameter(name = "page_size", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "page_size", required = false) Integer pageSize,
            @Parameter(name = "query", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "query", required = false) String query,
            @Parameter(name = "ordering", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "ordering", required = false) String ordering,
            @Parameter(name = "tags__id__all", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "tags__id__all", required = false) List<Integer> tagsIdAll,
            @Parameter(name = "document_type__id", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "document_type__id", required = false) Integer documentTypeId,
            @Parameter(name = "storage_path__id__in", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "storage_path__id__in", required = false) Integer storagePathIdIn,
            @Parameter(name = "correspondent__id", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "correspondent__id", required = false) Integer correspondentId,
            @Parameter(name = "truncate_content", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "truncate_content", required = false) Boolean truncateContent,
            @Parameter(name = "title_content", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "title_content", required = false) String titleContent
    ) {
        List<DocumentsDocument> docs;

        if(titleContent != null){
            List<Integer> res_ids = searchService.getDocumentByTitleContent(titleContent);
            logger.info("Found: " + String.valueOf(res_ids.size()) + " items");
            docs = documentsRepository.findByIdIn(res_ids);
        } else
        {
            docs = documentsRepository.findAll();
            logger.info("Show all Documents");
        }
        List<GetDocuments200ResponseResultsInner> results = new ArrayList<>();
        for(DocumentsDocument result : docs) {
            GetDocuments200ResponseResultsInner document = new GetDocuments200ResponseResultsInner();
            document.id(result.getId());
            document.content(result.getContent());
            document.title(result.getTitle());

            results.add(document);
        }
        GetDocuments200Response res = new GetDocuments200Response();
        res.count(docs.size());
        res.results(results);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * POST /api/documents/selection_data/
     *
     * @param selectionDataRequest  (optional)
     * @return Success (status code 200)
     */
    @Operation(
            operationId = "selectionData",
            tags = { "Documents" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = SelectionData200Response.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "selection_data/",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    public ResponseEntity<SelectionData200Response> selectionData(
            @Parameter(name = "SelectionDataRequest", description = "") @Valid @RequestBody(required = false) SelectionDataRequest selectionDataRequest
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"selected_storage_paths\" : [ { \"document_count\" : 6, \"id\" : 0 }, { \"document_count\" : 6, \"id\" : 0 } ], \"selected_document_types\" : [ { \"document_count\" : 6, \"id\" : 0 }, { \"document_count\" : 6, \"id\" : 0 } ], \"selected_correspondents\" : [ { \"document_count\" : 6, \"id\" : 0 }, { \"document_count\" : 6, \"id\" : 0 } ], \"selected_tags\" : [ { \"document_count\" : 6, \"id\" : 0 }, { \"document_count\" : 6, \"id\" : 0 } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * PUT /api/documents/{id}/
     *
     * @param id  (required)
     * @param updateDocumentRequest  (optional)
     * @return Success (status code 200)
     */
    @Operation(
            operationId = "updateDocument",
            tags = { "Documents" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = UpdateDocument200Response.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.PUT,
            value = "{id}/",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    public ResponseEntity<UpdateDocument200Response> updateDocument(
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id,
            @Parameter(name = "UpdateDocumentRequest", description = "") @RequestBody(required = false) UpdateDocumentRequest updateDocumentRequest
    ) {
        Optional<DocumentsDocument> optionalDoc = documentsRepository.findById(id);

        if (optionalDoc.isPresent()) {
            DocumentsDocument doc = optionalDoc.get();
            doc.setTitle(updateDocumentRequest.getTitle());
            documentsRepository.save(doc);

            UpdateDocument200Response response = new UpdateDocument200Response();
            response.id(doc.getId());
            response.title(doc.getTitle());
            response.added(doc.getAdded().toString());
            response.created(doc.getCreated().toString());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            logger.error("Couldn't update Document");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * POST /api/documents/post_document/
     *
     * @param title         (optional)
     * @param created       (optional)
     * @param documentType  (optional)
     * @param tags          (optional)
     * @param correspondent (optional)
     * @param document      (optional)
     * @return Success (status code 200)
     */

    @Operation(
            operationId = "uploadDocument",
            tags = { "Documents" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success")
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "post_document/",
            consumes = { "multipart/form-data" }
    )
    public ResponseEntity<Map<String, Object>> uploadDocument(
            @RequestPart("document") MultipartFile file
    ) {
        DocumentsDocument doc = new DocumentsDocument();
        doc.setTitle(file.getOriginalFilename());
        doc.setContent(file.getContentType());
        doc.setCreated(OffsetDateTime.now());
        doc.setModified(OffsetDateTime.now());
        doc.setAdded(OffsetDateTime.now());
        doc.setStorageType(file.getContentType());
        Integer documentId = this.documentsRepository.save(doc).getId();
        try{
            this.documentStorage.persistObject(documentId, file);
        }catch (Exception exception){
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "File upload failed");
            logger.error("Error occurred during MinIO saving:", exception);
            return ResponseEntity.status(507).body(errorResponse);
        }

        try{
            this.rabbitMQProducer.sendMessage(documentId.toString());
        }
        catch(Exception exception){
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "File upload failed");
            logger.error("Error occurred during RabbitMQ queuing:", exception);
            return ResponseEntity.status(507).body(errorResponse);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("task_id", 123213);
        response.put("message", "File uploaded successfully");

        return ResponseEntity.ok(response);
    }
}
