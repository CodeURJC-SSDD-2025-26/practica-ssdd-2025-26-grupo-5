package es.code_urjc_g5.bookify_project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FooterController {
    
    @GetMapping("/aboutUs")
    public String aboutUs() {
        return "aboutUs";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/pp")
    public String privacyPolicy() {
        return "pp";
    }

    @GetMapping("/ToS")
    public String termsOfService() {
        return "ToS";
    }
}
