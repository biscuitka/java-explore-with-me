package ru.practicum.ewm.service.event.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.service.constants.HeaderConstants;
import ru.practicum.ewm.service.constants.UtilConstants;
import ru.practicum.ewm.service.event.dto.EventFullDto;
import ru.practicum.ewm.service.event.dto.EventShortDto;
import ru.practicum.ewm.service.event.dto.paramDto.PublicRequestParamDto;
import ru.practicum.ewm.service.event.model.SortEvent;
import ru.practicum.ewm.service.event.service.EventService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
@Validated
@RequestMapping(path = "/events")
@RequiredArgsConstructor
public class EventControllerPublic {
    private final EventService eventService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EventShortDto> getAll(@RequestParam(defaultValue = "") String text,
                                      @RequestParam(required = false) List<Long> categories,
                                      @RequestParam(required = false) Boolean paid,
                                      @RequestParam(required = false) @DateTimeFormat(pattern = UtilConstants.DATETIME_FORMAT) LocalDateTime rangeStart,
                                      @RequestParam(required = false) @DateTimeFormat(pattern = UtilConstants.DATETIME_FORMAT) LocalDateTime rangeEnd,
                                      @RequestParam(defaultValue = "false") boolean onlyAvailable,
                                      @RequestParam(defaultValue = "VIEWS") SortEvent sort,
                                      @RequestParam(defaultValue = HeaderConstants.DEFAULT_FROM_VALUE) @Min(0) int from,
                                      @RequestParam(defaultValue = HeaderConstants.DEFAULT_SIZE_VALUE) int size,
                                      HttpServletRequest request) {
        log.info("Запрос всех событий с публичного API");
        PublicRequestParamDto requestParamDto = new PublicRequestParamDto(text, categories, paid, rangeStart,
                rangeEnd, onlyAvailable, sort, request);

        Pageable pageable = PageRequest.of(from / size, size);
        return eventService.getAllPublishedEvents(requestParamDto, pageable);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EventFullDto getById(@PathVariable long id,
                                HttpServletRequest request) {
        log.info("Запрос события с публичного API по id: {}", id);
        return eventService.getPublishedEventById(id, request);
    }
}
