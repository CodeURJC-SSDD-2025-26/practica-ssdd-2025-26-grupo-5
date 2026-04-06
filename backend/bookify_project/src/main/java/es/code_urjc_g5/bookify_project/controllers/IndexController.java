package es.code_urjc_g5.bookify_project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.code_urjc_g5.bookify_project.repositories.BookRepository;

@Controller
public class IndexController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("topBooks", bookRepository.findTop10ByOrderByReviewCountDesc());
        return "index";
    }
}
