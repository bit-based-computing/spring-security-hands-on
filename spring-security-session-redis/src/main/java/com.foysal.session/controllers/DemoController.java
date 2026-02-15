package com.foysal.session.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/public/hello")
    public String publicHello() {
        return "Hello Public";
    }

    @GetMapping("/home")
    public String home() {
        return "Hello Authenticated User";
    }

    @GetMapping("/admin/dashboard")
    public String admin() {
        return "Hello Admin";
    }
}

