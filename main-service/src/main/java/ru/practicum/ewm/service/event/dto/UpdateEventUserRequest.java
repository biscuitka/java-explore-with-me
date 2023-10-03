package ru.practicum.ewm.service.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.ewm.service.constants.UtilConstants;
import ru.practicum.ewm.service.event.dto.states.StateActionUser;
import ru.practicum.ewm.service.location.dto.LocationDto;

import javax.validation.constraints.Future;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateEventUserRequest {
    @Size(min = 20, max = 2000)
    String annotation;

    @Size(min = 3, max = 120)
    String title;

    @Size(min = 20, max = 7000)
    String description;

    Integer participantLimit;
    Long category;
    LocationDto location;

    @Future
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = UtilConstants.DATETIME_FORMAT)
    LocalDateTime eventDate;

    Boolean paid;
    Boolean requestModeration;
    StateActionUser stateAction;
}
