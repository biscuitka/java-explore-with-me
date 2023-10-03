package ru.practicum.ewm.service.user.service;

import org.springframework.data.domain.Pageable;
import ru.practicum.ewm.service.user.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);

    List<UserDto> getAllUsers(List<Long> ids, Pageable pageable);

    void deleteUserById(long userId);
}
