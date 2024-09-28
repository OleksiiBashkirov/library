package bashkirov.library.controller;

import bashkirov.library.dao.PersonDao;
import bashkirov.library.model.Person;
import bashkirov.library.validation.PersonValidator;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
    private final PersonDao personDao;
    private final PersonValidator personValidator;

    @GetMapping("/{id}")
    public String getById(
            @PathVariable("id") int personId,
            Model model
    ) {
        model.addAttribute("personGetById", personDao.getById(personId));
        return "person-page";
    }

    @GetMapping
    public String getAll(
            Model model
    ) {
        model.addAttribute("personGetAll", personDao.getAll());
        return "people-page";
    }

    @GetMapping("/new")
    public String savePersonNewPage(
            @ModelAttribute("personSave") Person person
    ) {
        return "person-new-page";
    }

    @PostMapping
    public String save(
            @Valid @ModelAttribute("personSave") Person person,
            BindingResult bindingResult
    ) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "person-new-page";
        }
        personDao.save(person);
        return "redirect:/person";
    }

    @GetMapping("/edit/{id}")
    public String updatePersonUpdatePage(
            @PathVariable("id") int personId,
            Model model
    ) {
        model.addAttribute("personUpdate", personDao.getById(personId));
        return "person-update-page";
    }

    @PutMapping("/{id}")
    public String update(
            @PathVariable("id") int personId,
            @Valid @ModelAttribute("personUpdate") Person person,
            BindingResult bindingResult
    ) {
        person.setId(personId);
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "person-update-page";
        }
        personDao.update(personId, person);
        return "redirect:/person/" + personId;
    }


    public String formatDate(LocalDate date) {
        return date != null ? date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
    }


}
