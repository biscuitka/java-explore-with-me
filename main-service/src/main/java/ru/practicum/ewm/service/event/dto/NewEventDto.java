package ru.practicum.ewm.service.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.ewm.service.constants.UtilConstants;
import ru.practicum.ewm.service.location.dto.LocationDto;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewEventDto {

    @NotBlank
    @Size(min = 20, max = 2000)
    String annotation;

    @NotBlank
    @Size(min = 3, max = 120)
    String title;

    @NotBlank
    @Size(min = 20, max = 7000)
    String description;

    @NotNull
    Long category;

    @NotNull
    LocationDto location;

    @NotNull
    @Future(message = "Событие должно быть в будущем")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = UtilConstants.DATETIME_FORMAT)
    LocalDateTime eventDate;

    Boolean paid;
    Integer participantLimit;
    Boolean requestModeration;
}
