package es.jpargoteo.fibonacci.service.impl;

import es.jpargoteo.fibonacci.exception.ApiException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {SequenceService.class})
class SequenceServiceTest {


    @Autowired
    private SequenceService sequenceService;

    @ParameterizedTest(name = "#{index} - Find Fibonacci's golden number for {0} with spected result {1}")
    @CsvSource({
            "5, 5",
            "6, 5",
            "50, 5",
            "80, 5",
    })
    void getSequencePositive(Long number, Long expectedResult) {

        try {

            assertEquals(expectedResult, sequenceService.getSequence(number).get(5));
        } catch (ApiException e) {

            fail("Expected no exception to be thrown");
        }
    }

    @ParameterizedTest(name = "#{index} - Find Fibonacci's golden number for {0} with spected exception result.")
    @CsvSource({
            "-1",
            "93",
    })
    void getSequenceNegative(Long number) {

        try {

            assertNotNull(sequenceService.getSequence(number));

            fail("Expected ApiException to be thrown");
        } catch (ApiException e) {

            assertEquals(405, e.getCode());
        }
    }
}