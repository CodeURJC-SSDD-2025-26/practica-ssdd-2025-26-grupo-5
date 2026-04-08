package es.code_urjc_g5.bookify_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.code_urjc_g5.bookify_project.models.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {


}

