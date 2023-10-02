package ru.practicum.ewm.service.event.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.service.constants.HeaderConstants;
import ru.practicum.ewm.service.constants.UtilConstants;
import ru.practicum.ewm.service.event.dto.EventFullDto;
import ru.practicum.ewm.service.event.dto.UpdateEventAdminRequest;
import ru.practicum.ewm.service.event.dto.paramDto.AdminRequestParamDto;
import ru.practicum.ewm.service.event.model.EventState;
import ru.practicum.ewm.service.event.service.EventService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "/admin/events")
@RequiredArgsConstructor
public class EventControllerAdmin {

    private final EventService eventService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EventFullDto> getAll(@RequestParam(required = false) List<Long> users,
                                     @RequestParam(required = false) List<EventState> states,
                                     @RequestParam(required = false) List<Long> categories,
                                     @RequestParam(required = false) @DateTimeFormat(pattern = UtilConstants.DATETIME_FORMAT) LocalDateTime rangeStart,
                                     @RequestParam(required = false) @DateTimeFormat(pattern = UtilConstants.DATETIME_FORMAT) LocalDateTime rangeEnd,
                                     @Valid @RequestParam(defaultValue = HeaderConstants.DEFAULT_FROM_VALUE) @Min(0) int from,
                                     @Valid @RequestParam(defaultValue = HeaderConstants.DEFAULT_SIZE_VALUE) int size) {
        log.info("Запрос событий администратором");
        AdminRequestParamDto requestParamDto = new AdminRequestParamDto(users, states,
                categories, rangeStart, rangeEnd);
        Pageable pageable = PageRequest.of(from / size, size);
        return eventService.getAllByAdmin(requestParamDto, pageable);
    }

    @PatchMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public EventFullDto update(@Valid @RequestBody UpdateEventAdminRequest adminRequest, @PathVariable long eventId) {
        log.info("Обновление события администратором: {}", adminRequest);
        return eventService.updateByAdmin(adminRequest, eventId);
    }
}
