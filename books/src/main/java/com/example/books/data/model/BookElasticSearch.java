package com.example.books.data.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;
import lombok.*;

import java.util.List;

@Document(indexName = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookElasticSearch {

    @Id
    private String id;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String title;

    @Field(type = FieldType.Keyword)
    private String titleKeyword;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String author;

    @Field(type = FieldType.Keyword)
    private String authorKeyword;

    @Field(type = FieldType.Keyword)
    private String image;

    @Field(type = FieldType.Float)
    private Double price;

    @Field(type = FieldType.Integer)
    private Integer discount;

    @Field(type = FieldType.Keyword)
    private String category;

    @Field(type = FieldType.Keyword)
    private String subcategory;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String description;

    @Field(type = FieldType.Nested)
    private List<Review> reviews;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Review {

        @Field(type = FieldType.Text, analyzer = "standard")
        private String comment;

        @Field(type = FieldType.Byte)
        private Byte rating;

        @Field(type = FieldType.Integer)
        private Integer likes;

        @Field(type = FieldType.Integer)
        private Integer dislikes;
    }
}
