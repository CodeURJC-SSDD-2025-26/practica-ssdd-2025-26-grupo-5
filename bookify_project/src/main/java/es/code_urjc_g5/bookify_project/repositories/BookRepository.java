package es.code_urjc_g5.bookify_project.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.code_urjc_g5.bookify_project.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByAuthorContainingIgnoreCase(String author);

    List<Book> findByGenreIgnoreCase(String genre);

    Optional<Book> findByTitleIgnoreCaseAndAuthorIgnoreCase(String title, String author);

    List<Book> findTop10ByOrderByScoreDesc();

    List<Book> findTop10ByOrderByReviewCountDesc();
}