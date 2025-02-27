package com.example.books.service;

import com.example.books.controller.model.BooksQueryResponse;
import com.example.books.controller.model.CreateBookElasticRequest;
import com.example.books.data.model.BookElasticSearch;

import java.util.List;

public interface IBooksElasticSearchService {

    BooksQueryResponse BookSearch(String title, String author, String genre, String publisher, String language, String description, Boolean aggregate);

    BookElasticSearch getBook(String bookId);

    Boolean removeBook(String bookId);

    BookElasticSearch createBook(CreateBookElasticRequest request);

}
