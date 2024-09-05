package com.colak.springtutorial.controller;

import com.colak.springtutorial.annotation.LogRequestResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/aop/")
public class ExampleController {

    // http://localhost:8080/api/aop/hello
    @GetMapping(path = "hello")
    @LogRequestResponse
    public String hello() {
        return "hello";
    }

}
