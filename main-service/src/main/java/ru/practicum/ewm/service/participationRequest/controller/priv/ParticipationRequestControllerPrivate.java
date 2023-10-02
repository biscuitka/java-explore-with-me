package ru.practicum.ewm.service.participationRequest.controller.priv;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.service.participationRequest.dto.ParticipationRequestDto;
import ru.practicum.ewm.service.participationRequest.service.ParticipationRequestService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "/users/{userId}/requests")
@RequiredArgsConstructor
public class ParticipationRequestControllerPrivate {
    private final ParticipationRequestService requestService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ParticipationRequestDto> getAll(@PathVariable long userId) {
        return requestService.getAllRequestsByRequester(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipationRequestDto create(@PathVariable long userId, @RequestParam long eventId) {
        log.info("Создание запроса к событию с id {} от пользователя: {}", eventId, userId);
        return requestService.createRequest(userId, eventId);
    }

    @PatchMapping("/{requestId}/cancel")
    @ResponseStatus(HttpStatus.OK)
    public ParticipationRequestDto cancel(@PathVariable long userId, @PathVariable long requestId) {
        log.info("Отмена своего запроса на участие: {}", requestId);
        return requestService.cancelRequestByRequester(userId, requestId);
    }
}
