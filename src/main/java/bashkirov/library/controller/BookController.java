package bashkirov.library.controller;

import bashkirov.library.dao.BookDao;
import bashkirov.library.model.Book;
import bashkirov.library.validation.BookValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookDao bookDao;
    private final BookValidator bookValidator;

    @GetMapping("/{id}")
    public String getById(
            @PathVariable("id") int bookId,
            Model model
    ) {
        model.addAttribute("bookGetById", bookDao.getById(bookId));
        return "book-page";
    }

    @GetMapping
    public String getAll(
            Model model
    ) {
        model.addAttribute("bookGetAll", bookDao.getAll());
        return "books-page";
    }

    @GetMapping("/new")
    public String bookNewPage(
            @ModelAttribute("newBook") Book newBook
    ) {
        return "book-new-page";
    }

    @PostMapping
    public String save(
            @Valid @ModelAttribute("newBook") Book newBook,
            BindingResult bindingResult
    ) {
        bookValidator.validate(newBook, bindingResult);
        if (bindingResult.hasErrors()) {
            return "book-new-page";
        }
        bookDao.save(newBook);
        return "redirect:/book";
    }

    @GetMapping("/edit/{id}")
    public String bookUpdatePage(
            @PathVariable("id") int bookId,
            Model model
    ) {
        model.addAttribute("updateBook", bookDao.getById(bookId));
        return "book-update-page";
    }

    @PutMapping("/{id}")
    public String update(
            @PathVariable("id") int bookId,
            @Valid @ModelAttribute("updateBook") Book updateBook,
            BindingResult bindingResult
    ) {
        bookValidator.validate(updateBook, bindingResult);
        if (bindingResult.hasErrors()) {
            return "book-update-page";
        }
        bookDao.update(bookId, updateBook);
        return "redirect:/book/" + bookId;
    }


}
