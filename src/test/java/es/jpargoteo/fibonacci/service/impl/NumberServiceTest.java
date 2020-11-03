package es.jpargoteo.fibonacci.service.impl;

import es.jpargoteo.fibonacci.exception.ApiException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {NumberService.class})
class NumberServiceTest {

    @Autowired
    private NumberService numberService;

    @ParameterizedTest(name = "#{index} - Find Fibonacci's golden number for {0} with spected result {1}")
    @CsvSource({
            "0, 0",
            "1, 1",
            "2, 1",
            "6, 8",
            "50, 12586269025",
            "80, 23416728348467685",
    })
    void getNumberPositive(Long number, Long expectedResult) {

        try {

            assertEquals(expectedResult.longValue(), numberService.getNumber(number).longValue());
        } catch (ApiException e) {

            fail("Expected no exception to be thrown");
        }
    }

    @ParameterizedTest(name = "#{index} - Find Fibonacci's golden number for {0} with spected exception result.")
    @CsvSource({
            "-1",
            "93",
    })
    void getNumberNegative(Long number) {

        try {

            assertNotNull(numberService.getNumber(number));

            fail("Expected ApiException to be thrown");
        } catch (ApiException e) {

            assertEquals(405, e.getCode());
        }
    }
}