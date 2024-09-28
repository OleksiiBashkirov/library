package bashkirov.library.dao;

import bashkirov.library.model.Book;
import bashkirov.library.model.Person;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookDao {
    private final JdbcTemplate jdbcTemplate;

    public Book getById(int id) {
        return jdbcTemplate.query(
                "select * from book where id = ?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)
        ).stream().findAny().orElseThrow(
                () -> new NoSuchElementException("Failed to find book with id=" + id)
        );
    }

    public List<Book> getAll() {
        return jdbcTemplate.query(
                "select * from book",
                new BeanPropertyRowMapper<>(Book.class)
        );
    }

    public void save(Book book) {
        jdbcTemplate.update(
                "insert into book(title, author, year, article) values (?,?,?,?)",
                book.getTitle(),
                book.getAuthor(),
                book.getYear(),
                book.getArticle()
        );
    }

    public void update(int id, Book book) {
        jdbcTemplate.update(
                "update book set title = ?, author = ?, year = ?, article = ? where id = ?",
                book.getTitle(),
                book.getAuthor(),
                book.getYear(),
                book.getArticle(),
                id
        );
    }

    public void deleteById(int id) {
        jdbcTemplate.update(
                "delete from book where id = ?",
                id
        );
    }

    public Optional<Book> findByArticle(String article) {
        return jdbcTemplate.query(
                "select * from book where article = ?",
                new Object[]{article},
                new BeanPropertyRowMapper<>(Book.class)
        ).stream().findAny();
    }

    public Optional<Person> getBookOwnerByBookId(int bookId) {
        return jdbcTemplate.query(
                "select p.* from book b join person p on b.person_id = p.id where b.id = ?",
                new Object[]{bookId},
                new BeanPropertyRowMapper<>(Person.class)
        ).stream().findAny();
    }

    public void assignBookOwnerByPersonId(int bookId, int personId) {
        jdbcTemplate.update(
                "update book set person_id = ? where id = ?",
                personId,
                bookId
        );
    }

    public void releaseBookOwnerByBookId(int bookId) {
        jdbcTemplate.update(
                "update book set person_id = null where id = ?",
                bookId
        );
    }
}
