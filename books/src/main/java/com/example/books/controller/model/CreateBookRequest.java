package com.example.books.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRequest {
    private String title;
    private String author;
    private String genre;
    private String publisher;
    private String publicationYear;
    private String isbn;
    private Double price;
    private Integer stock;
    private Integer rating;
    private Integer status;
}
