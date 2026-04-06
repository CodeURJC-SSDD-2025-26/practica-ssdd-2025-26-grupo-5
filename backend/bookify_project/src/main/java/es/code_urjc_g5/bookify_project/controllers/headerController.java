package es.code_urjc_g5.bookify_project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class headerController {
    
    @GetMapping("/admin")
    public String aboutUs() {
        return "admin";
    }

    @GetMapping("/login")
    public String contact() {
        return "login";
    }

    @GetMapping("/profile")
    public String privacyPolicy() {
        return "profile";
    }

    @GetMapping("/myLibrary")
    public String termsOfService() {
        return "myLibrary";
    }
}
