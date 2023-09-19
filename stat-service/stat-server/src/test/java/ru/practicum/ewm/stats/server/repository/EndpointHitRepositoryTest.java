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
import ru.practicum.ewm.stats.server.model.Application;
import ru.practicum.ewm.stats.server.model.EndpointHit;
import ru.practicum.ewm.stats.server.model.ViewStats;
import ru.practicum.ewm.stats.server.utils.DataTest;

import java.util.List;

@DataJpaTest
@FieldDefaults(level = AccessLevel.PRIVATE)
class EndpointHitRepositoryTest {

    @Autowired
    private EndpointHitRepository endpointHitRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @BeforeEach
    void saveAll() {
        Application app = DataTest.testApplication();
        EndpointHit endpointHit1 = DataTest.testEndpointHit1();
        endpointHit1.setApp(app);
        EndpointHit endpointHit2 = DataTest.testEndpointHit1_1();
        endpointHit2.setApp(app);
        EndpointHit endpointHit3 = DataTest.testEndpointHit2();
        endpointHit3.setApp(app);
        EndpointHit endpointHit4 = DataTest.testEndpointHit3();
        endpointHit4.setApp(app);
        EndpointHit endpointHit5 = DataTest.testEndpointHit3_3();
        endpointHit5.setApp(app);
        applicationRepository.save(app);
        endpointHitRepository.saveAll(List.of(endpointHit1, endpointHit2, endpointHit3, endpointHit4, endpointHit5));
    }

    @AfterEach
    void deleteAll() {
        endpointHitRepository.deleteAll();
    }

    @Test
    void findViewStatsByUniqueIpReturnTwoHits() {
        ViewStatsDto viewStatsDto = DataTest.testViewStatsDto2();
        List<ViewStats> result = endpointHitRepository.findViewStatsByUniqueIp(DataTest.time.minusYears(2),
                DataTest.time.plusYears(2), List.of("/events/2"));

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(2L, result.get(0).getHits());
        Assertions.assertEquals(viewStatsDto.getApp(), result.get(0).getApp().getName());
        Assertions.assertEquals(viewStatsDto.getUri(), result.get(0).getUri());
    }

    @Test
    void findViewStatsByNonUniqueIpReturnThreeHits() {
        ViewStatsDto viewStatsDto = DataTest.testViewStatsDto2();
        List<ViewStats> result = endpointHitRepository.findViewStatsByNonUniqueIp(DataTest.time.minusYears(2),
                DataTest.time.plusYears(2), List.of("/events/2"));

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(3L, result.get(0).getHits());
        Assertions.assertEquals(viewStatsDto.getApp(), result.get(0).getApp().getName());
        Assertions.assertEquals(viewStatsDto.getUri(), result.get(0).getUri());
    }

    @Test
    void findViewStatsByUniqueIpReturnOneHit() {
        ViewStatsDto viewStatsDto = DataTest.testViewStatsDto();
        List<ViewStats> result = endpointHitRepository.findViewStatsByUniqueIp(DataTest.time.minusYears(2),
                DataTest.time.plusYears(2), List.of("/events/1"));

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(1L, result.get(0).getHits());
        Assertions.assertEquals(viewStatsDto.getApp(), result.get(0).getApp().getName());
        Assertions.assertEquals(viewStatsDto.getUri(), result.get(0).getUri());
    }

    @Test
    void findViewStatsByNonUniqueIpReturnTwoHits() {
        ViewStatsDto viewStatsDto = DataTest.testViewStatsDto();
        List<ViewStats> result = endpointHitRepository.findViewStatsByNonUniqueIp(DataTest.time.minusYears(2),
                DataTest.time.plusYears(2), List.of("/events/1"));

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(2L, result.get(0).getHits());
        Assertions.assertEquals(viewStatsDto.getApp(), result.get(0).getApp().getName());
        Assertions.assertEquals(viewStatsDto.getUri(), result.get(0).getUri());
    }
}