package mate.academy.bookingapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.bookingapp.dto.accommodation.AccommodationResponseDto;
import mate.academy.bookingapp.dto.accommodation.CreateAccommodationRequestDto;
import mate.academy.bookingapp.dto.accommodation.UpdateAccommodationRequestDto;
import mate.academy.bookingapp.dto.address.UpdateAddressRequestDto;
import mate.academy.bookingapp.exception.EntityNotFoundException;
import mate.academy.bookingapp.mapper.AccommodationMapper;
import mate.academy.bookingapp.mapper.AddressMapper;
import mate.academy.bookingapp.model.Accommodation;
import mate.academy.bookingapp.model.Address;
import mate.academy.bookingapp.repository.AccommodationRepository;
import mate.academy.bookingapp.repository.AddressRepository;
import mate.academy.bookingapp.service.AccommodationService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final AccommodationMapper accommodationMapper;
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    public AccommodationResponseDto save(
            CreateAccommodationRequestDto requestDto
    ) {
        Accommodation accommodation = accommodationMapper.toModel(requestDto);
        Address address = addressRepository
                .save(addressMapper.toModel(requestDto.getAddress()));
        accommodation.setAddress(address);
        return accommodationMapper.toDto(accommodationRepository.save(accommodation));
    }

    @Override
    public List<AccommodationResponseDto> findAll(Pageable pageable) {
        return accommodationRepository.findAll(pageable).stream()
                .map(accommodationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccommodationResponseDto findById(Long id) {
        return accommodationMapper.toDto(
                accommodationRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException("Can't find accommodation by id " + id))
                );
    }

    @Override
    public AccommodationResponseDto update(
            Long id,
            UpdateAccommodationRequestDto updateRequestDto
    ) {
        Accommodation accommodation = updateAccommodation(id, updateRequestDto);

        if (updateRequestDto.getAddress() == null) {
            Accommodation updatedAccommodation = accommodationRepository
                    .save(accommodation);
            return accommodationMapper.toDto(updatedAccommodation);
        } else {
            Address address = updateAddress(id, updateRequestDto);
            Accommodation updatedAccommodation = accommodationRepository
                    .save(accommodation
                            .setAddress(address));
            return accommodationMapper.toDto(updatedAccommodation);
        }
    }

    @Override
    public void deleteById(Long id) {
        accommodationRepository.deleteById(id);
    }

    private Address updateAddress(Long id,
                                  UpdateAccommodationRequestDto updateRequestDto) {
        Accommodation accommodation = accommodationRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Accommodation by id " + id
                        + "wasn't found")
        );
        Address address = addressRepository.findById(
                        accommodation.getAddress().getId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Can't find address by id " + id)
                );
        UpdateAddressRequestDto addressRequestDto = updateRequestDto.getAddress();
        if (addressRequestDto.getCountry() != null) {
            address.setCountry(addressRequestDto.getCountry());
        }
        if (addressRequestDto.getCity() != null) {
            address.setCity(addressRequestDto.getCity());
        }
        if (addressRequestDto.getFirstAddressLine() != null) {
            address.setFirstAddressLine(addressRequestDto.getFirstAddressLine());
        }
        if (addressRequestDto.getSecondAddressLine() != null) {
            address.setSecondAddressLine(addressRequestDto.getSecondAddressLine());
        }
        if (addressRequestDto.getZipcode() != null) {
            address.setZipcode(addressRequestDto.getZipcode());
        }
        return addressRepository.save(address);
    }

    private Accommodation updateAccommodation(Long id,
                                              UpdateAccommodationRequestDto updateRequestDto) {
        Accommodation accommodation = accommodationRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Accommodation by id " + id
                        + "wasn't found")
        );
        if (updateRequestDto.getAmenities() != null) {
            accommodation.setAmenities(updateRequestDto.getAmenities());
        }
        if (updateRequestDto.getSize() != null) {
            accommodation.setSize(updateRequestDto.getSize());
        }
        if (updateRequestDto.getType() != null) {
            accommodation.setType(
                    Accommodation.Type.valueOf(updateRequestDto.getType().toUpperCase())
            );
        }
        if (updateRequestDto.getDailyRate() != null) {
            accommodation.setDailyRate(updateRequestDto.getDailyRate());
        }
        if (updateRequestDto.getAvailability() != null) {
            accommodation.setAvailability(updateRequestDto.getAvailability());
        }
        return accommodation;
    }
}
