package ru.practicum.ewm.service.event.controller.priv;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.service.constants.HeaderConstants;
import ru.practicum.ewm.service.event.dto.EventFullDto;
import ru.practicum.ewm.service.event.dto.EventShortDto;
import ru.practicum.ewm.service.event.dto.NewEventDto;
import ru.practicum.ewm.service.event.dto.UpdateEventUserRequest;
import ru.practicum.ewm.service.event.service.EventService;
import ru.practicum.ewm.service.participationRequest.dto.EventRequestStatusUpdateRequest;
import ru.practicum.ewm.service.participationRequest.dto.EventRequestStatusUpdateResult;
import ru.practicum.ewm.service.participationRequest.dto.ParticipationRequestDto;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "/users/{userId}/events")
@RequiredArgsConstructor
public class EventControllerPrivate {
    private final EventService eventService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EventShortDto> getAll(@PathVariable long userId,
                                      @RequestParam(defaultValue = HeaderConstants.DEFAULT_FROM_VALUE) @Min(0) int from,
                                      @RequestParam(defaultValue = HeaderConstants.DEFAULT_SIZE_VALUE) int size) {
        Pageable pageable = PageRequest.of(from / size, size);
        return eventService.getAllByInitiator(userId, pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventFullDto create(@PathVariable long userId,
                               @Valid @RequestBody NewEventDto newEventDto) {
        log.info("Создание события инициатором: {}", newEventDto);
        return eventService.createByInitiator(userId, newEventDto);
    }

    @GetMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public EventFullDto getById(@PathVariable long userId,
                                @PathVariable long eventId) {
        return eventService.getByIdByInitiator(userId, eventId);
    }

    @PatchMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public EventFullDto updateEvent(@PathVariable long userId,
                                    @PathVariable long eventId,
                                    @Valid @RequestBody UpdateEventUserRequest userRequest) {
        log.info("Обновление события инициатором: {}", userRequest);
        return eventService.updateByInitiator(userId, eventId, userRequest);
    }

    @GetMapping("/{eventId}/requests")
    @ResponseStatus(HttpStatus.OK)
    public List<ParticipationRequestDto> getRequestsByEventId(@PathVariable long userId,
                                                              @PathVariable long eventId) {
        return eventService.getRequestsByEventId(userId, eventId);
    }

    @PatchMapping("/{eventId}/requests")
    @ResponseStatus(HttpStatus.OK)
    public EventRequestStatusUpdateResult updateStatusOfRequests(@PathVariable long userId,
                                                                 @PathVariable long eventId,
                                                                 @Valid @RequestBody EventRequestStatusUpdateRequest statusUpdateRequest) {
        log.info("Обновление статусов запросов на участие: {}", statusUpdateRequest);
        return eventService.updateStatusOfRequests(userId, eventId, statusUpdateRequest);
    }
}
