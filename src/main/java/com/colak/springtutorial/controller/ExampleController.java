package com.colak.springtutorial.controller;

import com.colak.springtutorial.annotation.LogRequestResponse;
import com.colak.springtutorial.annotation.RedisLock;
import com.colak.springtutorial.annotation.SpecificParameter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    // http://localhost:8080/api/aop/status?card=yourCardValue&id=yourIdValue
    // The parameter 'card' was detected in the request with the value: yourCardValue
    @GetMapping(path = "status")
    @SpecificParameter("card")
    @RedisLock(key = "myLockKey", timeout = 10)
    public String checkStatusCard(@RequestParam(name = "card") String card, @RequestParam String id) {
        return "status account for client id: " + id + "!";
    }
}
