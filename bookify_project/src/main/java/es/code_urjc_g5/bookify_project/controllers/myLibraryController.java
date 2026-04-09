package es.code_urjc_g5.bookify_project.controllers;

import es.code_urjc_g5.bookify_project.models.Book;
import es.code_urjc_g5.bookify_project.repositories.BookRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import es.code_urjc_g5.bookify_project.services.UserService;

@Controller
public class myLibraryController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/myLibrary")
    public String myLibrary(Model model, Authentication authentication) {

        userService.findByEmail(authentication.getName()).ifPresent(user -> {
            model.addAttribute("user", user);
            model.addAttribute("collections", user.getCollections());
        });

        return "myLibrary";
    }
}
