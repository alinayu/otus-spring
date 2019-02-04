package ru.otus.config;

import com.github.mongobee.Mongobee;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.changelog.DatabaseChangelog;

@Configuration
public class MongoConfig {

    @Value("${mongo.db.url}")
    private String MONGO_DB_URL;

    @Value(("${mongo.db.port:27017}"))
    private int MONGO_DB_PORT;

    @Value("${mongo.db.name}")
    private String MONGO_DB_NAME;

    @Bean
    public MongoClient mongoClient() {
        return new MongoClient(MONGO_DB_URL, MONGO_DB_PORT);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), MONGO_DB_NAME);
    }

    @Bean
    public Mongobee mongobee(Environment environment) {
        Mongobee runner = new Mongobee(mongoClient());
        runner.setDbName(MONGO_DB_NAME);
        runner.setChangeLogsScanPackage(DatabaseChangelog.class.getPackage().getName());
        runner.setSpringEnvironment(environment);
        return runner;
    }
}
