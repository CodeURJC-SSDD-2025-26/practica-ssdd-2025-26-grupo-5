package es.code_urjc_g5.bookify_project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class headerController {
    @GetMapping("/profile")
    public String privacyPolicy() {
        return "profile";
    }
}
