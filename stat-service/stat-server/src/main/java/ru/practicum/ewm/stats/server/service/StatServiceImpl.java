package ru.practicum.ewm.stats.server.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.stats.dto.EndpointHitDto;
import ru.practicum.ewm.stats.dto.ViewStatsDto;
import ru.practicum.ewm.stats.server.mapper.StatMapper;
import ru.practicum.ewm.stats.server.model.EndpointHit;
import ru.practicum.ewm.stats.server.model.ViewStats;
import ru.practicum.ewm.stats.server.repository.EndpointHitRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class StatServiceImpl implements StatService {
    private final EndpointHitRepository endpointHitRepository;

    @Override
    public EndpointHitDto createHit(EndpointHitDto endpointHitDto) {
        EndpointHit hit = StatMapper.fromDtoToHit(endpointHitDto);
        EndpointHit createdHit = endpointHitRepository.save(hit);
        return StatMapper.fromHitToDto(createdHit);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ViewStatsDto> getViewStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique) {
        List<ViewStats> viewStatsList;
        if (unique) {
            log.info("Запрос статистики c уникальными IPs");
            viewStatsList = endpointHitRepository.findViewStatsByUniqueIp(start, end, uris);
        } else {
            log.info("Запрос статистики c НЕуникальными IPs");
            viewStatsList = endpointHitRepository.findViewStatsByNonUniqueIp(start, end, uris);
        }
        return StatMapper.fromListViewStatToDto(viewStatsList);
    }
}
