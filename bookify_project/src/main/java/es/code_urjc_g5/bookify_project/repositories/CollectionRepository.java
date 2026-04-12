package es.code_urjc_g5.bookify_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.code_urjc_g5.bookify_project.models.Collection;
import es.code_urjc_g5.bookify_project.models.User;
import java.util.List;

public interface CollectionRepository extends JpaRepository<Collection, Long> {

    List<Collection> findByUser(User user);
}
