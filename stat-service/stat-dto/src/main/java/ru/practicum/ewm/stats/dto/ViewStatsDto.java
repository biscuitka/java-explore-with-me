package ru.practicum.ewm.stats.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ViewStatsDto {
    String app;
    String uri;
    Long hits;
}
