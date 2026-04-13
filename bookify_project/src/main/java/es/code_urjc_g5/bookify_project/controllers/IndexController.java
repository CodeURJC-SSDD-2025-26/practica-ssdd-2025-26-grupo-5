package es.code_urjc_g5.bookify_project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.code_urjc_g5.bookify_project.repositories.BookRepository;
import es.code_urjc_g5.bookify_project.services.UserService;
import es.code_urjc_g5.bookify_project.models.User;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
public class IndexController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserService userService;

    // Authentication principal is used to get the currently logged in user, if any.
    // This way we can show different content on the index page depending on whether
    // the user is logged in and if they are an admin or not.
    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("topBooks", bookRepository.findTop10ByOrderByReviewCountDesc());

        if (currentUser != null) {
            // Hay sesión activa
            model.addAttribute("logged", true);

            Optional<User> userLoggedIn = userService.findByEmail(currentUser.getUsername());
            userLoggedIn.ifPresent(u -> model.addAttribute("isAdmin", "ADMIN".equals(u.getUserRole())));
        } else {
            // Nadie ha iniciado sesión
            model.addAttribute("logged", false);
            model.addAttribute("isAdmin", false);
        }

        return "index";
    }

}
