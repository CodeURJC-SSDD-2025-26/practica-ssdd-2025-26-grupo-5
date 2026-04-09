package es.code_urjc_g5.bookify_project.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import es.code_urjc_g5.bookify_project.models.Review;
import es.code_urjc_g5.bookify_project.models.Book;
import es.code_urjc_g5.bookify_project.models.User;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByBook(Book book);
    List<Review> findByUser(User user);
}