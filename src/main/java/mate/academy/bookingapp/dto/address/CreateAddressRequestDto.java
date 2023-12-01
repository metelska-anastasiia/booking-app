package mate.academy.bookingapp.dto.address;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class CreateAddressRequestDto {
    @NotNull
    private String country;
    @NotNull
    private String city;
    @NotNull
    private String firstAddressLine;
    private String secondAddressLine;
    @NotNull
    private Integer zipcode;
}
