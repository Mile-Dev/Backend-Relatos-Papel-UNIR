package com.example.books.service;

import com.example.books.controller.model.BooksQueryResponse;
import com.example.books.controller.model.CreateBookElasticRequest;
import com.example.books.controller.model.Review;
import com.example.books.data.model.BookElasticSearch;

import java.util.List;

public interface IBooksElasticSearchService {

    BooksQueryResponse BookSearch(String search, String category, String subcategory, Boolean aggregate);

    BookElasticSearch getBook(String bookId);

    Boolean removeBook(String bookId);

    BookElasticSearch createBook(CreateBookElasticRequest request);

    BookElasticSearch updateBookReviews(String bookId, List<Review> updatedReviews);

}
