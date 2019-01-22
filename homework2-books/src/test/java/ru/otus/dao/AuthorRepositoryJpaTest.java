package ru.otus.dao;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.domain.Author;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.tuple;

@RunWith(SpringRunner.class)
@DataJpaTest
class AuthorRepositoryJpaTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void insert() {
        Author saved = authorRepository.save(new Author("Максим", "Гоголь"));
        Author found = authorRepository.findById(saved.getId()).get();
        assertThat(found.getFirstName()).isEqualTo("Максим");
        assertThat(found.getLastName()).isEqualTo("Гоголь");
    }

    @Test
    void getById() {
        Author result = authorRepository.findById(1L).get();
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getFirstName()).isEqualTo("Александр");
        assertThat(result.getLastName()).isEqualTo("Пушкин");
    }

    @Test
    void getAll() {
        assertThat(authorRepository.findAll())
                .extracting("id", "lastName", "firstName")
                .contains(tuple(1L, "Пушкин", "Александр"),
                        tuple(2L, "Толстой", "Лев"),
                        tuple(3L, "Макконнелл", "Стив"),
                        tuple(4L, "Мартин", "Роберт"));
    }

    @Test
    void deleteById() {
        authorRepository.deleteById(1L);
        List<Author> allAuthors = authorRepository.findAll();
        assertThat(allAuthors.size()).isEqualTo(3);
        assertThat(allAuthors)
                .extracting("lastName")
                .contains("Толстой", "Макконнелл", "Мартин");
    }
}