package ru.practicum.ewm.stats.server.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.ewm.stats.dto.EndpointHitDto;
import ru.practicum.ewm.stats.dto.ViewStatsDto;
import ru.practicum.ewm.stats.server.model.EndpointHit;
import ru.practicum.ewm.stats.server.model.ViewStats;
import ru.practicum.ewm.stats.server.repository.EndpointHitRepository;
import ru.practicum.ewm.stats.server.utils.DataTest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class StatServiceImplTest {

    @Mock
    EndpointHitRepository endpointHitRepository;

    @InjectMocks
    StatServiceImpl statService;

    @Test
    void createHit() {
        EndpointHitDto hitDto = DataTest.testEndpointHitDto();
        EndpointHit hit = DataTest.testEndpointHit1();
        when(endpointHitRepository.save(any(EndpointHit.class)))
                .thenReturn(hit);

        EndpointHitDto resultDto = statService.createHit(hitDto);

        Assertions.assertNotNull(resultDto);
        Assertions.assertEquals(hitDto.getApp(), resultDto.getApp());
        Assertions.assertEquals(hitDto.getUri(), resultDto.getUri());
        Assertions.assertEquals(hitDto.getIp(), resultDto.getIp());

        verify(endpointHitRepository, times(1)).save(any(EndpointHit.class));
    }

    @Test
    void getViewStatsByUniqueIp() {
        ViewStats viewStat = DataTest.testViewStats();
        when(endpointHitRepository.findViewStatsByUniqueIp(any(LocalDateTime.class), any(LocalDateTime.class), anyList()))
                .thenReturn(List.of(viewStat));

        List<ViewStatsDto> result = statService.getViewStats(DataTest.time.minusYears(2),
                DataTest.time.plusYears(2), List.of("/events/1"), true);

        Assertions.assertEquals(1, result.size());
    }

    @Test
    void getViewStatsByNonUniqueIp() {
        ViewStats viewStat = DataTest.testViewStats();
        when(endpointHitRepository.findViewStatsByNonUniqueIp(any(LocalDateTime.class), any(LocalDateTime.class), anyList()))
                .thenReturn(List.of(viewStat));

        List<ViewStatsDto> result = statService.getViewStats(DataTest.time.minusYears(2),
                DataTest.time.plusYears(2), List.of("/events/1"), false);

        Assertions.assertEquals(1, result.size());
    }

    @Test
    void getViewStatsWithoutUris() {
        ViewStats viewStat = DataTest.testViewStats();
        when(endpointHitRepository.findViewStatsByNonUniqueIp(any(LocalDateTime.class), any(LocalDateTime.class), anyList()))
                .thenReturn(List.of(viewStat));

        List<ViewStatsDto> result = statService.getViewStats(DataTest.time.minusYears(2),
                DataTest.time.plusYears(2), Collections.emptyList(), false);

        Assertions.assertEquals(1, result.size());
    }
}