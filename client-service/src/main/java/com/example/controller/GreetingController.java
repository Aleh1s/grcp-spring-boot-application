package com.example.controller;

import com.example.service.GreetingClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/messages")
public class GreetingController {

    private final GreetingClientService messageClientService;

    @Autowired
    public GreetingController(GreetingClientService messageClientService) {
        this.messageClientService = messageClientService;
    }

    @GetMapping("/get-message/{name}/{message}")
    public Object getMessage(@PathVariable String name,
                             @PathVariable String message) {
        return messageClientService.getMessage(name, message);
    }
}
