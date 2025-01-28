package ms.books.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import ms.books.data.BookRepository;
import ms.books.conroller.model.BookDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import ms.books.data.model.Book;
import ms.books.controller.model.CreateBookRequest;

@Service
@Slf4j
@RequiredArgsConstructor
public class BooksServiceImpl implements BooksService {

    private final BookRepository repository;
    private final ObjectMapper objectMapper;

    @Override
    public List<Book> getBooks(String title, String author, String genre, String publisher, Integer publicationYear, String isbn, BigDecimal price, Integer stock) {

        if (StringUtils.hasLength(title) || StringUtils.hasLength(author) || StringUtils.hasLength(genre)
                || StringUtils.hasLength(publisher) || publicationYear != null || StringUtils.hasLength(isbn) || price != null || stock != null) {
            return repository.search(title, author, genre, publisher, publicationYear, isbn, price, stock);
        }

        List<Book> books = repository.getBooks();
        return books.isEmpty() ? null : books;
    }

    @Override
    public Book getBook(String bookId) {  return repository.getById(Long.valueOf(bookId)); }

    @Override
    public Boolean removeBook(String bookId) {

        Book book = repository.getById(Long.valueOf(bookId));

        if (book != null) {
            repository.delete(book);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public Book createBook(CreateBookRequest request) {
        Book book = Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .genre(request.getGenre())
                .publisher(request.getPublisher())
                .publicationYear(request.getPublicationYear())
                .isbn(request.getIsbn())
                .price(request.getPrice())
                .stock(request.getStock())
                .build();

        return repository.save(book);
    }

    @Override
    public Book updateBook(String bookId, CreateBookRequest request) {
        Book book = repository.getById(Long.valueOf(bookId));

        if (book != null) {
            try{
                JsonMergePatch jsonMergePatch = JsonMerguePatch.fromJson(objectMapper.readTree(request));
                JsonNode target= jsonMergePatch.apply(objectMapper.readTree(objectMapper.writeValueAsString(book)));
                Book patched = objectMapper.treeToValue(target, Book.class);
                repository.save(patched);
                return patched;
            } catch (JsonProcessingException | JsonPatchException e) {
                log.error("Error updating book {}", bookId,e);
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public Book updateBook(String bookId, BookDto updateRequest) {
        Book book = repository.getById(Long.valueOf(bookId));

        if (book != null) {
            book.update(updateRequest);
            repository.save(book);
            return book;
        } else {
            return null;
        }
    }
}