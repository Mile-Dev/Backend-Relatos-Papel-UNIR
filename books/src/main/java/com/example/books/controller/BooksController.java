package com.example.books.controller;

import com.example.books.controller.model.BookDto;
import com.example.books.controller.model.CreateBookRequest;
import com.example.books.data.model.Book;
import com.example.books.service.BooksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Slf4j
public class BooksController {

    private final BooksService booksService;

    @GetMapping
    public ResponseEntity<?> getAllBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String publisher,
            @RequestParam(required = false) String publicationYear,
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) Integer rating) {
        try {
            log.info("Obteniendo lista de libros con filtros");
            List<Book> books = booksService.getBooks(title, author, genre, publisher, publicationYear, isbn, null, null, rating);
            return ResponseEntity.ok(books);
        } catch (Exception e) {
            log.error("Error al obtener libros: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor al obtener los libros.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable String id) {
        try {
            log.info("Buscando libro con ID: {}", id);
            Book book = booksService.getBook(Integer.parseInt(id));
            return book != null ? ResponseEntity.ok(book) :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("Libro no encontrado con ID: " + id);
        } catch (NumberFormatException e) {
            log.error("Formato de ID inválido: {}", id, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El ID debe ser un número válido.");
        } catch (Exception e) {
            log.error("Error al obtener el libro: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor.");
        }
    }

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody CreateBookRequest request) {
        try {
            log.info("Creando nuevo libro: {}", request.getTitle());
            Book newBook = booksService.createBook(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
        } catch (Exception e) {
            log.error("Error al crear el libro: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el libro.");
        }
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<?> updateBook(@PathVariable String bookId, @RequestBody BookDto updateRequest) {
        try {
            log.info("Actualizando libro con ID: {}", bookId);
            Book updatedBook = booksService.updateBook(Integer.parseInt(bookId), updateRequest);
            return updatedBook != null ? ResponseEntity.ok(updatedBook) :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el libro con ID: " + bookId);
        } catch (NumberFormatException e) {
            log.error("Formato de ID inválido: {}", bookId, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El ID debe ser un número válido.");
        } catch (Exception e) {
            log.error("Error al actualizar el libro: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable String id) {
        try {
            log.info("Eliminando libro con ID: {}", id);
            Boolean deleted = booksService.removeBook(Integer.parseInt(id));
            return deleted ? ResponseEntity.noContent().build() :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el libro con ID: " + id);
        } catch (NumberFormatException e) {
            log.error("Formato de ID inválido: {}", id, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El ID debe ser un número válido.");
        } catch (Exception e) {
            log.error("Error al eliminar el libro: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor.");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String publisher,
            @RequestParam(required = false) String publicationYear,
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) BigDecimal price,
            @RequestParam(required = false) Integer stock,
            @RequestParam(required = false) Integer rating) {

        try {
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
        } catch (Exception e) {
            log.error("Error al buscar libros: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor.");
        }
    }

    @PutMapping("/inactive/{bookId}")
    public ResponseEntity<?> inactivateBook(@PathVariable String bookId) {
        try {
            log.info("Inactivando libro con ID: {}", bookId);
            Book updatedBook = booksService.inactivateBook(Integer.parseInt(bookId));
            return updatedBook != null ? ResponseEntity.ok(updatedBook) :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el libro con ID: " + bookId);
        } catch (NumberFormatException e) {
            log.error("Formato de ID inválido: {}", bookId, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El ID debe ser un número válido.");
        } catch (Exception e) {
            log.error("Error al inactivar el libro: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor.");
        }
    }
}
