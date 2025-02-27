package com.example.books.data;

import com.example.books.controller.model.BooksQueryResponse;
import com.example.books.data.model.BookElasticSearch;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BookElasticRepository {

    private final IBookElasticRepository bookElasticRepository;
    private final ElasticsearchOperations elasticClient;

    public BookElasticSearch save(BookElasticSearch product) {
        return bookElasticRepository.save(product);
    }

    public Boolean delete(BookElasticSearch product) {
        bookElasticRepository.delete(product);
        return Boolean.TRUE;
    }

    public Optional<BookElasticSearch> findById(String id) {
        return bookElasticRepository.findById(id);
    }

    @SneakyThrows
    public BooksQueryResponse BookSearch(String title, String author, String genre, String publisher, String language, String description, Boolean aggregate) {

        BoolQueryBuilder querySpec = QueryBuilders.boolQuery();

        if (!StringUtils.isEmpty(title)) {
            querySpec.must(QueryBuilders.matchQuery("title", title));
        }

        if (!StringUtils.isEmpty(author)) {
            querySpec.must(QueryBuilders.matchQuery("author", author));
        }

        if (!StringUtils.isEmpty(genre)) {
            querySpec.must(QueryBuilders.termQuery("genre", genre));
        }

        if (!StringUtils.isEmpty(publisher)) {
            querySpec.must(QueryBuilders.matchQuery("publisher", publisher));
        }

        if (!StringUtils.isEmpty(language)) {
            querySpec.must(QueryBuilders.termQuery("language", language));
        }

        if (!StringUtils.isEmpty(description)) {
            querySpec.must(QueryBuilders.matchQuery("description", description));
        }

        if (!querySpec.hasClauses()) {
            querySpec.must(QueryBuilders.matchAllQuery());
        }

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder().withQuery(querySpec);

        if (aggregate) {
            nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("language Aggregation").field("language").size(1000));
            // Mas info sobre size 1000 - https://www.elastic.co/guide/en/elasticsearch/reference/7.10/search-aggregations-bucket-terms-aggregation.html#search-aggregations-bucket-terms-aggregation-size
            nativeSearchQueryBuilder.withMaxResults(0);
        }

        Query query = nativeSearchQueryBuilder.build();
        SearchHits<BookElasticSearch> result = elasticClient.search(query, BookElasticSearch.class);


        return new BooksQueryResponse(result.getSearchHits().stream().map(SearchHit::getContent).toList());
    }

}
