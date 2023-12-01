package mate.academy.bookingapp.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class UserInfoResponseDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
}
