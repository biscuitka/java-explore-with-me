package ru.practicum.ewm.stats.server.repository;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.practicum.ewm.stats.dto.ViewStatsDto;
import ru.practicum.ewm.stats.server.model.EndpointHit;
import ru.practicum.ewm.stats.server.model.ViewStats;
import ru.practicum.ewm.stats.server.utils.DataTest;

import java.util.List;

@DataJpaTest
@FieldDefaults(level = AccessLevel.PRIVATE)
class EndpointHitRepositoryTest {

    @Autowired
    private EndpointHitRepository endpointHitRepository;

    @BeforeEach
    void saveAll() {
        EndpointHit endpointHit1 = DataTest.testEndpointHit1();
        EndpointHit endpointHit2 = DataTest.testEndpointHit1_1();
        EndpointHit endpointHit3 = DataTest.testEndpointHit2();
        EndpointHit endpointHit4 = DataTest.testEndpointHit3();
        EndpointHit endpointHit5 = DataTest.testEndpointHit3_3();
        endpointHitRepository.saveAll(List.of(endpointHit1, endpointHit2, endpointHit3, endpointHit4, endpointHit5));
    }

    @AfterEach
    void deleteAll() {
        endpointHitRepository.deleteAll();
    }

    @Test
    void findViewStatsByUniqueIp() {
        ViewStatsDto viewStatsDto1 = DataTest.testViewStatsDto();
        ViewStatsDto viewStatsDto2 = DataTest.testViewStatsDto2();
        List<ViewStats> result = endpointHitRepository.findViewStatsByUniqueIp(DataTest.time.minusYears(2),
                DataTest.time.plusYears(2), List.of("/events/2"));
        List<ViewStats> result2 = endpointHitRepository.findViewStatsByUniqueIp(DataTest.time.minusYears(2),
                DataTest.time.plusYears(2), List.of("/events/1"));

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(2L, result.get(0).getHits());
        Assertions.assertEquals(viewStatsDto2.getApp(), result.get(0).getApp());
        Assertions.assertEquals(viewStatsDto2.getUri(), result.get(0).getUri());

        Assertions.assertNotNull(result2);
        Assertions.assertEquals(1, result2.size());
        Assertions.assertEquals(1L, result2.get(0).getHits());
        Assertions.assertEquals(viewStatsDto1.getApp(), result2.get(0).getApp());
        Assertions.assertEquals(viewStatsDto1.getUri(), result2.get(0).getUri());

    }

    @Test
    void findViewStatsByNonUniqueIp() {
        ViewStatsDto viewStatsDto1 = DataTest.testViewStatsDto();
        ViewStatsDto viewStatsDto2 = DataTest.testViewStatsDto2();

        List<ViewStats> result = endpointHitRepository.findViewStatsByNonUniqueIp(DataTest.time.minusYears(2),
                DataTest.time.plusYears(2), List.of("/events/2"));
        List<ViewStats> result2 = endpointHitRepository.findViewStatsByNonUniqueIp(DataTest.time.minusYears(2),
                DataTest.time.plusYears(2), List.of("/events/1"));

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(3L, result.get(0).getHits());
        Assertions.assertEquals(viewStatsDto2.getApp(), result.get(0).getApp());
        Assertions.assertEquals(viewStatsDto2.getUri(), result.get(0).getUri());

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result2.size());
        Assertions.assertEquals(2L, result2.get(0).getHits());
        Assertions.assertEquals(viewStatsDto1.getApp(), result2.get(0).getApp());
        Assertions.assertEquals(viewStatsDto1.getUri(), result2.get(0).getUri());
    }
}