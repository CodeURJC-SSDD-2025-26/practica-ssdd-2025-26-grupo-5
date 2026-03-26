package es.code_urjc_g5.bookify_project.models;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String user_name;
    private String user_email;
    private String user_phone;
    private String user_birthday;
    private String user_location;
    private String user_fav_genres;
    private String user_bio;
    private String user_profile_pic;
    private String user_password;
    private String short_desc;

    private int user_books_read;
    private int user_books_in_progress;
    private int user_books_wishlist;

    private String user_role;
    // Getters y Setters
    
    public Long getId() {
        return id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public String getUser_birthday() {
        return user_birthday;
    }

    public String getUser_location() {
        return user_location;
    }

    public String getUser_fav_genres() {
        return user_fav_genres;
    }

    public String getUser_bio() {
        return user_bio;
    }

    public String getUser_profile_pic() {
        return user_profile_pic;
    }

    public String getUser_password() {
        return user_password;
    }

    public String getShort_desc() {
        return short_desc;
    }

    public int getUser_books_read() {
        return user_books_read;
    }

    public int getUser_books_in_progress() {
        return user_books_in_progress;
    }

    public int getUser_books_wishlist() {
        return user_books_wishlist;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public void setUser_birthday(String user_birthday) {
        this.user_birthday = user_birthday;
    }

    public void setUser_location(String user_location) {
        this.user_location = user_location;
    }

    public void setUser_fav_genres(String user_fav_genres) {
        this.user_fav_genres = user_fav_genres;
    }

    public void setUser_bio(String user_bio) {
        this.user_bio = user_bio;
    }

    public void setUser_profile_pic(String user_profile_pic) {
        this.user_profile_pic = user_profile_pic;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public void setShort_desc(String short_desc) {
        this.short_desc = short_desc;
    }

    public void setUser_books_read(int user_books_read) {
        this.user_books_read = user_books_read;
    }

    public void setUser_books_in_progress(int user_books_in_progress) {
        this.user_books_in_progress = user_books_in_progress;
    }

    public void setUser_books_wishlist(int user_books_wishlist) {
        this.user_books_wishlist = user_books_wishlist;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    
}
