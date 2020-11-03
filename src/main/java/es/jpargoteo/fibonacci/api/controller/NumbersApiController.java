package es.jpargoteo.fibonacci.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.jpargoteo.fibonacci.api.NumbersApi;
import es.jpargoteo.fibonacci.exception.ApiException;
import es.jpargoteo.fibonacci.service.INumberService;
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

@RestController
public class NumbersApiController extends ApiController implements NumbersApi {

    @Autowired
    private INumberService numberService;

    public NumbersApiController(ObjectMapper objectMapper, HttpServletRequest request) {

        super(objectMapper, request);
    }

    public ResponseEntity<Long> getNumber(@Parameter(in = ParameterIn.PATH, description = "Number for which to find its value in the sequence.", required = true, schema = @Schema()) @PathVariable("number") Long number) {

        String accept = request.getHeader("Accept");

        if (accept != null && accept.contains("application/json")) {
            try {

                return ResponseEntity.ok(numberService.getNumber(number));
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

        return new ResponseEntity<Long>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
