package es.jpargoteo.fibonacci.utils;

import es.jpargoteo.fibonacci.exception.FibonacciException;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Functions {

    public static List<Long> getFibonacciSeries(Long number) throws FibonacciException {


        if (number <= Constants.limit) {

            if (number >= 0) {

                return Stream.iterate(new Long[]{0L, 1L}, t -> new Long[]{t[1], t[0] + t[1]})
                        .limit(number + 1)
                        .map(n -> n[0])
                        .collect(toList());
            } else {

                throw new FibonacciException("The input number shouldn't be negative. We are no sad people :)");
            }
        } else {

            throw new FibonacciException("We know reaching the limit is awesome. We don't support a number bigger than 92 but we are working on it.");
        }
    }
}
