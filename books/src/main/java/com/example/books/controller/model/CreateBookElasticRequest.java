package com.example.books.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookElasticRequest {
    private String id;
    private String title;
    private String author;
    private String category;
    private String subcategory;
    private String description;
    private Double price;
    private String image;
    private Integer discount;
    private List<Review> reviews;
}
