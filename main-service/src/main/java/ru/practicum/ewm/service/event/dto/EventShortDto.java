package ru.practicum.ewm.service.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.ewm.service.category.dto.CategoryDto;
import ru.practicum.ewm.service.constants.UtilConstants;
import ru.practicum.ewm.service.user.dto.UserShortDto;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventShortDto {
    Long id;
    String annotation;
    String title;

    CategoryDto category;
    UserShortDto initiator;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = UtilConstants.DATETIME_FORMAT)
    LocalDateTime eventDate;

    Boolean paid;
    Long confirmedRequests;
    Long views;
}

