package com.example.books.controller;

import com.example.books.controller.model.BooksQueryResponse;
import com.example.books.controller.model.CreateBookElasticRequest;
import com.example.books.controller.model.CreateBookRequest;
import com.example.books.data.model.BookElasticSearch;
import com.example.books.service.BooksElasticSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BooksController {

    private final BooksElasticSearchService service;

    @GetMapping("/books")
    public ResponseEntity<BooksQueryResponse> getBooks(
            @RequestHeader Map<String, String> headers,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String publisher,
            @RequestParam(required = false) String language,
            @RequestParam(required = false, defaultValue = "false") Boolean aggregate) {

        log.info("headers: {}", headers);
        BooksQueryResponse books = service.BookSearch(title, author, genre, publisher, language, description, aggregate);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<BookElasticSearch> getBook(@PathVariable String bookId) {

        log.info("Request received for product {}", bookId);
        BookElasticSearch product = service.getBook(bookId);

        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable String bookId) {
        Boolean removed  = service.removeBook(bookId);
        if (Boolean.TRUE.equals(removed)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/books")
    public ResponseEntity<BookElasticSearch> createBook(@RequestBody CreateBookElasticRequest request) {

        BookElasticSearch createdBook = service.createBook(request);

        if (createdBook != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
