package com.example.books.data.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Document(indexName = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookElasticSearch {

    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String title;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String author;

    @Field(type = FieldType.Keyword)
    private String genre;

    @Field(type = FieldType.Keyword)
    private List<String> categories;

    @Field(type = FieldType.Float)
    private Double price;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String publisher;

    @Field(type = FieldType.Keyword)
    private String language;

    @Field(type = FieldType.Integer)
    private int stockQuantity;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String description;

    @Field(type = FieldType.Text, analyzer = "standard", copyTo = "allFields")
    private String searchField;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String allFields;
}
