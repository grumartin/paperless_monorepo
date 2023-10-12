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

public class LoginController implements ApiApi{
    /**
     * GET /api/
     *
     * @return Success (status code 200)
     */
    @Operation(
            operationId = "apiGet",
            tags = { "Login" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success")
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/api/"
    )
    public ResponseEntity<Void> apiGet(

    ) {
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * POST /api/groups/
     *
     * @param createGroupRequest  (optional)
     * @return Success (status code 200)
     */
    @Operation(
            operationId = "createGroup",
            tags = { "Login" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/api/groups/",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    public ResponseEntity<Object> createGroup(
            @Parameter(name = "CreateGroupRequest", description = "") @Valid @RequestBody(required = false) CreateGroupRequest createGroupRequest
    ) {
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * POST /api/users/
     *
     * @param createUserRequest  (optional)
     * @return Success (status code 200)
     */
    @Operation(
            operationId = "createUser",
            tags = { "Login" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = GetUsers200ResponseResultsInner.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/api/users/",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    public ResponseEntity<GetUsers200ResponseResultsInner> createUser(
            @Parameter(name = "CreateUserRequest", description = "") @Valid @RequestBody(required = false) CreateUserRequest createUserRequest
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"is_active\" : true, \"is_superuser\" : true, \"user_permissions\" : [ \"\", \"\" ], \"is_staff\" : true, \"last_name\" : \"last_name\", \"groups\" : [ \"\", \"\" ], \"password\" : \"password\", \"id\" : 5, \"date_joined\" : \"date_joined\", \"first_name\" : \"first_name\", \"email\" : \"email\", \"username\" : \"username\", \"inherited_permissions\" : [ \"inherited_permissions\", \"inherited_permissions\" ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * DELETE /api/groups/{id}/
     *
     * @param id  (required)
     * @return Success (status code 204)
     */
    @Operation(
            operationId = "deleteGroup",
            tags = { "Login" },
            responses = {
                    @ApiResponse(responseCode = "204", description = "Success")
            }
    )
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/api/groups/{id}/"
    )
    public ResponseEntity<Void> deleteGroup(
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id
    ) {
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * DELETE /api/users/{id}/
     *
     * @param id  (required)
     * @return Success (status code 204)
     */
    @Operation(
            operationId = "deleteUser",
            tags = { "Login" },
            responses = {
                    @ApiResponse(responseCode = "204", description = "Success")
            }
    )
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/api/users/{id}/"
    )
    public ResponseEntity<Void> deleteUser(
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id
    ) {
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * GET /api/groups/
     *
     * @param page  (optional)
     * @param pageSize  (optional)
     * @return Success (status code 200)
     */
    @Operation(
            operationId = "getGroups",
            tags = { "Login" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = GetGroups200Response.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/api/groups/",
            produces = { "application/json" }
    )
    public ResponseEntity<GetGroups200Response> getGroups(
            @Parameter(name = "page", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "page", required = false) Integer page,
            @Parameter(name = "page_size", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "page_size", required = false) Integer pageSize
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"next\" : 6, \"all\" : [ \"\", \"\" ], \"previous\" : 1, \"count\" : 0, \"results\" : [ \"\", \"\" ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * POST /api/token/
     *
     * @param userInfo  (optional)
     * @return Success (status code 200)
     */
    @Operation(
            operationId = "getToken",
            tags = { "Login" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success")
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/api/token/",
            consumes = { "application/json", "text/json", "application/*+json" }
    )
    public ResponseEntity<Void> getToken(
            @Parameter(name = "UserInfo", description = "") @Valid @RequestBody(required = false) UserInfo userInfo
    ) {
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * GET /api/users/
     *
     * @param page  (optional)
     * @param pageSize  (optional)
     * @return Success (status code 200)
     */
    @Operation(
            operationId = "getUsers",
            tags = { "Login" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = GetUsers200Response.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/api/users/",
            produces = { "application/json" }
    )
    public ResponseEntity<GetUsers200Response> getUsers(
            @Parameter(name = "page", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "page", required = false) Integer page,
            @Parameter(name = "page_size", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "page_size", required = false) Integer pageSize
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"next\" : 6, \"all\" : [ 5, 5 ], \"previous\" : 1, \"count\" : 0, \"results\" : [ { \"is_active\" : true, \"is_superuser\" : true, \"user_permissions\" : [ \"\", \"\" ], \"is_staff\" : true, \"last_name\" : \"last_name\", \"groups\" : [ \"\", \"\" ], \"password\" : \"password\", \"id\" : 5, \"date_joined\" : \"date_joined\", \"first_name\" : \"first_name\", \"email\" : \"email\", \"username\" : \"username\", \"inherited_permissions\" : [ \"inherited_permissions\", \"inherited_permissions\" ] }, { \"is_active\" : true, \"is_superuser\" : true, \"user_permissions\" : [ \"\", \"\" ], \"is_staff\" : true, \"last_name\" : \"last_name\", \"groups\" : [ \"\", \"\" ], \"password\" : \"password\", \"id\" : 5, \"date_joined\" : \"date_joined\", \"first_name\" : \"first_name\", \"email\" : \"email\", \"username\" : \"username\", \"inherited_permissions\" : [ \"inherited_permissions\", \"inherited_permissions\" ] } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.OK);

    }


    /**
     * POST /api/
     *
     * @return Success (status code 200)
     */
    @Operation(
            operationId = "root",
            tags = { "Login" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success")
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/api/"
    )
    public ResponseEntity<Void> root(

    ) {
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * GET /api/statistics/
     *
     * @return Success (status code 200)
     */
    @Operation(
            operationId = "statistics",
            tags = { "Login" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Statistics200Response.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/api/statistics/",
            produces = { "application/json" }
    )
    public ResponseEntity<Statistics200Response> statistics(

    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"document_file_type_counts\" : [ { \"mime_type\" : \"mime_type\", \"mime_type_count\" : 5 }, { \"mime_type\" : \"mime_type\", \"mime_type_count\" : 5 } ], \"documents_inbox\" : 6, \"inbox_tag\" : 1, \"documents_total\" : 0, \"character_count\" : 5 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.OK);

    }
    /**
     * PUT /api/groups/{id}/
     *
     * @param id  (required)
     * @param updateGroupRequest  (optional)
     * @return Success (status code 200)
     */
    @Operation(
            operationId = "updateGroup",
            tags = { "Login" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = UpdateGroup200Response.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/api/groups/{id}/",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    public ResponseEntity<UpdateGroup200Response> updateGroup(
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id,
            @Parameter(name = "UpdateGroupRequest", description = "") @Valid @RequestBody(required = false) UpdateGroupRequest updateGroupRequest
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"permissions\" : [ \"permissions\", \"permissions\" ], \"name\" : \"name\", \"id\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * PUT /api/users/{id}/
     *
     * @param id  (required)
     * @param updateUserRequest  (optional)
     * @return Success (status code 200)
     */
    @Operation(
            operationId = "updateUser",
            tags = { "Login" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = GetUsers200ResponseResultsInner.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/api/users/{id}/",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    public ResponseEntity<GetUsers200ResponseResultsInner> updateUser(
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id,
            @Parameter(name = "UpdateUserRequest", description = "") @Valid @RequestBody(required = false) UpdateUserRequest updateUserRequest
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"is_active\" : true, \"is_superuser\" : true, \"user_permissions\" : [ \"\", \"\" ], \"is_staff\" : true, \"last_name\" : \"last_name\", \"groups\" : [ \"\", \"\" ], \"password\" : \"password\", \"id\" : 5, \"date_joined\" : \"date_joined\", \"first_name\" : \"first_name\", \"email\" : \"email\", \"username\" : \"username\", \"inherited_permissions\" : [ \"inherited_permissions\", \"inherited_permissions\" ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
