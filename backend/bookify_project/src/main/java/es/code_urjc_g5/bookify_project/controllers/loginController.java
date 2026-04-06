package es.code_urjc_g5.bookify_project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class loginController {
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/signUp")
    public String signUp() {
        return "signUp";
    }
}
