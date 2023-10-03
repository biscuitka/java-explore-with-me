package ru.practicum.ewm.service.event.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.ewm.service.category.model.Category;
import ru.practicum.ewm.service.event.dto.EventFullDto;
import ru.practicum.ewm.service.event.dto.EventShortDto;
import ru.practicum.ewm.service.event.dto.NewEventDto;
import ru.practicum.ewm.service.event.model.Event;
import ru.practicum.ewm.service.location.model.Location;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "category", source = "category")
    @Mapping(target = "location", source = "location")
    @Mapping(target = "id", ignore = true)
    Event fromDtoToEvent(NewEventDto dto, Category category, Location location);

    EventFullDto fromEventToFullDto(Event event);

    List<EventFullDto> fromEventListToFullDto(List<Event> events);

    EventShortDto fromEventToShortDto(Event event);

    List<EventShortDto> fromEventListToShortDto(List<Event> events);
}
