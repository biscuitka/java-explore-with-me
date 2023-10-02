package ru.practicum.ewm.service.participationRequest.service;

import ru.practicum.ewm.service.participationRequest.dto.ParticipationRequestDto;

import java.util.List;

public interface ParticipationRequestService {
    List<ParticipationRequestDto> getAllRequestsByRequester(Long userId);

    ParticipationRequestDto createRequest(Long userId, Long eventId);

    ParticipationRequestDto cancelRequestByRequester(Long userId, Long requestId);
}
