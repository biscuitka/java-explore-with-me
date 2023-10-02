package ru.practicum.ewm.service.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.ewm.service.category.dto.CategoryDto;
import ru.practicum.ewm.service.constants.UtilConstants;
import ru.practicum.ewm.service.event.model.EventState;
import ru.practicum.ewm.service.location.model.Location;
import ru.practicum.ewm.service.user.dto.UserShortDto;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventFullDto {
    Long id;

    String annotation;
    String title;
    String description;
    Integer participantLimit;

    CategoryDto category;
    UserShortDto initiator;
    Location location;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = UtilConstants.DATETIME_FORMAT)
    LocalDateTime createdOn;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = UtilConstants.DATETIME_FORMAT)
    LocalDateTime publishedOn;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = UtilConstants.DATETIME_FORMAT)
    LocalDateTime eventDate;

    EventState state;
    Boolean paid;

    Boolean requestModeration;
    Long confirmedRequests;
    Long views;
}

