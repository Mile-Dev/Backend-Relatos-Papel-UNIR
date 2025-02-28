package com.example.books.data;

import com.example.books.controller.model.BooksQueryResponse;
import com.example.books.data.model.BookElasticSearch;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
    private final ElasticsearchOperations elasticsearchOperations;

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
    public BooksQueryResponse bookSearch(
            String title,
            String author,
            String category,
            String subcategory,
            String description,
            Boolean aggregate) {

        BoolQueryBuilder querySpec = QueryBuilders.boolQuery();

        if (StringUtils.isNotBlank(title)) {
            querySpec.must(QueryBuilders.matchQuery("title", title));
        }

        if (StringUtils.isNotBlank(author)) {
            querySpec.must(QueryBuilders.matchQuery("author", author));
        }

        if (StringUtils.isNotBlank(category)) {
            querySpec.must(QueryBuilders.termQuery("category", category));
        }

        if (StringUtils.isNotBlank(subcategory)) {
            querySpec.must(QueryBuilders.termQuery("subcategory", subcategory));
        }

        if (StringUtils.isNotBlank(description)) {
            querySpec.must(QueryBuilders.matchQuery("description", description));
        }

        if (!querySpec.hasClauses()) {
            querySpec.must(QueryBuilders.matchAllQuery());
        }

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder()
                .withQuery(querySpec);


        if (Boolean.TRUE.equals(aggregate)) {
            nativeSearchQueryBuilder.addAggregation(
                    AggregationBuilders.terms("categoryAggregation").field("category").size(1000)
            );

            nativeSearchQueryBuilder.withMaxResults(0);
        }

        Query query = nativeSearchQueryBuilder.build();
        SearchHits<BookElasticSearch> searchHits =
                elasticsearchOperations.search(query, BookElasticSearch.class);

        List<BookElasticSearch> results = searchHits.getSearchHits()
                .stream()
                .map(SearchHit::getContent)
                .toList();


        return new BooksQueryResponse(results);
    }
}
