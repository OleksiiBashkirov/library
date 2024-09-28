package bashkirov.library.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
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

    @Max(value = 2024, message = "Year cannot be in the future")
    private int year;

    @NotBlank(message = "Field cannot be empty")
    @Pattern(regexp = "^\\d{4}-\\d{6}$", message = "Field should be in the format `1234-123456")
    @Size(min = 11, max = 11, message = "Field should be in the format `1234-123456`")
    private String article;
}
