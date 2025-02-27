package com.example.books.data;

import com.example.books.data.model.BookElasticSearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

public interface IBookElasticRepository extends ElasticsearchRepository<BookElasticSearch, String> {

    List<BookElasticSearch> findByTitle(String title);

    Optional<BookElasticSearch> findById(String id);

    BookElasticSearch save(BookElasticSearch product);

    void delete(BookElasticSearch product);

    List<BookElasticSearch> findAll();
}
