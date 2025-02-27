package com.example.books.service;

import com.example.books.controller.model.BooksQueryResponse;
import com.example.books.controller.model.CreateBookElasticRequest;
import com.example.books.data.BookElasticRepository;
import com.example.books.data.model.BookElasticSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BooksElasticSearchService implements IBooksElasticSearchService {

    private final BookElasticRepository repository;

    @Override
    public BooksQueryResponse BookSearch(String title, String author, String genre, String publisher, String language, String description, Boolean aggregate) {
        return repository.BookSearch(title, author, genre, publisher, language, description, aggregate);
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

        if (request != null && StringUtils.hasLength(request.getTitle().trim())
                && StringUtils.hasLength(request.getDescription().trim())
                && StringUtils.hasLength(request.getAuthor().trim()) && request.getPrice() != null) {

            BookElasticSearch bookElasticSearch = BookElasticSearch.builder().title(request.getTitle()).description(request.getDescription())
                    .author(request.getAuthor()).genre(request.getGenre()).categories(request.getCategories())
                    .price(request.getPrice()).publisher(request.getPublisher()).language(request.getLanguage())
                    .stockQuantity(request.getStockQuantity()).build();

            return repository.save(bookElasticSearch);
        }else {
            return null;
        }
    }
}
