package com.example.books.service;

import com.example.books.controller.model.BookDto;
import com.example.books.controller.model.CreateBookRequest;
import com.example.books.data.model.Book;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface BooksService {

    List<Book> getBooks(String title, String author, String genre, String publisher,
                        String publicationYear, String isbn, BigDecimal price, Integer stock, Integer rating);

    Book getBook(Integer bookId);

    Boolean removeBook(Integer bookId);

    Book updateBook(Integer bookId, BookDto updateRequest);

    Book createBook(CreateBookRequest request);

    List<Book> getBooksFilters(Map<String, Object> filters);

}
