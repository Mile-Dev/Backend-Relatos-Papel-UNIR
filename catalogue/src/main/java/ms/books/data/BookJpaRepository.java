package ms.books.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ms.books.data.model.Book;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

interface BookJpaRepository extends  JpaRepository<Book, Long>, JpaSpecificationExecutor<Book>{
    List<Book> findByName(String title);

    List<Book> findByAuthor(String author);

    List<Book> findByGenre(String genre);

    List<Book> findByPublisher(String publisher);

    List<Book> findByPublicationYear(Integer publicationYear);

    List<Book> findByIsbn(String isbn);

    List<Book> findByPrice(Double price);

    List<Book> findByStock(String tittle, String author);

}