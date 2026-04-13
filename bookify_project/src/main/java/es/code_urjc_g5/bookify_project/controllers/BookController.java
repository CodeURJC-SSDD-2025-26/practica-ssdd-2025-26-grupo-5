package es.code_urjc_g5.bookify_project.controllers;

import java.util.Optional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import es.code_urjc_g5.bookify_project.models.Book;
import es.code_urjc_g5.bookify_project.models.Review;
import es.code_urjc_g5.bookify_project.models.User;
import es.code_urjc_g5.bookify_project.repositories.BookRepository;
import es.code_urjc_g5.bookify_project.repositories.CollectionRepository;
import es.code_urjc_g5.bookify_project.repositories.ReviewRepository;
import es.code_urjc_g5.bookify_project.services.UserService;
import es.code_urjc_g5.bookify_project.models.Collection;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private CollectionRepository collectionRepository;

    @GetMapping("/book/{id}")
    public String bookDescription(@PathVariable Long id, Model model, Authentication authentication) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            List<Review> reviews = reviewRepository.findByBook(book);

            String currentEmail = (authentication != null && authentication.isAuthenticated())
                    ? authentication.getName() : null;
            boolean isAdmin = currentEmail != null && authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

            List<Map<String, Object>> reviewsWithFlags = reviews.stream().map(r -> {
                Map<String, Object> map = new HashMap<>();
                map.put("id", r.getId());
                map.put("reviewText", r.getReviewText());
                map.put("reviewRating", r.getReviewRating());
                map.put("user", r.getUser());
                boolean isOwner = r.getUser() != null && r.getUser().getUserEmail().equals(currentEmail);
                map.put("canDelete", isAdmin || isOwner);
                return map;
            }).toList();

            model.addAttribute("book", book);
            model.addAttribute("reviews", reviewsWithFlags);

            // Shows collections only if user is logged in
            if (authentication != null && authentication.isAuthenticated() 
            && !(authentication instanceof org.springframework.security.authentication.AnonymousAuthenticationToken)) {
                Optional<User> userLoggedIn = userService.findByEmail(authentication.getName());
                if (userLoggedIn.isPresent()) {
                    User user = userLoggedIn.get();
                    model.addAttribute("userCollections", collectionRepository.findByUser(user));
                    model.addAttribute("loggedIn", true);
                    model.addAttribute("currentUserEmail", user.getUserEmail());
                }
            }

            int[] distribution = new int[10];
            for (Review r : reviews) {
                int rating = r.getReviewRating();
                if (rating >= 1 && rating <= 10) {
                    distribution[rating - 1]++;
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < distribution.length; i++) {
                sb.append(distribution[i]);
                if (i < distribution.length - 1)
                    sb.append(",");
            }
            model.addAttribute("reviewsDistribution", sb.toString());
            return "bookDescription";
        }
        return "bookNotFound";
    }

    @GetMapping("/book/{id}/pdf")
    public ResponseEntity<byte[]> downloadBookPdf(@PathVariable Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isPresent() && optionalBook.get().getPdfFile() != null) {
            Book book = optionalBook.get();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "application/pdf")
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + book.getTitle().replace(" ", "_") + ".pdf\"")
                    .body(book.getPdfFile());
        }

        return ResponseEntity.notFound().build();
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
            @RequestParam int reviewRating,
            Authentication authentication) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            Review review = new Review();
            review.setReviewText(reviewText);
            review.setReviewRating(reviewRating);
            review.setBook(book);
            if (authentication != null && authentication.isAuthenticated()) {
                userService.findByEmail(authentication.getName())
                        .ifPresent(review::setUser);
        }
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

    @PostMapping("/review/{reviewId}/delete")
    public String deleteReview(@PathVariable Long reviewId, Authentication authentication) {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        if (optionalReview.isEmpty()) {
            return "redirect:/";
        }
        Review review = optionalReview.get();
        Long bookId = review.getBook().getId();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        boolean isOwner = review.getUser() != null &&
                review.getUser().getUserEmail().equals(authentication.getName());

        if (!isAdmin && !isOwner) {
            return "redirect:/book/" + bookId + "?error=forbidden";
        }

        reviewRepository.delete(review);

        Book book = review.getBook();
        List<Review> remaining = reviewRepository.findByBook(book);
        book.setReviewCount(remaining.size());
        double avg = remaining.stream().mapToInt(Review::getReviewRating).average().orElse(0.0);
        book.setScore(Math.round(avg * 10.0) / 10.0);
        bookRepository.save(book);

        return "redirect:/book/" + bookId;
    }

    @PostMapping("/collection/{collectionId}/addBook/{bookId}")
    public String addBookToCollection(@PathVariable Long collectionId, @PathVariable Long bookId,
            Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            Optional<User> userLoggedIn = userService.findByEmail(authentication.getName());
            if (userLoggedIn.isPresent()) {
                User user = userLoggedIn.get();
                Optional<Collection> optionalCollection = collectionRepository.findById(collectionId);
                Optional<Book> optionalBook = bookRepository.findById(bookId);
                if (optionalCollection.isPresent() && optionalBook.isPresent()) {
                    Collection collection = optionalCollection.get();
                    Book book = optionalBook.get();
                    if (!collection.getBooks().contains(book)) {
                        collection.getBooks().add(book);
                        collectionRepository.save(collection);
                    }
                }
            }
        }
        return "redirect:/book/" + bookId; // Redirect to the book page after adding to collection
    }

}