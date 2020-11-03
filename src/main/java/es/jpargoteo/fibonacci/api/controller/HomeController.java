package es.jpargoteo.fibonacci.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String index() {

        System.out.println("/swagger-ui/index.html");
        return "redirect:/swagger-ui/";
    }
}
