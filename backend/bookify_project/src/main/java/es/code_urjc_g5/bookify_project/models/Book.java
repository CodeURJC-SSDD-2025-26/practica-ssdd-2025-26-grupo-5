package es.code_urjc_g5.bookify_project.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
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