package com.theophilusgordon.marketsquareserver.controller;

import lombok.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value(staticConstructor = "${welcome.message}")
    private String welcomeMessage;

    @GetMapping("/")
    public String serverRunning(){
        return welcomeMessage;
    }
}
