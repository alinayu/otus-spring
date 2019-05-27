package ru.otus.actuators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import ru.otus.repository.BookRepository;

@Component
public class BookCountHealthIndicator implements HealthIndicator {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Health health() {
        long count = bookRepository.count();
        if (count == 0) {
            return Health.down().withDetail("count", 0).build();
        }
        return Health.up().withDetail("count", count).build();
    }
}
