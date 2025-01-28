package ms.books.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BookDto {
    private String title;
    private String author;
    private String isbn;
    private String genre;
    private String publisher;
    private String publicationDate;
    private String language;
    private String format;
    private String numberOfPages;
    private String description;
    private String price;
    private String quantity;
    private String imageUrl;
}