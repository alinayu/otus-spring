package ru.otus.dao;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.domain.Book;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.tuple;

@RunWith(SpringRunner.class)
@DataJpaTest
class BookRepositoryJpaTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void insert() {
        Book saved = bookRepository.save(new Book("Анна Каренина", 2, 2));
        Book found = bookRepository.findById(saved.getId()).get();
        assertThat(found.getName()).isEqualTo("Анна Каренина");
        assertThat(found.getAuthor().getId()).isEqualTo(2);
        assertThat(found.getGenre().getId()).isEqualTo(2);
    }

    @Test
    void getAll() {
        assertThat(bookRepository.findAll())
                .extracting("name")
                .contains("Война и мир", "Капитанская дочка", "Евгений Онегин", "Совершенный код", "Чистый код");
    }

    @Test
    void getById() {
        Book result = bookRepository.findById(1L).get();
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getName()).isEqualTo("Война и мир");
        assertThat(result.getAuthor().getId()).isEqualTo(2);
        assertThat(result.getAuthor().getFirstName()).isEqualTo("Лев");
        assertThat(result.getGenre().getId()).isEqualTo(2);
        assertThat(result.getGenre().getName()).isEqualTo("Роман");
    }

    @Test
    void getByAuthorId() {
        assertThat(bookRepository.findByAuthorId(1))
                .extracting("id", "name", "author.id", "genre.id")
                .contains(tuple(2L, "Капитанская дочка", 1L, 1L),
                        tuple(3L, "Евгений Онегин", 1L, 2L));
    }

    @Test
    void deleteById() {
        bookRepository.deleteById(1L);
        List<Book> allBooks = bookRepository.findAll();
        assertThat(allBooks.size()).isEqualTo(4);
        assertThat(allBooks)
                .extracting("name")
                .contains("Капитанская дочка", "Евгений Онегин", "Совершенный код", "Чистый код");
    }

    @Test
    void updateNameById() {
        bookRepository.updateNameById(2, "Пиковая дама");
        Book updated = bookRepository.findById(2L).get();
        assertThat(updated.getId()).isEqualTo(2);
        assertThat(updated.getName()).isEqualTo("Пиковая дама");
    }
}