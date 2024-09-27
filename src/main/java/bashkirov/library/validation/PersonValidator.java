package bashkirov.library.validation;

import bashkirov.library.dao.PersonDao;
import bashkirov.library.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PersonValidator implements Validator {
    private final PersonDao personDao;

    @Override
    public boolean supports(Class<?> clazz) {
        return Objects.equals(clazz, Person.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        Optional<Person> optionalPerson = personDao.findByInn(person.getInn());
        if (optionalPerson.isPresent()) {
            Person existedPerson = optionalPerson.get();
            if (person.getId() == 0 || person.getId() != existedPerson.getId()) {
                errors.rejectValue("inn", "", String.format("Person with such INN already exists", person.getInn()));
            }
        }
    }
}
