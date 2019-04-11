package ru.otus.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.domain.jpa.AuthorEntity;
import ru.otus.domain.jpa.BookEntity;
import ru.otus.domain.jpa.GenreEntity;
import ru.otus.domain.mongo.Author;
import ru.otus.domain.mongo.Book;
import ru.otus.domain.mongo.Genre;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@EnableBatchProcessing
@Configuration
public class BatchConfig {
    private final Logger logger = LoggerFactory.getLogger("Batch");

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public EntityManagerFactory entityManagerFactory;

    @Autowired
    public MongoTemplate mongoTemplate;

    @Bean
    public JpaPagingItemReader<BookEntity> reader() {
        String jpqlQuery = "select b from BookEntity b";
        return new JpaPagingItemReaderBuilder<BookEntity>()
                .name("jpaDatabaseReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString(jpqlQuery)
                .pageSize(100)
                .saveState(true)
                .build();
    }

    @Bean
    public ItemProcessor processor() {
        return (ItemProcessor<BookEntity, Book>) bookEntity -> {
            AuthorEntity authorEntity = bookEntity.getAuthor();
            Author author = new Author(authorEntity.getLastName(), authorEntity.getFirstName());
            GenreEntity genreEntity = bookEntity.getGenre();
            Genre genre = new Genre(genreEntity.getName());
            return new Book(bookEntity.getName(), author, genre);
        };
    }

    @Bean
    public MongoItemWriter<Book> writer() {
        MongoItemWriter<Book> mongoItemWriter = new MongoItemWriter<>();
        mongoItemWriter.setCollection("books");
        mongoItemWriter.setTemplate(mongoTemplate);
        return mongoItemWriter;
    }

    @Bean
    public Job importUserJob(Step step1) {
        return jobBuilderFactory.get("importBooksJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        logger.info("Начало job");
                    }

                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        logger.info("Конец job");
                    }
                })
                .build();
    }

    @Bean
    public Step step1(ItemReader reader, ItemProcessor processor, ItemWriter writer) {
        return stepBuilderFactory.get("step1")
                .chunk(5)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .listener(new ItemReadListener() {
                    public void beforeRead() { logger.info("Начало чтения"); }
                    public void afterRead(Object o) { logger.info("Конец чтения"); }
                    public void onReadError(Exception e) { logger.info("Ошибка чтения"); }
                })
                .listener(new ItemWriteListener() {
                    public void beforeWrite(List list) {
                        logger.info("Начало записи"); }
                    public void afterWrite(List list) { logger.info("Конец записи"); }
                    public void onWriteError(Exception e, List list) { logger.info("Ошибка записи"); }
                })
                .listener(new ItemProcessListener() {
                    public void beforeProcess(Object o) {logger.info("Начало обработки");}
                    public void afterProcess(Object o, Object o2) {logger.info("Конец обработки");}
                    public void onProcessError(Object o, Exception e) {logger.info("Ошбка обработки");}
                })
                .listener(new ChunkListener() {
                    public void beforeChunk(ChunkContext chunkContext) {logger.info("Начало пачки");}
                    public void afterChunk(ChunkContext chunkContext) {logger.info("Конец пачки");}
                    public void afterChunkError(ChunkContext chunkContext) {logger.info("Ошибка пачки");}
                })
                .build();
    }

}
