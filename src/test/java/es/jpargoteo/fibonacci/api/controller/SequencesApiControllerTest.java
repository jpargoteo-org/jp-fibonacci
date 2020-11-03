package es.jpargoteo.fibonacci.api.controller;

import org.junit.Ignore;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

@Ignore
@AutoConfigureMockRestServiceServer
@ContextConfiguration(classes = {SequencesApiController.class})
@RestClientTest
class SequencesApiControllerTest {

    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    @ParameterizedTest(name = "#{index} - Find Fibonacci's golden number for {0} with spected result OK")
    @CsvSource({
            "0",
            "1",
            "2",
            "6",
            "50",
            "80",
    })
    void getNumberPositive(Long number) {

        mockRestServiceServer.expect(requestTo("/api/sequences/" + number.toString()))
                .andRespond(MockRestResponseCreators.withStatus(HttpStatus.OK));
    }

    @ParameterizedTest(name = "#{index} - Find Fibonacci's golden number for {0} with spected result OK")
    @CsvSource({
            "-1",
            "92",
    })
    void getNumberNegative(Long number) {

        mockRestServiceServer.expect(requestTo("/api/sequences/" + number.toString()))
                .andRespond(MockRestResponseCreators.withStatus(HttpStatus.BAD_REQUEST));
    }

}