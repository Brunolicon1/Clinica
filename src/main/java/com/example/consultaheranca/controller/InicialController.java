package com.example.consultaheranca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InicialController {

    @GetMapping("/")
    public String home() {
        return "index";
    }
}