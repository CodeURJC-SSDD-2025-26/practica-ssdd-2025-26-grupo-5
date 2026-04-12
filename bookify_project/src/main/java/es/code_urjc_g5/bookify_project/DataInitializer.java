package es.code_urjc_g5.bookify_project;

import es.code_urjc_g5.bookify_project.models.Book;
import es.code_urjc_g5.bookify_project.models.Collection;
import es.code_urjc_g5.bookify_project.models.Review;
import es.code_urjc_g5.bookify_project.models.User;
import es.code_urjc_g5.bookify_project.repositories.CollectionRepository;
import es.code_urjc_g5.bookify_project.repositories.UserRepository;
import es.code_urjc_g5.bookify_project.repositories.ReviewRepository;
import es.code_urjc_g5.bookify_project.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.core.io.ClassPathResource;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CollectionRepository collectionRepository;

    @Override
    public void run(String... args) {
        if (bookRepository.count() > 0)
            return;
        bookRepository.deleteAll(); // Borra todo aliniciar la aplicacion para evitar duplicados

        // Book builder para construir los modelos mediante notacion lombok
        Book book = Book.builder()
                .title("El Hobbit")
                .author("J.R.R. Tolkien")
                .genre("Fantasía")
                .synopsis(
                        "Escrito para los hijos de J.R.R. Tolkien, El Hobbit alcanzó un éxito inmediato cuando se publicó en 1937. Bilbo Bolsón, un hobbit que disfruta de su cómoda vida, se ve arrastrado a una aventura inesperada por el mago Gandalf y un grupo de trece enanos liderados por Thorin Escudo de Roble.\n"
                                +
                                "\n" +
                                "Su objetivo es recuperar el tesoro custodiado por el temible dragón Smaug en la Montaña Solitaria. A través de este viaje épico, Bilbo abandonará la seguridad de su hogar para enfrentarse a trolls, orcos y acertijos en la oscuridad. En las profundidades de la tierra, encontrará no solo un valor que no sabía que poseía, sino también un anillo mágico cuyo origen y poder cambiarán el destino de la Tierra Media para siempre.")
                .coverUrl(
                        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1546071216i/5907.jpg")
                .pages(310)
                .language("Español")
                .publicationYear(1937)
                .score(0)
                .reviewCount(0)
                .isbn(9780345445605L)
                .build();
        try {
            ClassPathResource pdfResource = new ClassPathResource("TheHobbit.pdf");
            byte[] pdfBytes = pdfResource.getInputStream().readAllBytes();
            book.setPdfFile(pdfBytes);
        } catch (Exception e) {
            System.out.println("No se pudo cargar el PDF: " + e.getMessage());
        }
        bookRepository.save(book);

        User user = new User();
        user.setUserName("admin");
        user.setUserPassword("admin");
        user.setUserEmail("admin@gmail.com");
        user.setUserBio("Administrador del sistema");
        user.setUserBooksInProgress(0);
        user.setUserBooksRead(0);
        user.setUserBooksWishlist(3);
        user.setUserLocation("madrid");
        user.setUserPhone("123456789");
        user.setUserProfilePic("https://cdn-icons-png.flaticon.com/512/149/149071.png");
        user.setUserRole("ADMIN");
        user.setUserBirthday("01/01/1990");
        user.setShortDesc("Soy admin");
        user.setUserFavGenres("Terror");
        user.setUserSince("2025");

        userRepository.save(user);
        // Segundo libro
        Book book2 = Book.builder()
                .title("El Señor de los Anillos")
                .author("J.R.R. Tolkien")
                .genre("Fantasía")
                .synopsis("Una historia épica sobre la lucha entre el bien y el mal en la Tierra Media.")
                .coverUrl(
                        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1566425108i/33.jpg")
                .pages(1178)
                .language("Español")
                .publicationYear(1954)
                .score(0)
                .reviewCount(0)
                .build();
        book2.setIsbn(9780261102385L);
        bookRepository.save(book2);

        // Tercer libro
        Book book3 = Book.builder()
                .title("Orgullo y Prejuicio")
                .author("Jane Austen")
                .genre("Romance")
                .synopsis(
                        "Una novela de romance y crítica social que sigue la historia de Elizabeth Bennet y su relación con el orgullo Mr. Darcy. A través del viaje de Elizabeth en busca del amor verdadero, Austen retrata la sociedad de la Regencia inglesa con ingenio y perspicacia. La novela explora temas de matrimonio, amor, amistad y la importancia de mirar más allá de las primeras impresiones para descubrir la verdadera naturaleza de las personas.")
                .coverUrl(
                        "https://imgs.search.brave.com/0qQv0rkSf0YCVhty2U32-J7JJk4ITKC7T6vpA_CuaEM/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9pbWFn/ZXMuY2RuMy5idXNj/YWxpYnJlLmNvbS9m/aXQtaW4vMzYweDM2/MC80OS82Yy80OTZj/YzJkMjYwNzBjNGYx/OWQ3ZjhlOTNhMDky/NzRhYy5qcGc")
                .pages(432)
                .language("Español")
                .publicationYear(1813)
                .score(0)
                .reviewCount(0)
                .isbn(9788489367968L)
                .build();
        bookRepository.save(book3);

        // Cuarto libro
        Book book4 = Book.builder()
                .title("El Código Da Vinci")
                .author("Dan Brown")
                .genre("Misterio/Thriller")
                .synopsis(
                        "Un enigma de arte, crimen e historia se desenvuelve ante Robert Langdon y Sophie Neveu cuando se ven envueltos en una conspiración que atraviesa siglos. Asesinado en el Museo del Louvre, un curador deja un mensaje críptico y un patrón oculto que lleva a Langdon y a Sophie en una búsqueda vertiginosa a través de Europa. Su investigación los lleva a cuestionar todo lo que sabían sobre el cristianismo, el arte y la historia mientras luchan por descubrir la verdad antes de ser silenciados.")
                .coverUrl(
                        "https://imgs.search.brave.com/fICO_tLodiucfbmgqgwRhnmlgnTM4IYzLDNeCuoJpYA/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9waWN0/dXJlcy5hYmVib29r/cy5jb20vaXNibi85/Nzg4NDk1NjE4ODE4/LWVzLTMwMC5qcGc")
                .pages(689)
                .language("Español")
                .publicationYear(2003)
                .score(0)
                .reviewCount(0)
                .isbn(9788408057765L)
                .build();
        bookRepository.save(book4);

        // Usuarios de prueba
        User user1 = new User();
        user1.setUserName("lector_infinito");
        user1.setUserEmail("lector@gmail.com");
        user1.setUserPassword("1234");
        user1.setUserRole("USER");
        userRepository.save(user1);

        User user2 = new User();
        user2.setUserName("carla_reads");
        user2.setUserEmail("carla@gmail.com");
        user2.setUserPhone("654321987");
        user2.setUserBirthday("14/08/1997");
        user2.setUserLocation("barcelona");
        user2.setUserFavGenres("Fantasía, Romance");
        user2.setUserBio("Lectora empedernida y amante de las sagas largas.");
        user2.setUserProfilePic("https://cdn-icons-png.flaticon.com/512/194/194935.png");
        user2.setUserPassword("1234");
        user2.setShortDesc("Siempre con un libro en la mochila");
        user2.setUserSince("2025");
        user2.setUserBooksRead(12);
        user2.setUserBooksInProgress(2);
        user2.setUserBooksWishlist(8);
        user2.setUserRole("USER");
        userRepository.save(user2);

        // Reseñas de prueba
        Review review1 = new Review();
        review1.setReviewText("Una obra maestra de la literatura fantástica.");
        review1.setReviewRating(9);
        review1.setBook(book);
        review1.setUser(user1);
        reviewRepository.save(review1);

        Review review2 = new Review();
        review2.setReviewText("Lectura obligatoria para cualquier amante de la fantasía.");
        review2.setReviewRating(10);
        review2.setBook(book2);
        review2.setUser(user2);
        reviewRepository.save(review2);

        // Colecciones de prueba
        Collection coleccion1 = new Collection();
        coleccion1.setCollectionName("Mis Favoritas");
        coleccion1.setUser(user1);
        coleccion1.getBooks().add(book);
        coleccion1.getBooks().add(book2);
        collectionRepository.save(coleccion1);

        Collection coleccion2 = new Collection();
        coleccion2.setCollectionName("Lista de Deseos");
        coleccion2.setUser(user2);
        coleccion2.getBooks().add(book2);
        collectionRepository.save(coleccion2);

        Collection coleccion3 = new Collection();
        coleccion3.setCollectionName("Tolkien Universe");
        coleccion3.setUser(user);
        coleccion3.getBooks().add(book);
        coleccion3.getBooks().add(book2);
        collectionRepository.save(coleccion3);

        Collection coleccion4 = new Collection();
        coleccion4.setCollectionName("Mis Favoritos");
        coleccion4.setUser(user);
        coleccion4.getBooks().add(book3);
        coleccion4.getBooks().add(book4);
        collectionRepository.save(coleccion4);
    }
}
