package es.code_urjc_g5.bookify_project;

import es.code_urjc_g5.bookify_project.models.Book;
import es.code_urjc_g5.bookify_project.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.core.io.ClassPathResource;
import es.code_urjc_g5.bookify_project.models.User;
import es.code_urjc_g5.bookify_project.repositories.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

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

    }
}
