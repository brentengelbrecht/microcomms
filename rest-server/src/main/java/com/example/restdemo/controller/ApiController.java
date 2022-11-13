package com.example.restdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("/test")
    public String getTest() {
        final int s = 10;
        try {
            Thread.sleep(s * 1000L);
        } catch (InterruptedException e) {
            System.out.println("Woke up");
        }
        return String.format("Response after %d seconds", s);
    }
}
