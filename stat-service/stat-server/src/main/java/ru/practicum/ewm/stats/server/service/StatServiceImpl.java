package ru.practicum.ewm.stats.server.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.stats.dto.EndpointHitDto;
import ru.practicum.ewm.stats.dto.ViewStatsDto;
import ru.practicum.ewm.stats.dto.ViewStatsRequestDto;
import ru.practicum.ewm.stats.server.mapper.StatMapper;
import ru.practicum.ewm.stats.server.model.Application;
import ru.practicum.ewm.stats.server.model.EndpointHit;
import ru.practicum.ewm.stats.server.model.ViewStats;
import ru.practicum.ewm.stats.server.repository.ApplicationRepository;
import ru.practicum.ewm.stats.server.repository.EndpointHitRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class StatServiceImpl implements StatService {
    private final EndpointHitRepository endpointHitRepository;
    private final ApplicationRepository applicationRepository;

    @Override
    public EndpointHitDto createHit(EndpointHitDto endpointHitDto) {
        Optional<Application> existingApplication = applicationRepository.findByName(endpointHitDto.getApp());
        Application app;
        if (existingApplication.isEmpty()) {
            app = new Application();
            app.setName(endpointHitDto.getApp());
            log.info("Сохранение приложения: {}", app.getName());
            app = applicationRepository.save(app);
        } else {
            app = existingApplication.get();
            log.info("Приложение уже сохранено: {}", app.getName());
        }

        EndpointHit hit = StatMapper.fromDtoToHit(endpointHitDto, app);
        EndpointHit createdHit = endpointHitRepository.save(hit);
        return StatMapper.fromHitToDto(createdHit);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ViewStatsDto> getViewStats(ViewStatsRequestDto viewStatsRequestDto) {
        LocalDateTime start = viewStatsRequestDto.getStart();
        LocalDateTime end = viewStatsRequestDto.getEnd();
        List<String> uris = viewStatsRequestDto.getUris();
        List<ViewStats> viewStatsList;
        if (viewStatsRequestDto.isUnique()) {
            log.info("Запрос статистики c уникальными IPs");
            viewStatsList = endpointHitRepository.findViewStatsByUniqueIp(start, end, uris);
        } else {
            log.info("Запрос статистики c НЕуникальными IPs");
            viewStatsList = endpointHitRepository.findViewStatsByNonUniqueIp(start, end, uris);
        }
        return StatMapper.fromListViewStatToDto(viewStatsList);
    }
}
