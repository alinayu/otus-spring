package ru.otus.dao;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.domain.Book;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.tuple;

@RunWith(SpringRunner.class)
@JdbcTest
@Import(BookDaoJdbc.class)
@TestPropertySource(value = "classpath:application-test.yml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BookDaoJdbcTest {

    @Autowired
    private BookDao bookDao;

    @Test
    void getAll() {
        assertThat(bookDao.getAll())
                .extracting("name")
                .contains("Война и мир", "Капитанская дочка", "Евгений Онегин", "Совершенный код", "Чистый код");
    }

    @Test
    void getById() {
        Book result = bookDao.getById(1);
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getName()).isEqualTo("Война и мир");
        assertThat(result.getAuthor().getId()).isEqualTo(2);
        assertThat(result.getAuthor().getFirstName()).isEqualTo("Лев");
        assertThat(result.getGenre().getId()).isEqualTo(2);
        assertThat(result.getGenre().getName()).isEqualTo("Роман");
    }

    @Test
    void getByAuthorId() {
        assertThat(bookDao.getByAuthorId(1))
                .extracting("id", "name", "author.id", "genre.id")
                .contains(tuple(2L, "Капитанская дочка", 1L, 1L),
                        tuple(3L, "Евгений Онегин", 1L, 2L));
    }

    @Test
    void deleteById() {
        bookDao.deleteById(1);
        List<Book> allBooks = bookDao.getAll();
        assertThat(allBooks.size()).isEqualTo(4);
        assertThat(allBooks)
                .extracting("name")
                .contains("Капитанская дочка", "Евгений Онегин", "Совершенный код", "Чистый код");
    }

    @Test
    void insert() {
        bookDao.insert(new Book(6, "Анна Каренина", 2, 2));
        Book inserted = bookDao.getById(6);
        assertThat(inserted.getId()).isEqualTo(6);
        assertThat(inserted.getName()).isEqualTo("Анна Каренина");
        assertThat(inserted.getAuthor().getId()).isEqualTo(2);
        assertThat(inserted.getGenre().getId()).isEqualTo(2);
    }

    @Test
    void updateNameById() {
        bookDao.updateNameById(2, "Пиковая дама");
        Book updated = bookDao.getById(2);
        assertThat(updated.getId()).isEqualTo(2);
        assertThat(updated.getName()).isEqualTo("Пиковая дама");
    }

}