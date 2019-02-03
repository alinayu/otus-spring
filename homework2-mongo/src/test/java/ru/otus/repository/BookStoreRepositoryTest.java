package ru.otus.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.config.MongoConfig;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.tuple;

@DataMongoTest
@Import(MongoConfig.class)
@DirtiesContext
class BookStoreRepositoryTest {

    @Autowired
    private BookStoreRepository bookStoreRepository;

    @Test
    void findByAuthor_FirstNameAndAuthor_LastName() {
        assertThat(bookStoreRepository
                .findByAuthor_LastNameAndAuthor_FirstName("Пушкин", "Александр"))
                .extracting("name")
                .contains("Дубровский", "Пророк");
    }

    @Test
    void findByGenre_Name() {
        assertThat(bookStoreRepository.findByGenre_Name("Проза"))
                .extracting("name")
                .contains("Дубровский");
    }

    @Test
    void removeByAuthor_FirstNameAndAuthor_LastName() {
        bookStoreRepository.removeByAuthor_LastNameAndAuthor_FirstName("Гоголь", "Николай");
        assertThat(bookStoreRepository.findAll())
                .extracting("author.lastName", "author.firstName")
                .doesNotContain(tuple("Гоголь", "Николай"));
    }

    @Test
    void removeByGenre_Name() {
        bookStoreRepository.removeByGenre_Name("Басня");
        assertThat(bookStoreRepository.findAll())
                .extracting("genre.name")
                .doesNotContain(tuple("Басня"));
    }

}