package ms.books.data;

import ms.books.data.utils.SeachCriteria;
import ms.books.data.utils.SearchOperation;
import ms.books.data.utils.SearchStatement;
import ms.books.data.model.Book;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepository {

    private final BookJpaRepository repository;

    public List<Book> getBooks() { return repository.findAll(); }

    public Book getById(Long id) {return repository.findById(id).orElse(null); }

    public Book save(Book book) {  return repository.save(book); }

    public void delete(Book book) { repository.delete(book);   }

    public List<Book> search(String title, String author, String genre, String publisher, Integer publicationYear, String isbn, BigDecimal price, Integer stock) {
        SearchCriteria<Book> spec = new SearchCriteria<>();

        if (StringUtils.isNotBlank(title)) {
            spec.add(new SearchStatement("title", title, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(author)) {
            spec.add(new SearchStatement("author", author, SearchOperation.EQUAL));
        }

        if (StringUtils.isNotBlank(genre)) {
            spec.add(new SearchStatement("genre", genre, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(publisher)) {
            spec.add(new SearchStatement("publisher", publisher, SearchOperation.MATCH));
        }

        if (publicationYear != null) {
            spec.add(new SearchStatement("publicationYear", publicationYear, SearchOperation.EQUAL));
        }

        if (StringUtils.isNotBlank(isbn)) {
            spec.add(new SearchStatement("isbn", isbn, SearchOperation.EQUAL));
        }

        if (price != null) {
            spec.add(new SearchStatement("price", price, SearchOperation.EQUAL));
        }

        return repository.findAll(spec);
    }

}