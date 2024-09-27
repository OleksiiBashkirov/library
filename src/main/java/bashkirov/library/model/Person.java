package bashkirov.library.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private int id;

    @NotBlank(message = "Field cannot be empty")
    @Size(min = 2, max = 32, message = "Field cannot be less 2 and more 32 characters")
    private String name;

    @NotBlank(message = "Field cannot be empty")
    @Size(min = 1, max = 32, message = "Field cannot be less 1 and more 32 characters")
    private String lastname;

    @NotBlank(message = "Field cannot be empty")
    @Pattern(regexp = "^[0-9]{10}$")
    private String inn;

    @PastOrPresent(message = "Date cannot be in the future")
    private LocalDate birthdate;
}
