package ru.practicum.ewm.service.participationRequest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.ewm.service.constants.UtilConstants;
import ru.practicum.ewm.service.participationRequest.model.ParticipationRequestStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParticipationRequestDto {
    Long id;
    Long requester;
    Long event;
    ParticipationRequestStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = UtilConstants.DATETIME_FORMAT)
    LocalDateTime created;
}
