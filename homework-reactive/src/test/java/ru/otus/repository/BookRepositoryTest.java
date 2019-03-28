package ru.otus.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import ru.otus.config.MongoConfig;
import ru.otus.domain.Book;

import java.util.ArrayList;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
@Import(MongoConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void findAll() {
        Flux<Book> all = bookRepository.findAll();
        StepVerifier.create(all)
                .recordWith(ArrayList::new)
                .expectNextCount(4)
                .consumeRecordedWith(results ->
                        assertThat(results)
                        .extracting("name")
                        .contains("Дубровский", "Пророк", "Мертвые души", "Ворона и лисица")
                )
                .verifyComplete();
    }

    @Test
    void findById() {
        StepVerifier.create(bookRepository.findById("1"))
                .assertNext(book -> {
                    assertEquals(book.getName(), "Дубровский");
                    assertEquals(book.getAuthor().getLastName(), "Пушкин");
                })
                .expectComplete()
                .verify();
    }

    @Test
    void findByAuthor_FirstNameAndAuthor_LastName() {
        Flux<Book> flux = bookRepository
                .findByAuthor_LastNameAndAuthor_FirstName("Пушкин", "Александр");
        StepVerifier.create(flux)
                .recordWith(ArrayList::new)
                .expectNextCount(2)
                .consumeRecordedWith(results ->
                        assertThat(results)
                            .extracting("name")
                            .contains("Дубровский", "Пророк")
                )
                .verifyComplete();
    }

    @Test
    void findByGenre_Name() {
        Flux<Book> flux = bookRepository.findByGenre_Name("Проза");
        StepVerifier.create(flux)
                .recordWith(ArrayList::new)
                .expectNextCount(1)
                .consumeRecordedWith(results ->
                        assertThat(results)
                                .extracting("name")
                                .contains("Дубровский")
                )
                .verifyComplete();
    }

    @Test
    void updateNameById() {
        bookRepository.updateBookNameById("1", "Капитанская дочка").subscribe();
        StepVerifier.create(bookRepository.findById("1"))
                .assertNext(book -> assertEquals(book.getName(), "Капитанская дочка"))
                .expectComplete()
                .verify();
    }

    @Test
    void deleteById() {
        bookRepository.deleteById("1").subscribe();
        StepVerifier.create(bookRepository.findAll())
                .recordWith(ArrayList::new)
                .expectNextCount(3)
                .consumeRecordedWith(results ->
                        assertThat(results)
                                .extracting("name")
                                .contains("Пророк", "Мертвые души", "Ворона и лисица")
                )
                .verifyComplete();
    }


}