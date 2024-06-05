package ua.profitsoft.studentsdockerdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.profitsoft.studentsdockerdemo.client.helloservice.HelloRestClient;

@RestController
@RequestMapping("/api/hello")
public class HelloController {

    @Autowired
    private HelloRestClient helloRestClient;

    @GetMapping
    public String hello() {
        return helloRestClient.sayHello();
    }
}
