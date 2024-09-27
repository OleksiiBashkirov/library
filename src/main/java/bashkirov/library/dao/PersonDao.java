package bashkirov.library.dao;

import bashkirov.library.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PersonDao {
    private final JdbcTemplate jdbcTemplate;

    public Person getById(int id) {
        return jdbcTemplate.query(
                "select * from person where id = ?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)
        ).stream().findAny().orElseThrow(
                () -> new NoSuchElementException("Failed to find person with id = " + id)
        );
    }

    public List<Person> getAll() {
        return jdbcTemplate.query(
                "select * from person",
                new BeanPropertyRowMapper<>(Person.class)
        );
    }

    public void save(Person person) {
        jdbcTemplate.update(
                "insert into person(name, lastname, inn, birthdate) values (?,?,?,?)",
                person.getName(),
                person.getLastname(),
                person.getInn(),
                person.getBirthdate()
        );
    }

    public void update(int id, Person person) {
        jdbcTemplate.update(
                "update person set name = ?, lastname = ?, inn = ?, birthdate = ? where id = ?",
                person.getName(),
                person.getLastname(),
                person.getInn(),
                person.getBirthdate(),
                id
        );
    }

    public void deleteById(int id) {
        jdbcTemplate.update(
                "delete from person where id = ?",
                id
        );
    }

    public Optional<Person> findByInn(String inn) {
        return jdbcTemplate.query(
                "select * from person where inn = ?",
                new Object[]{inn},
                new BeanPropertyRowMapper<>(Person.class)
        ).stream().findAny();
    }
}
