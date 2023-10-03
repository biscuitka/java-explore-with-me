package ru.practicum.ewm.service.user.mapper;

import org.mapstruct.Mapper;
import ru.practicum.ewm.service.user.dto.UserDto;
import ru.practicum.ewm.service.user.dto.UserShortDto;
import ru.practicum.ewm.service.user.model.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto fromUserToDto(User user);

    List<UserDto> fromListUsersToDto(List<User> users);

    User fromDtotoUser(UserDto userDto);

    UserShortDto fromUserToUserShortDto(User user);
}
