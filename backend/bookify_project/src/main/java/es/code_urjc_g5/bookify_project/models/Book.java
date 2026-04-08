package es.code_urjc_g5.bookify_project.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
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
    private String coverUrl;
    private int pages;
    private String language;
    private int publicationYear;
    private double score;
    private int reviewCount;

    // Getters y Setters
    
}