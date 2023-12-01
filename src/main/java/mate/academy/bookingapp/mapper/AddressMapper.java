package mate.academy.bookingapp.mapper;

import mate.academy.bookingapp.config.MapperConfig;
import mate.academy.bookingapp.dto.accommodation.UpdateAccommodationRequestDto;
import mate.academy.bookingapp.dto.address.CreateAddressRequestDto;
import mate.academy.bookingapp.model.Address;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface AddressMapper {
    Address toModel(CreateAddressRequestDto requestDto);

    Address toModel(UpdateAccommodationRequestDto requestDto);

}
