package ru.practicum.ewm.service.location.mapper;

import org.mapstruct.Mapper;
import ru.practicum.ewm.service.location.dto.LocationDto;
import ru.practicum.ewm.service.location.model.Location;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    LocationDto fromLocationToDto(Location location);

    Location fromDtoToLocation(LocationDto dto);
}
