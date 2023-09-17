package ru.practicum.ewm.stats.server.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.ewm.stats.dto.EndpointHitDto;
import ru.practicum.ewm.stats.dto.ViewStatsDto;
import ru.practicum.ewm.stats.server.model.EndpointHit;
import ru.practicum.ewm.stats.server.model.ViewStats;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class StatMapper {
    public static EndpointHitDto fromHitToDto(EndpointHit hit) {
        return EndpointHitDto.builder()
                .app(hit.getApp())
                .uri(hit.getUri())
                .ip(hit.getIp())
                .time(hit.getTimestamp())
                .build();
    }

    public static EndpointHit fromDtoToHit(EndpointHitDto dto) {
        EndpointHit hit = new EndpointHit();
        hit.setApp(dto.getApp());
        hit.setUri(dto.getUri());
        hit.setIp(dto.getIp());
        hit.setTimestamp(dto.getTime());
        return hit;
    }

    public static ViewStatsDto fromViewStatToDto(ViewStats viewStats) {
        return ViewStatsDto.builder()
                .app(viewStats.getApp())
                .uri(viewStats.getUri())
                .hits(viewStats.getHits())
                .build();
    }

    public static List<ViewStatsDto> fromListViewStatToDto(List<ViewStats> viewStatsList) {
        return viewStatsList.stream()
                .map(StatMapper::fromViewStatToDto)
                .collect(Collectors.toList());
    }
}
