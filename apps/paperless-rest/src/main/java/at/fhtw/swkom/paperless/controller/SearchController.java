package at.fhtw.swkom.paperless.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class SearchController implements ApiApi{
    /**
     * GET /api/search/autocomplete/
     *
     * @param term  (optional)
     * @param limit  (optional)
     * @return Success (status code 200)
     */
    @Operation(
            operationId = "autoComplete",
            tags = { "Search" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = String.class)))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/api/search/autocomplete/",
            produces = { "application/json" }
    )
    public ResponseEntity<List<String>> autoComplete(
            @Parameter(name = "term", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "term", required = false) String term,
            @Parameter(name = "limit", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "limit", required = false) Integer limit
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
}
