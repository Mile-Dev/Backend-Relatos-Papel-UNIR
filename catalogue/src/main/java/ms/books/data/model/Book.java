package ms.books.catalogue.data.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString

@Entity
@Table(name = "Books")
@Getter
@Setter

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BookID")
    private Long bookId;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "Author", nullable = false)
    private String author;

    @Column(name = "Genre")
    private String genre;

    @Column(name = "Publisher")
    private String publisher;

    @Column(name = "PublicationYear")
    private Integer publicationYear;

    @Column(name = "ISBN", unique = true)
    private String isbn;

    @Column(name = "Price", nullable = false)
    private BigDecimal price;

    @Column(name = "Stock", nullable = false, columnDefinition = "int default 0")
    private Integer stock;

    @Column(name = "CreatedAt", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "UpdatedAt")
    private LocalDateTime updatedAt;

    // Constructors
    public Book() {
    }

    public Book(String title, String author, String genre, String publisher, Integer publicationYear, String isbn, BigDecimal price, Integer stock) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.price = price;
        this.stock = stock;
    }
}
