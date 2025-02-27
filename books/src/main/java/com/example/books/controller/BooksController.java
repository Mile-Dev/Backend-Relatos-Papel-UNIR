package com.example.books.controller;

import com.example.books.controller.model.BooksQueryResponse;
import com.example.books.controller.model.CreateBookElasticRequest;
import com.example.books.data.model.BookElasticSearch;
import com.example.books.controller.model.Review;  // <-- Importar aquí
import com.example.books.service.BooksElasticSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException; // si lo usas
import java.util.List; // <-- Importar también
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/books")
public class BooksController {

    private final BooksElasticSearchService service;

    /**
     * 🔍 Obtiene una lista de libros según los filtros aplicados.
     */
    @GetMapping
    public ResponseEntity<BooksQueryResponse> getBooks(
            @RequestHeader Map<String, String> headers,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String subcategory,
            @RequestParam(required = false, defaultValue = "false") Boolean aggregate) {

        BooksQueryResponse books = service.BookSearch(title, author, category, subcategory, description, aggregate);
        return ResponseEntity.ok(books);
    }

    /**
     * 📖 Obtiene un libro por su ID.
     */
    @GetMapping("/{bookId}")
    public ResponseEntity<BookElasticSearch> getBook(@PathVariable String bookId) {
        BookElasticSearch book = service.getBook(bookId);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * ❌ Elimina un libro por su ID.
     */
    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable String bookId) {
        Boolean removed = service.removeBook(bookId);
        if (Boolean.TRUE.equals(removed)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * ➕ Crea un nuevo libro en la base de datos Elasticsearch.
     */
    @PostMapping
    public ResponseEntity<BookElasticSearch> createBook(@RequestBody CreateBookElasticRequest request) {
        // Validaciones antes de enviar la petición
        if (request.getTitle() == null || request.getTitle().trim().isEmpty() ||
                request.getAuthor() == null || request.getAuthor().trim().isEmpty() ||
                request.getCategory() == null || request.getCategory().trim().isEmpty() ||
                request.getPrice() == null || request.getPrice() <= 0) {
            return ResponseEntity.badRequest().build();
        }

        BookElasticSearch createdBook = service.createBook(request);
        if (createdBook != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * ✏️ PATCH para actualizar **solo** la lista de reseñas de un libro.
     */
    @PatchMapping("/{id}/reviews")
    public ResponseEntity<BookElasticSearch> updateBookReviews(
            @PathVariable String id,
            @RequestBody List<Review> updatedReviews
    ) {
        BookElasticSearch savedBook = service.updateBookReviews(id, updatedReviews);
        if (savedBook == null) {
            // Libro no encontrado
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(savedBook);
    }
}
