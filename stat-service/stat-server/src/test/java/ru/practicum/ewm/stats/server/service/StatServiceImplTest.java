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
import ru.practicum.ewm.stats.dto.ViewStatsRequestDto;
import ru.practicum.ewm.stats.server.model.Application;
import ru.practicum.ewm.stats.server.model.EndpointHit;
import ru.practicum.ewm.stats.server.model.ViewStats;
import ru.practicum.ewm.stats.server.repository.ApplicationRepository;
import ru.practicum.ewm.stats.server.repository.EndpointHitRepository;
import ru.practicum.ewm.stats.server.utils.DataTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class StatServiceImplTest {

    @Mock
    EndpointHitRepository endpointHitRepository;

    @Mock
    ApplicationRepository applicationRepository;

    @InjectMocks
    StatServiceImpl statService;

    @Test
    void createHit() {
        EndpointHitDto hitDto = DataTest.testEndpointHitDto();
        Application app = DataTest.testApplication();
        EndpointHit hit = DataTest.testEndpointHit1();
        hit.setApp(app);
        when(applicationRepository.findByName(any())).thenReturn(Optional.of(app));
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
        ViewStatsRequestDto viewStatsRequestDto = DataTest.testViewStatsRequestDto();
        when(endpointHitRepository.findViewStatsByUniqueIp(any(LocalDateTime.class), any(LocalDateTime.class), anyList()))
                .thenReturn(List.of(viewStat));

        List<ViewStatsDto> result = statService.getViewStats(viewStatsRequestDto);

        Assertions.assertEquals(1, result.size());
    }

    @Test
    void getViewStatsByNonUniqueIp() {
        ViewStats viewStat = DataTest.testViewStats();
        ViewStatsRequestDto viewStatsRequestDto = DataTest.testViewStatsRequestDto();
        viewStatsRequestDto.setUnique(false);
        when(endpointHitRepository.findViewStatsByNonUniqueIp(any(LocalDateTime.class), any(LocalDateTime.class), anyList()))
                .thenReturn(List.of(viewStat));

        List<ViewStatsDto> result = statService.getViewStats(viewStatsRequestDto);

        Assertions.assertEquals(1, result.size());
    }

    @Test
    void getViewStatsWithoutUris() {
        ViewStats viewStat = DataTest.testViewStats();
        ViewStatsRequestDto viewStatsRequestDto = DataTest.testViewStatsRequestDto2();
        when(endpointHitRepository.findViewStatsByNonUniqueIp(any(LocalDateTime.class), any(LocalDateTime.class), anyList()))
                .thenReturn(List.of(viewStat));

        List<ViewStatsDto> result = statService.getViewStats(viewStatsRequestDto);

        Assertions.assertEquals(1, result.size());
    }
}