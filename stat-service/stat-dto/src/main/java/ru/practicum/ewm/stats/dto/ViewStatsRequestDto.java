package ru.practicum.ewm.stats.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ViewStatsRequestDto {
    LocalDateTime start;
    LocalDateTime end;
    List<String> uris;
    boolean unique;
}
