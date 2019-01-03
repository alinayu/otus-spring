package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.service.TestService;

/**
 * Created by alina on 09.12.2018.
 */
@SpringBootApplication
public class Main {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("classpath:/i18n/bundle");
        ms.setDefaultEncoding("UTF-8");
        ms.setFallbackToSystemLocale(false);
        return ms;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}

