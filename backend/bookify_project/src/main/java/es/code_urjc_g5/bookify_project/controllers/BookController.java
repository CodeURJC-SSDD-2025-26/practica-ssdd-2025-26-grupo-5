package es.code_urjc_g5.bookify_project.controllers;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import es.code_urjc_g5.bookify_project.models.Book;
import es.code_urjc_g5.bookify_project.models.Review;
import es.code_urjc_g5.bookify_project.repositories.BookRepository;
import es.code_urjc_g5.bookify_project.repositories.ReviewRepository;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/book/{id}")
    public String bookDescription(@PathVariable Long id, Model model) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            model.addAttribute("book", book);
            model.addAttribute("reviews", reviewRepository.findByBook(book));
            return "bookDescription";
        }
        return "bookNotFound";
    }

    @PostMapping("/book/new")
    public String newBook(Book book) {
        bookRepository.save(book);
        return "redirect:/";
    }

    @PostMapping("/book/{id}/delete")
    public String deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/book/{id}/review")
    public String addReview(@PathVariable Long id,
                            @RequestParam String reviewText,
                            @RequestParam int reviewRating) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            Review review = new Review();
            review.setReviewText(reviewText);
            review.setReviewRating(reviewRating);
            review.setBook(book);
            reviewRepository.save(review);

            // Recalculate score and reviewCount from real reviews
            List<Review> allReviews = reviewRepository.findByBook(book);
            book.setReviewCount(allReviews.size());
            double avgScore = allReviews.stream()
                .mapToInt(Review::getReviewRating)
                .average()
                .orElse(0.0);
            book.setScore(Math.round(avgScore * 10.0) / 10.0);
            bookRepository.save(book);
        }
        return "redirect:/book/" + id;
    }
}