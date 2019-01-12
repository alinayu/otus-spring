package ru.otus.dao;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.domain.Author;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.tuple;

@RunWith(SpringRunner.class)
@JdbcTest
@Import(AuthorDaoJdbc.class)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AuthorDaoJdbcTest {
    
    @Autowired
    private AuthorDao authorDao;

    @Test
    void getById() {
        Author result = authorDao.getById(1);
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getFirstName()).isEqualTo("Александр");
        assertThat(result.getLastName()).isEqualTo("Пушкин");
    }

    @Test
    void getAll() {
        assertThat(authorDao.getAll())
                .extracting("id", "lastName", "firstName")
                .contains(tuple(1L, "Пушкин", "Александр"),
                        tuple(2L, "Толстой", "Лев"),
                        tuple(3L, "Макконнелл", "Стив"),
                        tuple(4L, "Мартин", "Роберт"));
    }

    @Test
    void insert() {
        authorDao.insert(new Author(5, "Максим", "Гоголь"));
        Author inserted = authorDao.getById(5);
        assertThat(inserted.getId()).isEqualTo(5);
        assertThat(inserted.getFirstName()).isEqualTo("Максим");
        assertThat(inserted.getLastName()).isEqualTo("Гоголь");
    }

    @Test
    void deleteById() {
        authorDao.deleteById(1);
        List<Author> allAuthors = authorDao.getAll();
        assertThat(allAuthors.size()).isEqualTo(3);
        assertThat(allAuthors)
                .extracting("lastName")
                .contains("Толстой", "Макконнелл", "Мартин");
    }
}