package es.code_urjc_g5.bookify_project.repositories;

import es.code_urjc_g5.bookify_project.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    
}