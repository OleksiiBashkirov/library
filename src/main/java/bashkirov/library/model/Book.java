package bashkirov.library.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private int id;

    @NotBlank(message = "Field cannot be empty")
    @Size(min = 1, max = 64, message = "Field cannot be less 1 and more 64 characters")
    private String title;

    @NotBlank(message = "Field cannot be empty")
    @Size(min = 3, max = 64, message = "Field cannot be less 3 and more 64 characters")
    private String author;

    @PastOrPresent(message = "Year cannot be in the future")
    private int year;

    @NotBlank(message = "Field cannot be empty")
    @Pattern(regexp = "^\\d[0-9]{4}-\\d[0-9]{6}$", message = "Field should be in the format `1234-123456")
    @Size(min = 11, max = 11, message = "Field should be in the format `1234-123456`")
    private String article;
}
