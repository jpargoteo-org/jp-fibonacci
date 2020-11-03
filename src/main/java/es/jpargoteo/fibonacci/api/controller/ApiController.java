package es.jpargoteo.fibonacci.api.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/api/")
public class ApiController {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    protected final ObjectMapper objectMapper;

    protected final HttpServletRequest request;

    public ApiController(ObjectMapper objectMapper, HttpServletRequest request) {

        this.objectMapper = objectMapper;
        this.request = request;
    }
}
