package es.code_urjc_g5.bookify_project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import es.code_urjc_g5.bookify_project.repositories.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import es.code_urjc_g5.bookify_project.models.User;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class UserController {

    @Autowired
    private UserRepository users;

    public void init() {
    }

    @PostMapping("/user/new")
    public String newUser(Model model, User user) {
        users.save(user);
        return "saved_user";
    }

    @PostMapping("/user/{id}/delete")
    public String deleteUser(Model model, @PathVariable Long id) {
        Optional<User> optionalUser = users.findById(id);
        if (optionalUser.isPresent()) {
            users.deleteById(id);
            return "deleted_user";
        } else {
            return "user_not_found";
        }
    }

    public String userList(Model model) {
        model.addAttribute("users", users.findAll());
        return "userList";
    }

    @GetMapping("/profile/{id}")
    public String showUser(Model model, @PathVariable Long id) {
        Optional<User> optionalUser = users.findById(id);
        if (optionalUser.isPresent()) {
            model.addAttribute("user", optionalUser.get());
            System.out.println("Phone: " + optionalUser.get().getUserPhone()); // Para verificar que lombok funciona
            return "profile";
        } else {
            return "user_not_found";
        }
    }

}
