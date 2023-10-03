package ru.practicum.ewm.service.event.dto.paramDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ru.practicum.ewm.service.event.model.EventState;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminRequestParamDto {
    List<Long> userIds;
    List<EventState> states;
    List<Long> categoryIds;
    LocalDateTime rangeStart;
    LocalDateTime rangeEnd;
}
