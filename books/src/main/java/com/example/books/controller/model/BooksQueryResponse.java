package com.example.books.controller.model;

import com.example.books.data.model.BookElasticSearch;
import com.example.books.service.BooksElasticSearchService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BooksQueryResponse {

    private List<BookElasticSearch> books;

}
