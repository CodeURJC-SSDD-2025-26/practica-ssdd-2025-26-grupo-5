package es.code_urjc_g5.bookify_project.models;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
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
    private String userSince;
    
    private int userBooksRead;
    private int userBooksInProgress;
    private int userBooksWishlist;

    private String userRole; // "USER" o "ADMIN"
    // Getters y Setters
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Collection> collections;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

    public User(Long id, String userName, String userEmail, String userPhone, String userBirthday, String userLocation,
            String userFavGenres, String userBio, String userProfilePic, String userPassword, String shortDesc,
            String userSince, int userBooksRead, int userBooksInProgress, int userBooksWishlist, String userRole,
            List<Collection> collections, List<Review> reviews) {
        this.id = id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userBirthday = userBirthday;
        this.userLocation = userLocation;
        this.userFavGenres = userFavGenres;
        this.userBio = userBio;
        this.userProfilePic = userProfilePic;
        this.userPassword = userPassword;
        this.shortDesc = shortDesc;
        this.userSince = userSince;
        this.userBooksRead = userBooksRead;
        this.userBooksInProgress = userBooksInProgress;
        this.userBooksWishlist = userBooksWishlist;
        this.userRole = userRole;
        this.collections = collections;
        this.reviews = reviews;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public String getUserFavGenres() {
        return userFavGenres;
    }

    public void setUserFavGenres(String userFavGenres) {
        this.userFavGenres = userFavGenres;
    }

    public String getUserBio() {
        return userBio;
    }

    public void setUserBio(String userBio) {
        this.userBio = userBio;
    }

    public String getUserProfilePic() {
        return userProfilePic;
    }

    public void setUserProfilePic(String userProfilePic) {
        this.userProfilePic = userProfilePic;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getUserSince() {
        return userSince;
    }

    public void setUserSince(String userSince) {
        this.userSince = userSince;
    }

    public int getUserBooksRead() {
        return userBooksRead;
    }

    public void setUserBooksRead(int userBooksRead) {
        this.userBooksRead = userBooksRead;
    }

    public int getUserBooksInProgress() {
        return userBooksInProgress;
    }

    public void setUserBooksInProgress(int userBooksInProgress) {
        this.userBooksInProgress = userBooksInProgress;
    }

    public int getUserBooksWishlist() {
        return userBooksWishlist;
    }

    public void setUserBooksWishlist(int userBooksWishlist) {
        this.userBooksWishlist = userBooksWishlist;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public List<Collection> getCollections() {
        return collections;
    }

    public void setCollections(List<Collection> collections) {
        this.collections = collections;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", userName=" + userName + ", userEmail=" + userEmail + ", userPhone=" + userPhone
                + ", userBirthday=" + userBirthday + ", userLocation=" + userLocation + ", userFavGenres="
                + userFavGenres + ", userBio=" + userBio + ", userProfilePic=" + userProfilePic + ", userPassword="
                + userPassword + ", shortDesc=" + shortDesc + ", userSince=" + userSince + ", userBooksRead="
                + userBooksRead + ", userBooksInProgress=" + userBooksInProgress + ", userBooksWishlist="
                + userBooksWishlist + ", userRole=" + userRole + ", collections=" + collections + ", reviews=" + reviews
                + ", getCollections()=" + getCollections() + ", getId()=" + getId() + ", getReviews()=" + getReviews()
                + ", getShortDesc()=" + getShortDesc() + ", getUserBio()=" + getUserBio() + ", getUserBirthday()="
                + getUserBirthday() + ", getUserBooksInProgress()=" + getUserBooksInProgress() + ", getUserBooksRead()="
                + getUserBooksRead() + ", getUserBooksWishlist()=" + getUserBooksWishlist() + ", getUserEmail()="
                + getUserEmail() + ", getUserFavGenres()=" + getUserFavGenres() + ", getUserLocation()="
                + getUserLocation() + ", getUserName()=" + getUserName() + ", getUserPassword()=" + getUserPassword()
                + ", getUserPhone()=" + getUserPhone() + ", getUserProfilePic()=" + getUserProfilePic()
                + ", getUserRole()=" + getUserRole() + ", getUserSince()=" + getUserSince() + ", hashCode()="
                + hashCode() + ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        if (userEmail == null) {
            if (other.userEmail != null)
                return false;
        } else if (!userEmail.equals(other.userEmail))
            return false;
        if (userPhone == null) {
            if (other.userPhone != null)
                return false;
        } else if (!userPhone.equals(other.userPhone))
            return false;
        if (userBirthday == null) {
            if (other.userBirthday != null)
                return false;
        } else if (!userBirthday.equals(other.userBirthday))
            return false;
        if (userLocation == null) {
            if (other.userLocation != null)
                return false;
        } else if (!userLocation.equals(other.userLocation))
            return false;
        if (userFavGenres == null) {
            if (other.userFavGenres != null)
                return false;
        } else if (!userFavGenres.equals(other.userFavGenres))
            return false;
        if (userBio == null) {
            if (other.userBio != null)
                return false;
        } else if (!userBio.equals(other.userBio))
            return false;
        if (userProfilePic == null) {
            if (other.userProfilePic != null)
                return false;
        } else if (!userProfilePic.equals(other.userProfilePic))
            return false;
        if (userPassword == null) {
            if (other.userPassword != null)
                return false;
        } else if (!userPassword.equals(other.userPassword))
            return false;
        if (shortDesc == null) {
            if (other.shortDesc != null)
                return false;
        } else if (!shortDesc.equals(other.shortDesc))
            return false;
        if (userSince == null) {
            if (other.userSince != null)
                return false;
        } else if (!userSince.equals(other.userSince))
            return false;
        if (userBooksRead != other.userBooksRead)
            return false;
        if (userBooksInProgress != other.userBooksInProgress)
            return false;
        if (userBooksWishlist != other.userBooksWishlist)
            return false;
        if (userRole == null) {
            if (other.userRole != null)
                return false;
        } else if (!userRole.equals(other.userRole))
            return false;
        if (collections == null) {
            if (other.collections != null)
                return false;
        } else if (!collections.equals(other.collections))
            return false;
        if (reviews == null) {
            if (other.reviews != null)
                return false;
        } else if (!reviews.equals(other.reviews))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        result = prime * result + ((userEmail == null) ? 0 : userEmail.hashCode());
        result = prime * result + ((userPhone == null) ? 0 : userPhone.hashCode());
        result = prime * result + ((userBirthday == null) ? 0 : userBirthday.hashCode());
        result = prime * result + ((userLocation == null) ? 0 : userLocation.hashCode());
        result = prime * result + ((userFavGenres == null) ? 0 : userFavGenres.hashCode());
        result = prime * result + ((userBio == null) ? 0 : userBio.hashCode());
        result = prime * result + ((userProfilePic == null) ? 0 : userProfilePic.hashCode());
        result = prime * result + ((userPassword == null) ? 0 : userPassword.hashCode());
        result = prime * result + ((shortDesc == null) ? 0 : shortDesc.hashCode());
        result = prime * result + ((userSince == null) ? 0 : userSince.hashCode());
        result = prime * result + userBooksRead;
        result = prime * result + userBooksInProgress;
        result = prime * result + userBooksWishlist;
        result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());
        result = prime * result + ((collections == null) ? 0 : collections.hashCode());
        result = prime * result + ((reviews == null) ? 0 : reviews.hashCode());
        return result;
    }

    
}
