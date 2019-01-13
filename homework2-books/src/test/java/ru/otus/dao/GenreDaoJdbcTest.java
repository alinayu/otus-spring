package ru.otus.dao;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.tuple;

@RunWith(SpringRunner.class)
@JdbcTest
@Import(GenreDaoJdbc.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class GenreDaoJdbcTest {

    @Autowired
    private GenreDao genreDao;

    @Test
    void getById() {
        Genre result = genreDao.getById(1);
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getName()).isEqualTo("Повесть");
    }

    @Test
    void getAll() {
        assertThat(genreDao.getAll())
                .extracting("id", "name")
                .contains(tuple(1L, "Повесть"),
                        tuple(2L, "Роман"),
                        tuple(3L, "Компьютерная литература"));
    }

    @Test
    void insert() {
        genreDao.insert(new Genre(4, "Проза"));
        Genre inserted = genreDao.getById(4);
        assertThat(inserted.getId()).isEqualTo(4);
        assertThat(inserted.getName()).isEqualTo("Проза");
    }

    @Test
    void deleteById() {
        genreDao.deleteById(1);
        List<Genre> allGenres = genreDao.getAll();
        assertThat(allGenres.size()).isEqualTo(2);
        assertThat(allGenres)
                .extracting("name")
                .contains("Роман", "Компьютерная литература");
    }
}