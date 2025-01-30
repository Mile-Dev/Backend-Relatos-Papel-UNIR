package com.example.books.controller;

import com.example.books.controller.model.BookDto;
import com.example.books.controller.model.CreateBookRequest;
import com.example.books.data.model.Book;
import com.example.books.service.BooksService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Slf4j
public class BooksController {

    private final BooksService booksService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String publisher,
            @RequestParam(required = false) String publicationYear,
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) Integer rating) {

        log.info("Obteniendo lista de libros con filtros");
        List<Book> books = booksService.getBooks(title, author, genre, publisher, publicationYear, isbn, null, null, null);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable String id) {
        log.info("Buscando libro con ID: {}", id);
        Book book = booksService.getBook(Integer.valueOf(id));
        return book != null ? ResponseEntity.ok(book) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody CreateBookRequest request) {
        log.info("Creando nuevo libro: {}", request.getTitle());
        Book newBook = booksService.createBook(request);
        return ResponseEntity.ok(newBook);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable String bookId, @RequestBody BookDto updateRequest) {
        log.info("Actualizando libro con ID: {}", bookId);
        Book updatedBook = booksService.updateBook(Integer.valueOf(bookId), updateRequest);
        return updatedBook != null ? ResponseEntity.ok(updatedBook) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        log.info("Eliminando libro con ID: {}", id);
        Boolean deleted = booksService.removeBook(Integer.valueOf(id));
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String publisher,
            @RequestParam(required = false) String publicationYear,
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) BigDecimal price,
            @RequestParam(required = false) Integer stock,
            @RequestParam(required = false) Integer rating) {

        Map<String, Object> filters = new HashMap<>();
        if (title != null) filters.put("title", title);
        if (author != null) filters.put("author", author);
        if (genre != null) filters.put("genre", genre);
        if (publisher != null) filters.put("publisher", publisher);
        if (publicationYear != null) filters.put("publicationYear", publicationYear);
        if (isbn != null) filters.put("isbn", isbn);
        if (price != null) filters.put("price", price);
        if (stock != null) filters.put("stock", stock);
        if (rating != null) filters.put("rating", rating);

        List<Book> books = booksService.getBooksFilters(filters);
        return ResponseEntity.ok(books);
    }

}
