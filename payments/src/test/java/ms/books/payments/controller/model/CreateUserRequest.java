package ms.books.payments.controller.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "email cannot be null")
    @Email(message = "Email should be valid")
    private String email;

    @Size(min = 10, max = 15, message = "About Me must be between 10 and 15 characters")
    private String phone;
}
