package ru.otus.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;

@Configuration
public class IntegrationConfig {

    @Bean
    public IntegrationFlow saveNameHistory() {
        return f -> f
                .handle("bookHistoryRepository", "save")
                .log();
    }
}
