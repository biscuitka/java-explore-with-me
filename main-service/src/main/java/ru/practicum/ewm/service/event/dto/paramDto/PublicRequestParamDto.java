package ru.practicum.ewm.service.event.dto.paramDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ru.practicum.ewm.service.event.model.SortEvent;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PublicRequestParamDto {
    String text;
    List<Long> categories;
    Boolean paid;
    LocalDateTime rangeStart;
    LocalDateTime rangeEnd;
    boolean onlyAvailable;
    SortEvent sort;
    HttpServletRequest request;
}
