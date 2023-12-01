package mate.academy.bookingapp.service;

import java.util.List;
import mate.academy.bookingapp.dto.accommodation.AccommodationResponseDto;
import mate.academy.bookingapp.dto.accommodation.CreateAccommodationRequestDto;
import mate.academy.bookingapp.dto.accommodation.UpdateAccommodationRequestDto;
import org.springframework.data.domain.Pageable;

public interface AccommodationService {
    AccommodationResponseDto save(
            CreateAccommodationRequestDto createAccommodationRequestDto);

    List<AccommodationResponseDto> findAll(Pageable pageable);

    AccommodationResponseDto findById(Long id);

    AccommodationResponseDto update(
            Long id,
            UpdateAccommodationRequestDto updateRequestDto
    );

    void deleteById(Long id);
}
