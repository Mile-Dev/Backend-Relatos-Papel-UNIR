package com.example.books.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private String comment;
    private Integer rating;
    private Integer likes;
    private Integer dislikes;
}
