package es.code_urjc_g5.bookify_project.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.code_urjc_g5.bookify_project.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE LOWER(u.user_email) = LOWER(:email)")
    Optional<User> findByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE LOWER(u.user_name) = LOWER(:userName)")
    Optional<User> findByUsername(@Param("userName") String userName);

    @Query("SELECT u FROM User u WHERE LOWER(u.user_name) LIKE LOWER(CONCAT('%', :searchText, '%')) OR LOWER(u.user_email) LIKE LOWER(CONCAT('%', :searchText, '%')) ORDER BY u.user_name ASC")
    List<User> searchUsers(@Param("searchText") String searchText);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE LOWER(u.user_email) = LOWER(:email)")
    boolean existsByEmail(@Param("email") String email);
}
