package ru.practicum.ewm.stats.server.utils;

import ru.practicum.ewm.stats.dto.EndpointHitDto;
import ru.practicum.ewm.stats.dto.ViewStatsDto;
import ru.practicum.ewm.stats.dto.ViewStatsRequestDto;
import ru.practicum.ewm.stats.server.model.Application;
import ru.practicum.ewm.stats.server.model.EndpointHit;
import ru.practicum.ewm.stats.server.model.ViewStats;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class DataTest {

    public static LocalDateTime time = LocalDateTime.now().withNano(0);

    public static EndpointHitDto testEndpointHitDto() {
        return EndpointHitDto.builder()
                .app("ewm-main-service")
                .uri("/events/1")
                .ip("192.163.0.1")
                .time(time)
                .build();
    }

    public static ViewStatsDto testViewStatsDto() {
        return ViewStatsDto.builder()
                .app("ewm-main-service")
                .uri("/events/1")
                .hits(1L)
                .build();
    }

    public static ViewStatsDto testViewStatsDto2() {
        return ViewStatsDto.builder()
                .app("ewm-main-service")
                .uri("/events/2")
                .hits(2L)
                .build();
    }

    public static Application testApplication() {
        Application application = new Application();
        application.setName("ewm-main-service");
        return application;
    }

    public static ViewStats testViewStats() {
        ViewStats viewStats = new ViewStats();
        viewStats.setApp(testApplication());
        viewStats.setUri("/events/1");
        viewStats.setHits(1L);
        return viewStats;
    }

    public static EndpointHit testEndpointHit1() {
        EndpointHit endpointHit = new EndpointHit();
        endpointHit.setId(1L);
        endpointHit.setUri("/events/1");
        endpointHit.setIp("192.163.0.1");
        endpointHit.setTimestamp(time);
        return endpointHit;
    }

    public static EndpointHit testEndpointHit1_1() {
        EndpointHit endpointHit = new EndpointHit();
        endpointHit.setId(2L);
        endpointHit.setUri("/events/1");
        endpointHit.setIp("192.163.0.1");
        endpointHit.setTimestamp(time);
        return endpointHit;
    }

    public static EndpointHit testEndpointHit2() {
        EndpointHit endpointHit = new EndpointHit();
        endpointHit.setId(3L);
        endpointHit.setUri("/events/2");
        endpointHit.setIp("192.163.0.1");
        endpointHit.setTimestamp(time);
        return endpointHit;
    }

    public static EndpointHit testEndpointHit3() {
        EndpointHit endpointHit = new EndpointHit();
        endpointHit.setId(4L);
        endpointHit.setUri("/events/2");
        endpointHit.setIp("192.163.0.2");
        endpointHit.setTimestamp(time);
        return endpointHit;
    }

    public static EndpointHit testEndpointHit3_3() {
        EndpointHit endpointHit = new EndpointHit();
        endpointHit.setId(5L);
        endpointHit.setUri("/events/2");
        endpointHit.setIp("192.163.0.2");
        endpointHit.setTimestamp(time);
        return endpointHit;
    }

    public static ViewStatsRequestDto testViewStatsRequestDto() {
        return new ViewStatsRequestDto(time.minusYears(2),
                time.plusYears(2), List.of("/events/1"), true);
    }

    public static ViewStatsRequestDto testViewStatsRequestDto2() {
        return new ViewStatsRequestDto(time.minusYears(2),
                time.plusYears(2), Collections.emptyList(), false);
    }
}
