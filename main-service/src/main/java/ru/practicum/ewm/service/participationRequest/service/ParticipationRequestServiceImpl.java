package ru.practicum.ewm.service.participationRequest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.service.event.model.Event;
import ru.practicum.ewm.service.event.model.EventState;
import ru.practicum.ewm.service.event.repository.EventRepository;
import ru.practicum.ewm.service.exception.ConflictException;
import ru.practicum.ewm.service.exception.NotFoundException;
import ru.practicum.ewm.service.participationRequest.dto.ParticipationRequestDto;
import ru.practicum.ewm.service.participationRequest.mapper.ParticipationRequestMapper;
import ru.practicum.ewm.service.participationRequest.model.ParticipationRequest;
import ru.practicum.ewm.service.participationRequest.model.ParticipationRequestStatus;
import ru.practicum.ewm.service.participationRequest.repository.ParticipationRequestRepository;
import ru.practicum.ewm.service.user.model.User;
import ru.practicum.ewm.service.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ParticipationRequestServiceImpl implements ParticipationRequestService {
    private final ParticipationRequestRepository requestRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final ParticipationRequestMapper requestMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ParticipationRequestDto> getAllRequestsByRequester(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        return requestMapper.fromListRequestToDto(requestRepository.findAllByRequesterId(userId));
    }

    @Override
    public ParticipationRequestDto createRequest(Long userId, Long eventId) {
        User requester = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Событие не найдено"));

        if (event.getInitiator().getId().equals(userId)) {
            throw new ConflictException("Запрос не может быть выполнен инициатором");
        }
        if (!event.getState().equals(EventState.PUBLISHED)) {
            throw new ConflictException("Запрос не может быть выполнен к неопубликованному событию");
        }

        if (event.getParticipantLimit() > 0) {
            if (requestRepository.getCountByEventIdAndStatus(eventId, ParticipationRequestStatus.CONFIRMED) >= event.getParticipantLimit()) {
                throw new ConflictException("Лимит участников достигнут");
            }
        }

        ParticipationRequest participationRequest = new ParticipationRequest();
        participationRequest.setRequester(requester);
        participationRequest.setEvent(event);
        participationRequest.setCreated(LocalDateTime.now());

        if (event.getParticipantLimit() != 0 && event.getRequestModeration()) {
            participationRequest.setStatus(ParticipationRequestStatus.PENDING);
        } else {
            participationRequest.setStatus(ParticipationRequestStatus.CONFIRMED);
        }
        ParticipationRequest savedRequest = requestRepository.save(participationRequest);

        return requestMapper.fromRequestToDto(savedRequest);
    }

    @Override
    public ParticipationRequestDto cancelRequestByRequester(Long userId, Long requestId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        ParticipationRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException("Запрос не найден"));

        if (!request.getRequester().getId().equals(userId)) {
            throw new NotFoundException("Запрос для данного пользователя не найден");
        }

        request.setStatus(ParticipationRequestStatus.CANCELED);

        ParticipationRequest canceledRequest = requestRepository.save(request);

        return requestMapper.fromRequestToDto(canceledRequest);
    }
}
