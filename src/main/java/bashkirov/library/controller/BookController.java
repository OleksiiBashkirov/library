package bashkirov.library.controller;

import bashkirov.library.dao.BookDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookDao bookDao;

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


}
