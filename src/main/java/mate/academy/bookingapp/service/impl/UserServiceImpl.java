package mate.academy.bookingapp.service.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.bookingapp.dto.user.UserInfoResponseDto;
import mate.academy.bookingapp.dto.user.UserRegistrationRequestDto;
import mate.academy.bookingapp.dto.user.UserRegistrationResponseDto;
import mate.academy.bookingapp.dto.user.UserRoleUpdateRequestDto;
import mate.academy.bookingapp.dto.user.UserUpdateInfoDto;
import mate.academy.bookingapp.exception.EntityNotFoundException;
import mate.academy.bookingapp.exception.RegistrationException;
import mate.academy.bookingapp.mapper.UserMapper;
import mate.academy.bookingapp.model.User;
import mate.academy.bookingapp.repository.UserRepository;
import mate.academy.bookingapp.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserRegistrationResponseDto register(UserRegistrationRequestDto request)
            throws RegistrationException {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RegistrationException("This user already exists");
        }
        User user = userMapper.toModel(request)
                .setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    public UserInfoResponseDto updateRole(Authentication authentication, Long id,
                                          UserRoleUpdateRequestDto updateRequestDto) {
        User existedUser = getUser(authentication);
        if (!existedUser.getId().equals(id)) {
            throw new RuntimeException("You can't update role for another user");
        }
        String roleFromDto = updateRequestDto.getRole().toUpperCase();
        existedUser.setRole(User.Role.valueOf(roleFromDto));
        User updatedUser = userRepository.save(existedUser);
        return userMapper.toUserInfoResponseDto(updatedUser);
    }

    @Override
    public UserInfoResponseDto updateUserInfo(Authentication authentication,
                                              UserUpdateInfoDto requestDto) {
        User existedUser = getUser(authentication);
        if (requestDto.getEmail() != null) {
            existedUser.setEmail(requestDto.getEmail());
        }
        if (requestDto.getFirstName() != null) {
            existedUser.setFirstName(requestDto.getFirstName());
        }
        if (requestDto.getLastName() != null) {
            existedUser.setLastName(requestDto.getLastName());
        }
        if (requestDto.getPassword() != null) {
            existedUser.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        }
        User updatedUser = userRepository.save(existedUser);
        return userMapper.toUserInfoResponseDto(updatedUser);
    }

    @Override
    public UserInfoResponseDto getUserInformation(Authentication authentication) {
        User user = getUser(authentication);
        return userMapper.toUserInfoResponseDto(user);
    }

    private User getUser(Authentication authentication) {
        String email = authentication.getName();
        return userRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("Can not find user by email" + email));
    }
}
