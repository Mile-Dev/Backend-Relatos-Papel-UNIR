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
    public BooksQueryResponse BookSearch(String title, String author, String category, String subcategory, String description, Boolean aggregate) {
        return repository.bookSearch(title, author, category, subcategory, description, aggregate);
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
                                r.getRating() == null ? null : r.getRating().byteValue(),
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
                    .reviews(reviews)
                    .build();

            return repository.save(bookElasticSearch);
        } else {
            return null;
        }
    }

    @Override
    public BookElasticSearch updateBookReviews(String id, List<com.example.books.controller.model.Review> updatedReviews) {

        BookElasticSearch existingBook = repository.findById(id).orElse(null);
        if (existingBook == null) {
            return null;
        }


        List<BookElasticSearch.Review> existingReviews = existingBook.getReviews();
        if (existingReviews == null) {
            existingReviews = new ArrayList<>();
        } else {
            existingReviews = new ArrayList<>(existingReviews);
        }

        if (updatedReviews != null) {
            for (com.example.books.controller.model.Review r : updatedReviews) {


                BookElasticSearch.Review converted = new BookElasticSearch.Review(
                        r.getComment(),
                        (r.getRating() == null) ? null : r.getRating().byteValue(),
                        r.getLikes(),
                        r.getDislikes()
                );


                Optional<BookElasticSearch.Review> existing = existingReviews.stream()
                        .filter(er -> er.getComment().equalsIgnoreCase(r.getComment()))
                        .findFirst();

                if (existing.isPresent()) {
                    BookElasticSearch.Review existingReview = existing.get();
                    existingReview.setRating(converted.getRating());
                    existingReview.setLikes(converted.getLikes());
                    existingReview.setDislikes(converted.getDislikes());
                } else {
                    existingReviews.add(converted);
                }
            }
        }

        existingBook.setReviews(new ArrayList<>(existingReviews));

        return repository.save(existingBook);
    }





}


