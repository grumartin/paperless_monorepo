package at.fhtw.swkom.paperless.controller;

import at.fhtw.swkom.paperless.services.dto.AckTasks200Response;
import at.fhtw.swkom.paperless.services.dto.AckTasksRequest;
import at.fhtw.swkom.paperless.services.dto.GetTasks200ResponseInner;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public class TasksController implements ApiApi{

    /**
     * POST /api/acknowledge_tasks/
     *
     * @param ackTasksRequest  (optional)
     * @return Success (status code 200)
     */
    @Operation(
            operationId = "ackTasks",
            tags = { "Tasks" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = AckTasks200Response.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/api/acknowledge_tasks/",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    public ResponseEntity<AckTasks200Response> ackTasks(
            @Parameter(name = "AckTasksRequest", description = "") @Valid @RequestBody(required = false) AckTasksRequest ackTasksRequest
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"result\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * GET /api/tasks/
     *
     * @return Success (status code 200)
     */
    @Operation(
            operationId = "getTasks",
            tags = { "Tasks" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GetTasks200ResponseInner.class)))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/api/tasks/",
            produces = { "application/json" }
    )
    public ResponseEntity<List<GetTasks200ResponseInner>> getTasks(

    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"date_done\" : \"date_done\", \"result\" : \"result\", \"acknowledged\" : true, \"task_file_name\" : \"task_file_name\", \"date_created\" : \"date_created\", \"related_document\" : \"related_document\", \"task_id\" : \"task_id\", \"id\" : 0, \"type\" : \"type\", \"status\" : \"status\" }, { \"date_done\" : \"date_done\", \"result\" : \"result\", \"acknowledged\" : true, \"task_file_name\" : \"task_file_name\", \"date_created\" : \"date_created\", \"related_document\" : \"related_document\", \"task_id\" : \"task_id\", \"id\" : 0, \"type\" : \"type\", \"status\" : \"status\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
