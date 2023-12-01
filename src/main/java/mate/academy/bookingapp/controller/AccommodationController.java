package mate.academy.bookingapp.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.bookingapp.dto.accommodation.AccommodationResponseDto;
import mate.academy.bookingapp.dto.accommodation.CreateAccommodationRequestDto;
import mate.academy.bookingapp.dto.accommodation.UpdateAccommodationRequestDto;
import mate.academy.bookingapp.service.AccommodationService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accommodations")
public class AccommodationController {
    private final AccommodationService accommodationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public AccommodationResponseDto createAccommodation(
            @RequestBody @Valid CreateAccommodationRequestDto requestDto) {
        return accommodationService.save(requestDto);
    }

    @GetMapping
    public List<AccommodationResponseDto> findAll(Pageable pageable) {
        return accommodationService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public AccommodationResponseDto findById(@PathVariable Long id) {
        return accommodationService.findById(id);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public AccommodationResponseDto update(@PathVariable Long id,
                                           @RequestBody @Valid
                                           UpdateAccommodationRequestDto requestDto) {
        return accommodationService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long id) {
        accommodationService.deleteById(id);
    }
}
