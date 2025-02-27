package com.example.books.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookElasticRequest {
    private String title;
    private String author;
    private String genre;
    private String publisher;
    private List<String> categories;
    private Double price;
    private String language;
    private Integer stockQuantity;
    private String description;
}
