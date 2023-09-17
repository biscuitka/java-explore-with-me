package ru.practicum.ewm.stats.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.ewm.stats.dto.EndpointHitDto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatClient extends BaseClient {
    private static final String HIT_API_PREFIX = "/hit";
    private static final String STATS_API_PREFIX = "/stats";

    @Autowired
    public StatClient(@Value("${stat-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public ResponseEntity<Object> createHit(EndpointHitDto endpointHitDto) {
        return post(HIT_API_PREFIX, endpointHitDto);
    }

    public ResponseEntity<Object> getViewStats(LocalDateTime start, LocalDateTime end,
                                               List<String> uris, boolean unique) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("start", start);
        parameters.put("end", end);
        if (uris != null && !uris.isEmpty()) {
            parameters.put("uris", uris);
        }
        parameters.put("unique", unique);

        if (parameters.containsKey("uris")) {
            return get(STATS_API_PREFIX + "?start={start}&end={end}&uris={uris}&unique={unique}", parameters);

        } else {
            return get(STATS_API_PREFIX + "?start={start}&end={end}&unique={unique}", parameters);
        }
    }
}
