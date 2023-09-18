package ru.practicum.ewm.stats.server.service;

import ru.practicum.ewm.stats.dto.EndpointHitDto;
import ru.practicum.ewm.stats.dto.ViewStatsDto;
import ru.practicum.ewm.stats.dto.ViewStatsRequestDto;

import java.util.List;

public interface StatService {
    EndpointHitDto createHit(EndpointHitDto endpointHitDto);

    List<ViewStatsDto> getViewStats(ViewStatsRequestDto viewStatsRequestDto);
}
