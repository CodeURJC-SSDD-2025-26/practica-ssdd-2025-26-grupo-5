package es.code_urjc_g5.bookify_project.models;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter 
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String userName;
    private String userEmail;
    private String userPhone;
    private String userBirthday;
    private String userLocation;
    private String userFavGenres;
    private String userBio;
    private String userProfilePic;
    private String userPassword;
    private String shortDesc;

    private int userBooksRead;
    private int userBooksInProgress;
    private int userBooksWishlist;

    private String user_role;
    // Getters y Setters
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Collection> collections;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    
}
