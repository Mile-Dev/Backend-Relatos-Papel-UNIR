package com.example.books.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private String title;
    private String author;
    private String category;
    private String subcategory;
    private String description;
    private Double price;
    private Integer rating;
    private String image;
    private Integer discount;
}
