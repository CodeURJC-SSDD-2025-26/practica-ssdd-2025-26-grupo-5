package es.code_urjc_g5.bookify_project;

import es.code_urjc_g5.bookify_project.models.Book;
import es.code_urjc_g5.bookify_project.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void run(String... args) {
        Book book = new Book();
        book.setTitle("El Hobbit");
        book.setAuthor("J.R.R. Tolkien");
        book.setGenre("Fantasía");
        book.setSynopsis("Escrito para los hijos de J.R.R. Tolkien, El Hobbit alcanzó un éxito inmediato cuando se publicó en 1937. Bilbo Bolsón, un hobbit que disfruta de su cómoda vida, se ve arrastrado a una aventura inesperada por el mago Gandalf y un grupo de trece enanos liderados por Thorin Escudo de Roble.\n" + 
                        "\n" + 
                        "Su objetivo es recuperar el tesoro custodiado por el temible dragón Smaug en la Montaña Solitaria. A través de este viaje épico, Bilbo abandonará la seguridad de su hogar para enfrentarse a trolls, orcos y acertijos en la oscuridad. En las profundidades de la tierra, encontrará no solo un valor que no sabía que poseía, sino también un anillo mágico cuyo origen y poder cambiarán el destino de la Tierra Media para siempre.");
        book.setCoverUrl("https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1546071216i/5907.jpg");
        book.setPages(310);
        book.setLanguage("Español");
        book.setPublicationYear(1937);
        book.setScore(8.2);
        book.setReviewCount(1240);

        bookRepository.save(book);
    }
}
