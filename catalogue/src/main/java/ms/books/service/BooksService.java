package ms.books.service;

import java.util.List;

import ms.books.data.model.Book;
import ms.books.controller.model.BookDto;
import ms.books.controller.model.CreateBookRequest;

public interface BooksService {

    List<Book> getBooks(String title, String author, String genre, String publisher, Integer publicationYear, String isbn, BigDecimal price, Integer stock, LocalDateTime createdAt, LocalDateTime updatedAt);

    Book getBook(String bookId);

    Boolean removeBook(String bookId);

    Book createBook(CreateBookRequest request);

    Book updateBook(String bookId, String updateRequest);

    Book updateBook(String bookId, BookDto updateRequest);

}