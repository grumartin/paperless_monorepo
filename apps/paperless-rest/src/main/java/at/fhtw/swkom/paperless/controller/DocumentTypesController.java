package at.fhtw.swkom.paperless.controller;

import at.fhtw.swkom.paperless.services.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class DocumentTypesController implements ApiApi{
    /**
     * POST /api/document_types/
     *
     * @param createCorrespondentRequest  (optional)
     * @return Success (status code 200)
     */
    @Operation(
            operationId = "createDocumentType",
            tags = { "DocumentTypes" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CreateDocumentType200Response.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/api/document_types/",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    public ResponseEntity<CreateDocumentType200Response> createDocumentType(
            @Parameter(name = "CreateCorrespondentRequest", description = "") @Valid @RequestBody(required = false) CreateCorrespondentRequest createCorrespondentRequest
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"owner\" : 1, \"matching_algorithm\" : 6, \"user_can_change\" : true, \"is_insensitive\" : true, \"name\" : \"name\", \"match\" : \"match\", \"id\" : 0, \"slug\" : \"slug\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * DELETE /api/document_types/{id}/
     *
     * @param id  (required)
     * @return Success (status code 204)
     */
    @Operation(
            operationId = "deleteDocumentType",
            tags = { "DocumentTypes" },
            responses = {
                    @ApiResponse(responseCode = "204", description = "Success")
            }
    )
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/api/document_types/{id}/"
    )
    public ResponseEntity<Void> deleteDocumentType(
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id
    ) {
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * GET /api/document_types/
     *
     * @param page  (optional)
     * @param fullPerms  (optional)
     * @return Success (status code 200)
     */
    @Operation(
            operationId = "getDocumentTypes",
            tags = { "DocumentTypes" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = GetDocumentTypes200Response.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/api/document_types/",
            produces = { "application/json" }
    )
    public ResponseEntity<GetDocumentTypes200Response> getDocumentTypes(
            @Parameter(name = "page", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "page", required = false) Integer page,
            @Parameter(name = "full_perms", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "full_perms", required = false) Boolean fullPerms
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"next\" : 6, \"all\" : [ 5, 5 ], \"previous\" : 1, \"count\" : 0, \"results\" : [ { \"owner\" : 9, \"matching_algorithm\" : 2, \"document_count\" : 7, \"is_insensitive\" : true, \"permissions\" : { \"view\" : { \"groups\" : [ \"\", \"\" ], \"users\" : [ \"\", \"\" ] }, \"change\" : { \"groups\" : [ \"\", \"\" ], \"users\" : [ \"\", \"\" ] } }, \"name\" : \"name\", \"match\" : \"match\", \"id\" : 5, \"slug\" : \"slug\" }, { \"owner\" : 9, \"matching_algorithm\" : 2, \"document_count\" : 7, \"is_insensitive\" : true, \"permissions\" : { \"view\" : { \"groups\" : [ \"\", \"\" ], \"users\" : [ \"\", \"\" ] }, \"change\" : { \"groups\" : [ \"\", \"\" ], \"users\" : [ \"\", \"\" ] } }, \"name\" : \"name\", \"match\" : \"match\", \"id\" : 5, \"slug\" : \"slug\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * PUT /api/document_types/{id}/
     *
     * @param id  (required)
     * @param updateDocumentTypeRequest  (optional)
     * @return Success (status code 200)
     */
    @Operation(
            operationId = "updateDocumentType",
            tags = { "DocumentTypes" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = UpdateDocumentType200Response.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/api/document_types/{id}/",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    public ResponseEntity<UpdateDocumentType200Response> updateDocumentType(
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id,
            @Parameter(name = "UpdateDocumentTypeRequest", description = "") @Valid @RequestBody(required = false) UpdateDocumentTypeRequest updateDocumentTypeRequest
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"owner\" : 5, \"matching_algorithm\" : 6, \"user_can_change\" : true, \"document_count\" : 1, \"is_insensitive\" : true, \"name\" : \"name\", \"match\" : \"match\", \"id\" : 0, \"slug\" : \"slug\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
