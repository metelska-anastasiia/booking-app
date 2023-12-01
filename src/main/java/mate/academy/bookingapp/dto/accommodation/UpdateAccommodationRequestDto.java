package mate.academy.bookingapp.dto.accommodation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;
import mate.academy.bookingapp.dto.address.UpdateAddressRequestDto;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class UpdateAccommodationRequestDto {
    private String type;
    private String size;
    private List<String> amenities;
    private BigDecimal dailyRate;
    private Integer availability;
    private UpdateAddressRequestDto address;
}
