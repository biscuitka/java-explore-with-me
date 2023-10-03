package ru.practicum.ewm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.practicum.ewm.stats.client.StatClient;

@Configuration
@RequiredArgsConstructor
public class StatClientConfig {

    @Value("${STAT_SERVER_URL:http://localhost:9090}")
    private String statServerUrl;
    private final RestTemplateBuilder builder;


    @Bean
    public StatClient statClient() {
        return new StatClient(statServerUrl, builder);
    }
}
