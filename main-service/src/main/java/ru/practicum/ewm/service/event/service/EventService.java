package ru.practicum.ewm.service.event.service;

import org.springframework.data.domain.Pageable;
import ru.practicum.ewm.service.event.dto.*;
import ru.practicum.ewm.service.event.dto.paramDto.AdminRequestParamDto;
import ru.practicum.ewm.service.event.dto.paramDto.PublicRequestParamDto;
import ru.practicum.ewm.service.participationRequest.dto.EventRequestStatusUpdateRequest;
import ru.practicum.ewm.service.participationRequest.dto.EventRequestStatusUpdateResult;
import ru.practicum.ewm.service.participationRequest.dto.ParticipationRequestDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface EventService {
    List<EventFullDto> getAllByAdmin(AdminRequestParamDto requestParamDto, Pageable pageable);

    List<EventShortDto> getAllByInitiator(Long userId, Pageable pageable);

    EventFullDto getByIdByInitiator(Long userId, Long eventId);

    List<ParticipationRequestDto> getRequestsByEventId(Long userId, Long eventId);

    List<EventShortDto> getAllPublishedEvents(PublicRequestParamDto paramDto, Pageable pageable);

    EventFullDto getPublishedEventById(Long eventId, HttpServletRequest request);

    EventFullDto createByInitiator(Long userId, NewEventDto newEventDto);

    EventFullDto updateByAdmin(UpdateEventAdminRequest adminRequest, Long eventId);

    EventFullDto updateByInitiator(Long userId, Long eventId, UpdateEventUserRequest userRequest);

    EventRequestStatusUpdateResult updateStatusOfRequests(Long userId, Long eventId,
                                                          EventRequestStatusUpdateRequest statusUpdateRequest);
}
