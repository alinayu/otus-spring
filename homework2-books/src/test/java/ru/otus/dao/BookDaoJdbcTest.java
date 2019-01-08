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

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.tuple;

@RunWith(SpringRunner.class)
@JdbcTest
@Import(BookDaoJdbc.class)
@TestPropertySource(value = "classpath:application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BookDaoJdbcTest {

    @Autowired
    private BookDao bookDao;

    @Test
    void getAllTest() {
        assertThat(bookDao.getAll())
                .extracting("name")
                .contains("Война и мир", "Капитанская дочка", "Евгений Онегин", "Совершенный код", "Чистый код");
    }

    @Test
    void getByIdTest() {
        Book result = bookDao.getById(1);
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getName()).isEqualTo("Война и мир");
        assertThat(result.getAuthorId()).isEqualTo(2);
        assertThat(result.getGenreId()).isEqualTo(2);
    }

    @Test
    void getByAuthorIdTest() {
        assertThat(bookDao.getByAuthorId(1))
                .extracting("id", "name", "authorId", "genreId")
                .contains(tuple(2, "Капитанская дочка", 1, 1),
                        tuple(3, "Евгений Онегин", 1, 2));
    }

    @Test
    void deleteByIdTest() {
        bookDao.deleteById(1);
        assertThat(bookDao.getAll())
                .extracting("name")
                .contains("Капитанская дочка", "Евгений Онегин", "Совершенный код", "Чистый код");
    }

    @Test
    void insertBookTest() {
        bookDao.insert(6, "Анна Каренина", 2, 2);
        Book inserted = bookDao.getById(6);
        assertThat(inserted.getId()).isEqualTo(6);
        assertThat(inserted.getName()).isEqualTo("Анна Каренина");
        assertThat(inserted.getAuthorId()).isEqualTo(2);
        assertThat(inserted.getGenreId()).isEqualTo(2);
    }

    @Test
    void updateNameByIdTest() {
        bookDao.updateNameById(2, "Пиковая дама");
        Book updated = bookDao.getById(2);
        assertThat(updated.getId()).isEqualTo(2);
        assertThat(updated.getName()).isEqualTo("Пиковая дама");
    }

}