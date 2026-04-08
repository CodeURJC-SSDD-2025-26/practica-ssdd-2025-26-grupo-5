package es.code_urjc_g5.bookify_project.controllers;

import es.code_urjc_g5.bookify_project.models.Book;
import es.code_urjc_g5.bookify_project.repositories.BookRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class myLibraryController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/myLibrary")
    public String myLibrary(Model model) {

        List<Book> books = bookRepository.findAll();

        model.addAttribute("Books", books);
        model.addAttribute("bookCount", books.size());

        return "myLibrary";
    }
}
