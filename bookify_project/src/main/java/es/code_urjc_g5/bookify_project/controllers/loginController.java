package es.code_urjc_g5.bookify_project.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import es.code_urjc_g5.bookify_project.models.User;

import es.code_urjc_g5.bookify_project.services.UserService;

@Controller
public class loginController {

    @Autowired
    private UserService userService;

    // Muestra la página de login
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Muestra el formulario de registro
    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    // Procesa el registro
    @PostMapping("/register")
    public String registerSubmit(User user) {
        boolean ok = userService.registerUser(user);
        if (ok) {
            return "redirect:/login";
        } else {
            return "redirect:/register?error";
        }
    }

    // Perfil del usuario autenticado
    @GetMapping("/profile/me")
    public String myProfile(Model model, Authentication auth) {
        userService.findByEmail(auth.getName())
                .ifPresent(user -> model.addAttribute("user", user));
        return "profile";
    }
}
