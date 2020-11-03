package es.jpargoteo.fibonacci.service.impl;

import es.jpargoteo.fibonacci.exception.ApiException;
import es.jpargoteo.fibonacci.exception.FibonacciException;
import es.jpargoteo.fibonacci.service.ISequenceService;
import es.jpargoteo.fibonacci.utils.Functions;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SequenceService extends Service implements ISequenceService {

    @Override
    public List<Long> getSequence(Long number) throws ApiException {

        try {

            return Functions.getFibonacciSeries(number);
        } catch (FibonacciException e) {

            throw new ApiException(405, e.getMessage());
        } catch (Exception e) {

            throw new ApiException(500, e.getMessage());
        }
    }
}
