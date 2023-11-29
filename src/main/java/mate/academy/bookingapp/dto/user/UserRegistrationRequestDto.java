package mate.academy.bookingapp.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;
import mate.academy.bookingapp.lib.FieldMatch;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldMatch(first = "password", second = "repeatPassword", message = "Passwords must match")
@Accessors(chain = true)
public class UserRegistrationRequestDto {
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String repeatPassword;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
}
