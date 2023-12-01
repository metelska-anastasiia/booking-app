package mate.academy.bookingapp.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.experimental.Accessors;
import mate.academy.bookingapp.lib.FieldMatch;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldMatch(first = "password", second = "repeatPassword", message = "Passwords must match")
@Accessors(chain = true)
public class UserUpdateInfoDto {
    @Email
    private String email;
    private String password;
    private String repeatPassword;
    private String firstName;
    private String lastName;
}
