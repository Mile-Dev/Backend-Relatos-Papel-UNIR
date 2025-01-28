package ms.books.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {

    private String title;
    private String author;
    private String genre;
    private String publisher;
    private Integer publicationYear;
    private String isbn;
    private Double price;
    private Integer stock;

}