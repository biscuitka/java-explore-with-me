package ru.practicum.ewm.service.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.service.constants.HeaderConstants;
import ru.practicum.ewm.service.user.dto.UserDto;
import ru.practicum.ewm.service.user.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Slf4j
@Validated
@RequestMapping(path = "/admin/users")
@RequiredArgsConstructor
public class UserControllerAdmin {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@Valid @RequestBody UserDto userDto) {
        log.info("Создание пользователя: {}", userDto);
        return userService.createUser(userDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAll(@RequestParam(required = false) List<Long> ids,
                                @RequestParam(defaultValue = HeaderConstants.DEFAULT_FROM_VALUE) @Min(0) int from,
                                @RequestParam(defaultValue = HeaderConstants.DEFAULT_SIZE_VALUE) int size) {
        Pageable pageable = PageRequest.of(from / size, size);
        log.info("Запрос администратором всех пользователей");
        return userService.getAllUsers(ids, pageable);

    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable long userId) {
        log.info("Удаление пользователя по id: {}", userId);
        userService.deleteUserById(userId);
    }
}
