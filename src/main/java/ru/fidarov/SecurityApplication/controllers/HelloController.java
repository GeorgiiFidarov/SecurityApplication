package ru.fidarov.SecurityApplication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/content")
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(){
        return "content/hello";
    }
}
