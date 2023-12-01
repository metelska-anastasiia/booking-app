package mate.academy.bookingapp.service;

import mate.academy.bookingapp.dto.user.UserInfoResponseDto;
import mate.academy.bookingapp.dto.user.UserRegistrationRequestDto;
import mate.academy.bookingapp.dto.user.UserRegistrationResponseDto;
import mate.academy.bookingapp.dto.user.UserRoleUpdateRequestDto;
import mate.academy.bookingapp.dto.user.UserUpdateInfoDto;
import mate.academy.bookingapp.exception.RegistrationException;
import org.springframework.security.core.Authentication;

public interface UserService {
    UserRegistrationResponseDto register(UserRegistrationRequestDto request)
            throws RegistrationException;

    UserInfoResponseDto updateRole(
            Authentication authentication,
            Long id,
            UserRoleUpdateRequestDto updateRequestDto
    );

    UserInfoResponseDto getUserInformation(Authentication authentication);

    UserInfoResponseDto updateUserInfo(Authentication authentication,
                                       UserUpdateInfoDto requestDto);
}

