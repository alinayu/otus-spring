package ru.otus.dao;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.tuple;

@RunWith(SpringRunner.class)
@Import(GenreRepositoryJpa.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class GenreRepositoryJpaTest {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    void insert() {
        Genre saved = genreRepository.insert(new Genre("Проза"));
        Genre found = genreRepository.getById(saved.getId());
        assertThat(found.getName()).isEqualTo("Проза");
    }

    @Test
    void getById() {
        Genre result = genreRepository.getById(1);
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getName()).isEqualTo("Повесть");
    }

    @Test
    void getAll() {
        assertThat(genreRepository.getAll())
                .extracting("id", "name")
                .contains(tuple(1L, "Повесть"),
                        tuple(2L, "Роман"),
                        tuple(3L, "Компьютерная литература"));
    }

    @Test
    void deleteById() {
        genreRepository.deleteById(1);
        List<Genre> allGenres = genreRepository.getAll();
        assertThat(allGenres.size()).isEqualTo(2);
        assertThat(allGenres)
                .extracting("name")
                .contains("Роман", "Компьютерная литература");
    }
}