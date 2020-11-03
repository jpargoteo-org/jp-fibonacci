package es.jpargoteo.fibonacci.service.impl;

import es.jpargoteo.fibonacci.exception.ApiException;
import es.jpargoteo.fibonacci.exception.FibonacciException;
import es.jpargoteo.fibonacci.service.INumberService;
import es.jpargoteo.fibonacci.utils.Functions;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NumberService extends Service implements INumberService {

    @Override
    public Long getNumber(Long number) throws ApiException {

        try {

            List<Long> fibonacciSeries = Functions.getFibonacciSeries(number);

            return fibonacciSeries.get(fibonacciSeries.size() - 1);
        } catch (FibonacciException e) {

            throw new ApiException(405, e.getMessage());
        } catch (Exception e) {

            throw new ApiException(500, e.getMessage());
        }
    }
}
