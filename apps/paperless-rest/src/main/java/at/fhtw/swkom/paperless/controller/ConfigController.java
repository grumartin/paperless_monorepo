package at.fhtw.swkom.paperless.controller;

import at.fhtw.swkom.paperless.services.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ConfigController implements ApiApi{
    /**
     * POST /api/saved_views/
     *
     * @param createSavedViewsRequest  (optional)
     * @return Success (status code 200)
     */
    @Operation(
            operationId = "createSavedViews",
            tags = { "Config" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success")
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/api/saved_views/",
            consumes = { "application/json" }
    )
    public ResponseEntity<Void> createSavedViews(
            @Parameter(name = "CreateSavedViewsRequest", description = "") @Valid @RequestBody(required = false) CreateSavedViewsRequest createSavedViewsRequest
    ) {
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * POST /api/storage_paths/
     *
     * @param createStoragePathRequest  (optional)
     * @return Success (status code 200)
     */
    @Operation(
            operationId = "createStoragePath",
            tags = { "Config" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CreateStoragePath200Response.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/api/storage_paths/",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    public ResponseEntity<CreateStoragePath200Response> createStoragePath(
            @Parameter(name = "CreateStoragePathRequest", description = "") @Valid @RequestBody(required = false) CreateStoragePathRequest createStoragePathRequest
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"owner\" : 1, \"path\" : \"path\", \"matching_algorithm\" : 6, \"user_can_change\" : true, \"is_insensitive\" : true, \"name\" : \"name\", \"match\" : \"match\", \"id\" : 0, \"slug\" : \"slug\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * POST /api/ui_settings/
     *
     * @param createUISettingsRequest  (optional)
     * @return Success (status code 200)
     */
    @Operation(
            operationId = "createUISettings",
            tags = { "Config" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CreateUISettings200Response.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/api/ui_settings/",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    public ResponseEntity<CreateUISettings200Response> createUISettings(
            @Parameter(name = "CreateUISettingsRequest", description = "") @Valid @RequestBody(required = false) CreateUISettingsRequest createUISettingsRequest
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"success\" : true }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * DELETE /api/storage_paths/{id}/
     *
     * @param id  (required)
     * @return Success (status code 204)
     */
    @Operation(
            operationId = "deleteStoragePath",
            tags = { "Config" },
            responses = {
                    @ApiResponse(responseCode = "204", description = "Success")
            }
    )
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/api/storage_paths/{id}/"
    )
    public ResponseEntity<Void> deleteStoragePath(
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id
    ) {
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * GET /api/logs/{id}/
     *
     * @param id  (required)
     * @return Success (status code 200)
     */
    @Operation(
            operationId = "getLog",
            tags = { "Config" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = String.class)))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/api/logs/{id}/",
            produces = { "application/json" }
    )
    public ResponseEntity<List<String>> getLog(
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") String id
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ \"\", \"\" ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.OK);

    }


    /**
     * GET /api/logs/
     *
     * @return Success (status code 200)
     */
    @Operation(
            operationId = "getLogs",
            tags = { "Config" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = String.class)))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/api/logs/",
            produces = { "application/json" }
    )
    public ResponseEntity<List<String>> getLogs(

    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ \"\", \"\" ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.OK);

    }


    /**
     * GET /api/saved_views/
     *
     * @param page  (optional)
     * @param pageSize  (optional)
     * @return Success (status code 200)
     */
    @Operation(
            operationId = "getSavedViews",
            tags = { "Config" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = GetSavedViews200Response.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/api/saved_views/",
            produces = { "application/json" }
    )
    public ResponseEntity<GetSavedViews200Response> getSavedViews(
            @Parameter(name = "page", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "page", required = false) Integer page,
            @Parameter(name = "page_size", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "page_size", required = false) Integer pageSize
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"next\" : 6, \"all\" : [ 5, 5 ], \"previous\" : 1, \"count\" : 0, \"results\" : [ { \"owner\" : { \"is_superuser\" : true, \"is_active\" : true, \"user_permissions\" : [ 9, 9 ], \"is_staff\" : true, \"last_login\" : \"last_login\", \"last_name\" : \"last_name\", \"groups\" : [ \"\", \"\" ], \"password\" : \"password\", \"id\" : 7, \"date_joined\" : \"date_joined\", \"first_name\" : \"first_name\", \"email\" : \"email\", \"username\" : \"username\" }, \"user_can_change\" : true, \"sort_field\" : \"sort_field\", \"show_on_dashboard\" : true, \"name\" : \"name\", \"show_in_sidebar\" : true, \"filter_rules\" : [ { \"rule_type\" : 2, \"value\" : \"value\" }, { \"rule_type\" : 2, \"value\" : \"value\" } ], \"sort_reverse\" : true, \"id\" : 5 }, { \"owner\" : { \"is_superuser\" : true, \"is_active\" : true, \"user_permissions\" : [ 9, 9 ], \"is_staff\" : true, \"last_login\" : \"last_login\", \"last_name\" : \"last_name\", \"groups\" : [ \"\", \"\" ], \"password\" : \"password\", \"id\" : 7, \"date_joined\" : \"date_joined\", \"first_name\" : \"first_name\", \"email\" : \"email\", \"username\" : \"username\" }, \"user_can_change\" : true, \"sort_field\" : \"sort_field\", \"show_on_dashboard\" : true, \"name\" : \"name\", \"show_in_sidebar\" : true, \"filter_rules\" : [ { \"rule_type\" : 2, \"value\" : \"value\" }, { \"rule_type\" : 2, \"value\" : \"value\" } ], \"sort_reverse\" : true, \"id\" : 5 } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.OK);

    }


    /**
     * GET /api/storage_paths
     *
     * @param page  (optional)
     * @param fullPerms  (optional)
     * @return Success (status code 200)
     */
    @Operation(
            operationId = "getStoragePaths",
            tags = { "Config" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = GetStoragePaths200Response.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/api/storage_paths/",
            produces = { "application/json" }
    )
    public ResponseEntity<GetStoragePaths200Response> getStoragePaths(
            @Parameter(name = "page", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "page", required = false) Integer page,
            @Parameter(name = "full_perms", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "full_perms", required = false) Boolean fullPerms
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"next\" : true, \"all\" : [ 6, 6 ], \"previous\" : true, \"count\" : 0, \"results\" : [ { \"owner\" : 2, \"path\" : \"path\", \"matching_algorithm\" : 5, \"document_count\" : 5, \"is_insensitive\" : true, \"permissions\" : { \"view\" : { \"groups\" : [ \"\", \"\" ], \"users\" : [ 7, 7 ] }, \"change\" : { \"groups\" : [ \"\", \"\" ], \"users\" : [ 7, 7 ] } }, \"name\" : \"name\", \"match\" : \"match\", \"id\" : 1, \"slug\" : \"slug\" }, { \"owner\" : 2, \"path\" : \"path\", \"matching_algorithm\" : 5, \"document_count\" : 5, \"is_insensitive\" : true, \"permissions\" : { \"view\" : { \"groups\" : [ \"\", \"\" ], \"users\" : [ 7, 7 ] }, \"change\" : { \"groups\" : [ \"\", \"\" ], \"users\" : [ 7, 7 ] } }, \"name\" : \"name\", \"match\" : \"match\", \"id\" : 1, \"slug\" : \"slug\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * GET /api/ui_settings/
     *
     * @return Success (status code 200)
     */
    @Operation(
            operationId = "getUISettings",
            tags = { "Config" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = GetUISettings200Response.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/api/ui_settings/",
            produces = { "application/json" }
    )
    public ResponseEntity<GetUISettings200Response> getUISettings(

    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"settings\" : { \"update_checking\" : { \"backend_setting\" : \"backend_setting\" } }, \"permissions\" : [ \"permissions\", \"permissions\" ], \"display_name\" : \"display_name\", \"user\" : { \"is_superuser\" : true, \"groups\" : [ \"\", \"\" ], \"id\" : 0, \"username\" : \"username\" } }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * PUT /api/storage_paths/{id}/
     *
     * @param id  (required)
     * @param updateStoragePathRequest  (optional)
     * @return Success (status code 200)
     */
    @Operation(
            operationId = "updateStoragePath",
            tags = { "Config" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = UpdateStoragePath200Response.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/api/storage_paths/{id}/",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    public ResponseEntity<UpdateStoragePath200Response> updateStoragePath(
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id,
            @Parameter(name = "UpdateStoragePathRequest", description = "") @Valid @RequestBody(required = false) UpdateStoragePathRequest updateStoragePathRequest
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"owner\" : 5, \"path\" : \"path\", \"matching_algorithm\" : 6, \"user_can_change\" : true, \"document_count\" : 1, \"is_insensitive\" : true, \"name\" : \"name\", \"match\" : \"match\", \"id\" : 0, \"slug\" : \"slug\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.OK);

    }


}
