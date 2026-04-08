package es.code_urjc_g5.bookify_project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import es.code_urjc_g5.bookify_project.models.Book;
import es.code_urjc_g5.bookify_project.repositories.BookRepository;
import es.code_urjc_g5.bookify_project.repositories.ReviewRepository;
import es.code_urjc_g5.bookify_project.repositories.UserRepository;

@Controller
public class AdminController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("reviews", reviewRepository.findAll());
        return "admin";
    }

    @PostMapping("/admin/book/new")
    public String newBook(@RequestParam String title,
                          @RequestParam String author,
                          @RequestParam String genre,
                          @RequestParam String coverUrl,
                          @RequestParam int pages,
                          @RequestParam String language,
                          @RequestParam int publicationYear,
                          @RequestParam String synopsis) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setCoverUrl(coverUrl);
        book.setPages(pages);
        book.setLanguage(language);
        book.setPublicationYear(publicationYear);
        book.setSynopsis(synopsis);
        book.setScore(0.0);
        book.setReviewCount(0);
        bookRepository.save(book);
        return "redirect:/admin";
    }

    @PostMapping("/admin/book/{id}/delete")
    public String deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/user/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/review/{id}/delete")
    public String deleteReview(@PathVariable Long id) {
        reviewRepository.deleteById(id);
        return "redirect:/admin";
    }
}