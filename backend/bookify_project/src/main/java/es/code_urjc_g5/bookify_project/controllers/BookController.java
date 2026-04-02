package es.code_urjc_g5.bookify_project.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.code_urjc_g5.bookify_project.models.Book;
import es.code_urjc_g5.bookify_project.repositories.BookRepository;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    public void init() {}

    public String bookList(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "bookList";
    }


    @PostMapping("/book/new")
    public String newBook(Model model, Book book) {
        bookRepository.save(book);
        return "saved_book";
    }

    @PostMapping("/editbook")
    public String editBook(Model model, Book editedBook) {
        Optional<Book> optionalBook = bookRepository.findById(editedBook.getId());
        if (optionalBook.isEmpty()) {
            return "book_not_found";
        }
        bookRepository.save(editedBook);
        model.addAttribute("book", editedBook);
        return "edit_book";
    }


    
    @PostMapping("/book/{id}/delete")
    public String deleteBook(Model model, @PathVariable Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            bookRepository.deleteById(id);
            return "deleted_book";
        } else {
            return "book_not_found";
        }
        
    }

    @GetMapping("/book/{id}")
    public String bookDescription(@PathVariable Long id, Model model) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isEmpty()) {
            Book book = bookRepository.findById(id).orElseThrow();
            model.addAttribute("book", book);
        return "bookDescription";
        } else {
            return "bookNotFound";
        }
        
    }
}