package ru.fidarov.SecurityApplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.fidarov.SecurityApplication.services.AdminService;

@Controller
@RequestMapping("/content")
public class HelloController {

    private final AdminService adminService;

    @Autowired
    public HelloController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/hello")
    public String sayHello(){
        return "content/hello";
    }

    @GetMapping("/admin")
    public String adminPage(){
        return "content/admin";
    }
}
