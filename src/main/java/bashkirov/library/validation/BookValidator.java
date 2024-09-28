package bashkirov.library.validation;

import bashkirov.library.dao.BookDao;
import bashkirov.library.model.Book;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class BookValidator implements Validator {
    private final BookDao bookDao;

    @Override
    public boolean supports(Class<?> clazz) {
        return Objects.equals(clazz, Book.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;
        Optional<Book> optionalBook = bookDao.findByArticle(book.getArticle());
        if (optionalBook.isPresent()) {
            Book existedBook = optionalBook.get();
            if(book.getId() == 0 || book.getId() != existedBook.getId()) {
                errors.rejectValue(
                        "article",
                        "",
                        String.format("Book with such article= %s already exists", book.getArticle())
                );
            }
        }
    }
}
