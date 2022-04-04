package com.example.controller;

import com.example.service.RandomClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/randoms")
public class RandomController {

    private final RandomClientService randomClientService;

    @Autowired
    public RandomController(RandomClientService randomClientService) {
        this.randomClientService = randomClientService;
    }

    @GetMapping("/get-random/{name}")
    public Object getRandom(@PathVariable String name) {
        return randomClientService.getRandom(name);
    }
}
