package mate.academy.bookingapp.mapper;

import mate.academy.bookingapp.config.MapperConfig;
import mate.academy.bookingapp.dto.accommodation.AccommodationResponseDto;
import mate.academy.bookingapp.dto.accommodation.CreateAccommodationRequestDto;
import mate.academy.bookingapp.model.Accommodation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface AccommodationMapper {

    Accommodation toModel(CreateAccommodationRequestDto requestDto);

    @Mapping(source = "address.id", target = "addressId")
    AccommodationResponseDto toDto(Accommodation accommodation);
}
