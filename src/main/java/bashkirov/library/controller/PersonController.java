package bashkirov.library.controller;

import bashkirov.library.dao.PersonDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonDao personDao;

    @GetMapping("/{id}")
    public String getById(
            @PathVariable("id") int personId,
            Model model
    ) {
        model.addAttribute("personGetById", personDao.getById(personId));
        return "person-page";
    }




}
