package ru.practicum.ewm.service.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.service.exception.NotFoundException;
import ru.practicum.ewm.service.user.dto.UserDto;
import ru.practicum.ewm.service.user.mapper.UserMapper;
import ru.practicum.ewm.service.user.model.User;
import ru.practicum.ewm.service.user.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.fromDtotoUser(userDto);
        User createdUser = userRepository.save(user);
        return userMapper.fromUserToDto(createdUser);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers(List<Long> ids, Pageable pageable) {
        List<User> users;
        if (ids != null && !ids.isEmpty()) {
            users = userRepository.findAllByIdIn(ids, pageable);
        } else {
            users = userRepository.findAll(pageable).getContent();
        }

        return userMapper.fromListUsersToDto(users);
    }

    @Override
    public void deleteUserById(long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User with id=" + userId + " was not found"));
        userRepository.deleteById(userId);
    }
}
