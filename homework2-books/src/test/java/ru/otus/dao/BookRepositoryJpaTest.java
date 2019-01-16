package ru.otus.dao;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.domain.Book;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@Import(BookRepositoryJpa.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BookRepositoryJpaTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void insert() {
        bookRepository.insert(new Book(6, "Анна Каренина", 2, 2));
        Book inserted = bookRepository.getById(6);
        assertThat(inserted.getId()).isEqualTo(6);
        assertThat(inserted.getName()).isEqualTo("Анна Каренина");
        assertThat(inserted.getAuthor().getId()).isEqualTo(2);
        assertThat(inserted.getGenre().getId()).isEqualTo(2);
    }

    @Test
    void getAll() {
        assertThat(bookRepository.getAll())
                .extracting("name")
                .contains("Война и мир", "Капитанская дочка", "Евгений Онегин", "Совершенный код", "Чистый код");
    }

    @Test
    void getById() {
    }

    @Test
    void getByAuthorId() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void updateNameById() {
    }
}