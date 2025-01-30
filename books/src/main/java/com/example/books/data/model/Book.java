package com.example.books.data.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookID;

    @Column(name = "Title", nullable = false, length = 255)
    private String title;

    @Column(name = "Author", nullable = false, length = 255)
    private String author;

    @Column(name = "Genre", length = 100)
    private String genre;

    @Column(name = "Publisher", length = 255)
    private String publisher;

    @Column(name = "PublicationYear")
    private String publicationYear;

    @Column(name = "ISBN", unique = true, length = 20)
    private String isbn;

    @Column(name = "Price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "Stock", nullable = false)
    private Integer stock;

    @Column(name = "Rating", nullable = false)
    private Integer rating;

    @Column(name = "Status", nullable = false)
    private Integer status;

    @Column(name = "CreatedAt", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "UpdatedAt")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
