package ru.practicum.ewm.stats.server.service;

import ru.practicum.ewm.stats.dto.EndpointHitDto;
import ru.practicum.ewm.stats.dto.ViewStatsDto;

import java.time.LocalDateTime;
import java.util.List;

public interface StatService {
    EndpointHitDto createHit(EndpointHitDto endpointHitDto);

    List<ViewStatsDto> getViewStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique);
}
