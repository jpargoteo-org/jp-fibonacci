package es.jpargoteo.fibonacci.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.jpargoteo.fibonacci.api.SequencesApi;
import es.jpargoteo.fibonacci.exception.ApiException;
import es.jpargoteo.fibonacci.service.ISequenceService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class SequencesApiController extends ApiController implements SequencesApi {

    @Autowired
    private ISequenceService sequenceService;

    public SequencesApiController(ObjectMapper objectMapper, HttpServletRequest request) {

        super(objectMapper, request);
    }

    public ResponseEntity<List<Long>> getSequence(@Parameter(in = ParameterIn.PATH, description = "Number for which to find the sequence.", required = true, schema = @Schema()) @PathVariable("number") Long number) {

        String accept = request.getHeader("Accept");

        if (accept != null && accept.contains("application/json")) {
            try {

                return ResponseEntity.ok(sequenceService.getSequence(number));
            } catch (ApiException e) {

                if (405 == e.getCode()) {

                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
                } else if (500 == e.getCode()) {

                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
                }

            } catch (Exception e) {

                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
        }

        return new ResponseEntity<List<Long>>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
