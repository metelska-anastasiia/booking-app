package mate.academy.bookingapp.dto.accommodation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;
import mate.academy.bookingapp.dto.address.CreateAddressRequestDto;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class CreateAccommodationRequestDto {
    @NotNull
    private String type;
    @NotNull
    private String size;
    private List<String> amenities;
    @Min(0)
    private BigDecimal dailyRate;
    @Min(0)
    private Integer availability;
    private CreateAddressRequestDto address;
}
