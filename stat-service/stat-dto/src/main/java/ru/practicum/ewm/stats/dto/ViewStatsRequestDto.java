package ru.practicum.ewm.stats.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ViewStatsRequestDto {
    LocalDateTime start;
    LocalDateTime end;
    List<String> uris;
    boolean unique;
}
