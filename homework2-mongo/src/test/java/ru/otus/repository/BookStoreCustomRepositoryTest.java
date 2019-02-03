package ru.otus.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.domain.Comment;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.tuple;

@SpringBootTest
@DirtiesContext
class BookStoreCustomRepositoryTest {

    @Autowired
    private BookStoreRepository bookStoreCustomRepository;

    @Test
    void findAllDistinctAuthors() {
        assertThat(bookStoreCustomRepository.findAllDistinctAuthors())
                .hasSize(3)
                .extracting("lastName", "firstName")
                .contains(tuple("Пушкин", "Александр"),
                        tuple("Гоголь", "Николай"),
                        tuple("Крылов", "Иван"));
    }

    @Test
    void findAllDistinctGenres() {
        assertThat(bookStoreCustomRepository.findAllDistinctGenres())
                .hasSize(4)
                .extracting("name")
                .contains("Проза", "Стихотворение", "Поэма", "Басня");
    }

    @Test
    void findCommentsByBookId() {
        assertThat(bookStoreCustomRepository.findCommentsByBookId("1"))
                .extracting("text")
                .contains("Хорошо");
    }

    @Test
    void updateBookNameById() {
        bookStoreCustomRepository.updateBookNameById("1", "Капитанская дочка");
        assertThat(bookStoreCustomRepository.findById("1").get().getName())
                .isEqualTo("Капитанская дочка");
    }

    @Test
    void addCommentByBookId() {
        bookStoreCustomRepository.addCommentByBookId("2", new Comment("Замечательно"));
        assertThat(bookStoreCustomRepository.findById("2").get().getComments())
                .hasSize(1)
                .extracting("text")
                .contains("Замечательно");
    }
}