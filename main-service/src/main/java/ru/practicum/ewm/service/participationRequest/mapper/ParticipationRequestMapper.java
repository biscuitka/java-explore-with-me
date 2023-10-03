package ru.practicum.ewm.service.participationRequest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.ewm.service.participationRequest.dto.ParticipationRequestDto;
import ru.practicum.ewm.service.participationRequest.model.ParticipationRequest;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParticipationRequestMapper {
    @Mapping(target = "requester", source = "participationRequest.requester.id")
    @Mapping(target = "event", source = "participationRequest.event.id")
    ParticipationRequestDto fromRequestToDto(ParticipationRequest participationRequest);

    List<ParticipationRequestDto> fromListRequestToDto(List<ParticipationRequest> participationRequests);
}
