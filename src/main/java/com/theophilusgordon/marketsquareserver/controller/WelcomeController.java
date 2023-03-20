package com.theophilusgordon.marketsquareserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/")
    @ResponseBody
    public String welcomeMessage(){
        return "index";
    }
}
