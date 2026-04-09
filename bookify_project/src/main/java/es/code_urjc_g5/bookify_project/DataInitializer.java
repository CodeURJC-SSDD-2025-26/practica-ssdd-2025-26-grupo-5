package es.code_urjc_g5.bookify_project;

import es.code_urjc_g5.bookify_project.models.Book;
import es.code_urjc_g5.bookify_project.models.Review;
import es.code_urjc_g5.bookify_project.models.User;
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

    @Override
    public void run(String... args) {
        if (bookRepository.count() > 0) return;
        bookRepository.deleteAll(); //Borra todo aliniciar la aplicacion para evitar duplicados
        
        //Book builder para construir los modelos mediante notacion lombok
        Book book = Book.builder()
                .title("El Hobbit")
                .author("J.R.R. Tolkien")
                .genre("Fantasía")
                .synopsis("Escrito para los hijos de J.R.R. Tolkien, El Hobbit alcanzó un éxito inmediato cuando se publicó en 1937. Bilbo Bolsón, un hobbit que disfruta de su cómoda vida, se ve arrastrado a una aventura inesperada por el mago Gandalf y un grupo de trece enanos liderados por Thorin Escudo de Roble.\n" + 
                        "\n" + 
                        "Su objetivo es recuperar el tesoro custodiado por el temible dragón Smaug en la Montaña Solitaria. A través de este viaje épico, Bilbo abandonará la seguridad de su hogar para enfrentarse a trolls, orcos y acertijos en la oscuridad. En las profundidades de la tierra, encontrará no solo un valor que no sabía que poseía, sino también un anillo mágico cuyo origen y poder cambiarán el destino de la Tierra Media para siempre.")
                .coverUrl("https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1546071216i/5907.jpg")
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

        // Segundo libro
        Book book2 = Book.builder()
                .title("El Señor de los Anillos")
                .author("J.R.R. Tolkien")
                .genre("Fantasía")
                .synopsis("Una historia épica sobre la lucha entre el bien y el mal en la Tierra Media.")
                .coverUrl("https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1566425108i/33.jpg")
                .pages(1178)
                .language("Español")
                .publicationYear(1954)
                .score(0)
                .reviewCount(0)
                .build();
        book2.setIsbn(9780261102385L);
        bookRepository.save(book2);

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
        user2.setUserPassword("1234");
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
    }
}
