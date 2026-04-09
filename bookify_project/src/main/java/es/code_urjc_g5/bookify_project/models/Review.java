package es.code_urjc_g5.bookify_project.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String reviewText;
    private int reviewRating;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Book book;

    private String status = "PENDIENTE";

    public boolean isAcepted() { return "ACEPTADO".equals(status); }
    public boolean isPendiente() { return "PENDIENTE".equals(status); }
    public boolean isReportado() { return "REPORTADO".equals(status); }
}
