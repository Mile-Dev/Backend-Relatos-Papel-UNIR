package com.example.books.service;

import com.example.books.controller.model.BookDto;
import com.example.books.controller.model.CreateBookRequest;
import com.example.books.data.BookRepository;
import com.example.books.data.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;

@Service
public class BooksServiceImpl implements BooksService {

    private final BookRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public BooksServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Book> getBooks(String title, String author, String genre, String publisher,
                               String publicationYear, String isbn, BigDecimal price, Integer stock, Integer rating ) {
        return repository.findAll();
    }

    @Override
    public Book getBook(Integer bookId) {
        Optional<Book> book = repository.findById(Long.valueOf(bookId));
        return book.orElse(null);
    }

    @Override
    public Boolean removeBook(Integer bookId) {
        Optional<Book> book = repository.findById(Long.valueOf(bookId));
        if (book.isPresent()) {
            repository.delete(book.get());
            return true;
        }
        return false;
    }

    @Override
    public Book updateBook(Integer bookId, BookDto updateRequest) {
        Optional<Book> bookOpt = repository.findById(Long.valueOf(bookId));

        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            if (updateRequest.getTitle() != null) book.setTitle(updateRequest.getTitle());
            if (updateRequest.getAuthor() != null) book.setAuthor(updateRequest.getAuthor());
            if (updateRequest.getGenre() != null) book.setGenre(updateRequest.getGenre());
            if (updateRequest.getPublisher() != null) book.setPublisher(updateRequest.getPublisher());
            if (updateRequest.getPublicationYear() != null) book.setPublicationYear(updateRequest.getPublicationYear());
            if (updateRequest.getIsbn() != null) book.setIsbn(updateRequest.getIsbn());
            if (updateRequest.getPrice() != null) book.setPrice(updateRequest.getPrice());
            if (updateRequest.getStock() != null) book.setStock(updateRequest.getStock());
            if (updateRequest.getRating() != null) book.setRating(updateRequest.getRating());

            return repository.save(book);
        }
        return null;
    }

    @Override
    public Book createBook(CreateBookRequest request) {
        Book newBook = Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .genre(request.getGenre())
                .publisher(request.getPublisher())
                .publicationYear(request.getPublicationYear())
                .isbn(request.getIsbn())
                .price(request.getPrice())
                .stock(request.getStock())
                .rating(request.getRating())
                .build();
        return repository.save(newBook);
    }

    @Override
    public List<Book> getBooksFilters(Map<String, Object> filters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = cb.createQuery(Book.class);
        Root<Book> book = query.from(Book.class);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.equal(book.get("status"), 1));

        List<Predicate> filterPredicates = new ArrayList<>();

        filters.forEach((key, value) -> {
            if (value != null) {
                if (value instanceof String) {
                    filterPredicates.add(cb.like(cb.lower(book.get(key)), "%" + value.toString().toLowerCase() + "%"));
                } else {
                    filterPredicates.add(cb.equal(book.get(key), value));
                }
            }
        });

        if (!filterPredicates.isEmpty()) {
            predicates.add(cb.or(filterPredicates.toArray(new Predicate[0])));
        }

        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }
}
