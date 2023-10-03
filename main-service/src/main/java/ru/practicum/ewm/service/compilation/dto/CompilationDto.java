package ru.practicum.ewm.service.compilation.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.ewm.service.event.dto.EventShortDto;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompilationDto {
    Long id;
    Boolean pinned;
    String title;
    List<EventShortDto> events;
}
