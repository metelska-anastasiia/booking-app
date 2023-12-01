package mate.academy.bookingapp.dto.address;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class UpdateAddressRequestDto {
    private String country;
    private String city;
    private String firstAddressLine;
    private String secondAddressLine;
    private Integer zipcode;
}
