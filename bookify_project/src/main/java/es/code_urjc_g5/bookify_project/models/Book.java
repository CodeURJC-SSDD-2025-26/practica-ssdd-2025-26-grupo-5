package es.code_urjc_g5.bookify_project.models;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@Table(name = "books")
@Builder //Para usar el patrón builder en la clase Book, lo que facilita la creación de objetos Book con una sintaxis más fluida y legible. Esto es especialmente útil cuando se tienen muchos atributos, ya que permite establecer solo los necesarios sin tener que usar un constructor con muchos parámetros.
@NoArgsConstructor
@AllArgsConstructor //Necesario para usar el patrón builder, ya que proporciona un constructor con todos los argumentos que el builder necesita para crear una instancia de Book.
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String author;
    private String genre;
    @Column(columnDefinition = "TEXT")
    private String synopsis;
    private Long isbn;
    private String coverUrl;
    private int pages;
    private String language;
    private int publicationYear;
    private double score;
    private int reviewCount;
    @jakarta.persistence.Lob
    @jakarta.persistence.Column(columnDefinition = "LONGBLOB")
    private byte[] pdfFile;  
    
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;
}