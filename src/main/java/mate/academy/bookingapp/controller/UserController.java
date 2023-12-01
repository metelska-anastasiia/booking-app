package mate.academy.bookingapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.bookingapp.dto.user.UserInfoResponseDto;
import mate.academy.bookingapp.dto.user.UserRoleUpdateRequestDto;
import mate.academy.bookingapp.dto.user.UserUpdateInfoDto;
import mate.academy.bookingapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PutMapping("/{id}/role")
    @ResponseStatus(HttpStatus.OK)
    public UserInfoResponseDto updateRole(
            Authentication authentication,
            @PathVariable Long id,
            @RequestBody @Valid
            UserRoleUpdateRequestDto updateRequestDto) {
        return userService.updateRole(authentication, id, updateRequestDto);
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public UserInfoResponseDto retrieveUserInfo(Authentication authentication) {
        return userService.getUserInformation(authentication);
    }

    @PatchMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public UserInfoResponseDto updateUsersInfo(Authentication authentication,
                                               @RequestBody @Valid UserUpdateInfoDto requestDto) {
        return userService.updateUserInfo(authentication, requestDto);
    }
}
