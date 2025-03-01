package com.example.books.service;

import com.example.books.controller.model.BooksQueryResponse;
import com.example.books.controller.model.CreateBookElasticRequest;
import com.example.books.data.BookElasticRepository;
import com.example.books.data.model.BookElasticSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BooksElasticSearchService implements IBooksElasticSearchService {

    private final BookElasticRepository repository;

    @Override
    public BooksQueryResponse BookSearch(String search,  String category, String subcategory, Boolean aggregate) {
        return repository.bookSearch(search, category, subcategory, aggregate);
    }

    @Override
    public BookElasticSearch getBook(String bookId) {
        return repository.findById(bookId).orElse(null);
    }

    @Override
    public Boolean removeBook(String bookId) {
        BookElasticSearch book = repository.findById(bookId).orElse(null);
        if (book != null) {
            repository.delete(book);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public BookElasticSearch createBook(CreateBookElasticRequest request) {
        if (request != null
                && StringUtils.hasLength(request.getTitle().trim())
                && StringUtils.hasLength(request.getDescription().trim())
                && StringUtils.hasLength(request.getAuthor().trim())
                && request.getPrice() != null) {

            List<com.example.books.data.model.BookElasticSearch.Review> reviews = null;
            if (request.getReviews() != null) {
                reviews = request.getReviews().stream()
                        .map(r -> new com.example.books.data.model.BookElasticSearch.Review(
                                r.getComment(),
                                r.getRating() == null ? null : r.getRating().byteValue(), // conversión explícita
                                r.getLikes(),
                                r.getDislikes()))
                        .collect(Collectors.toList());
            }

            BookElasticSearch bookElasticSearch = BookElasticSearch.builder()
                    .id(request.getId())
                    .title(request.getTitle())
                    .description(request.getDescription())
                    .author(request.getAuthor())
                    .category(request.getCategory())
                    .subcategory(request.getSubcategory())
                    .price(request.getPrice())
                    .image(request.getImage())
                    .discount(request.getDiscount())
                    .reviews(reviews)  // Ahora la lista tiene el tipo correcto
                    .build();

            return repository.save(bookElasticSearch);
        } else {
            return null;
        }
    }

    @Override
    public BookElasticSearch updateBookReviews(String id, List<com.example.books.controller.model.Review> updatedReviews) {

        // 1. Buscar el libro en el repositorio
        BookElasticSearch existingBook = repository.findById(id).orElse(null);
        if (existingBook == null) {
            return null; // Si no existe el libro, retornar null
        }

        // 2. Obtener lista de reseñas actuales y asegurar que sea mutable
        List<BookElasticSearch.Review> existingReviews = existingBook.getReviews();
        if (existingReviews == null) {
            existingReviews = new ArrayList<>();
        } else {
            existingReviews = new ArrayList<>(existingReviews); // Copia para modificar
        }

        // 3. Iterar sobre las reseñas enviadas en el request
        if (updatedReviews != null) {
            for (com.example.books.controller.model.Review r : updatedReviews) {

                // Convertir la reseña entrante al formato de BookElasticSearch
                BookElasticSearch.Review converted = new BookElasticSearch.Review(
                        r.getComment(),
                        (r.getRating() == null) ? null : r.getRating().byteValue(),
                        r.getLikes(),
                        r.getDislikes()
                );

                // 3a. Buscar si ya existe una reseña con el mismo comentario
                Optional<BookElasticSearch.Review> existing = existingReviews.stream()
                        .filter(er -> er.getComment().equalsIgnoreCase(r.getComment())) // Ignorar mayúsculas
                        .findFirst();

                if (existing.isPresent()) {
                    // 3b. Si ya existe, actualizar la reseña
                    BookElasticSearch.Review existingReview = existing.get();
                    existingReview.setRating(converted.getRating());
                    existingReview.setLikes(converted.getLikes());
                    existingReview.setDislikes(converted.getDislikes());
                } else {
                    // 3c. Si no existe, agregar la nueva reseña
                    existingReviews.add(converted);
                }
            }
        }

        // 🔹 FORZAR LA ACTUALIZACIÓN DE LA LISTA EN EL LIBRO
        existingBook.setReviews(new ArrayList<>(existingReviews)); // Evitar problemas de referencia

        // 4. Guardar el libro con la lista actualizada
        return repository.save(existingBook);
    }





}


