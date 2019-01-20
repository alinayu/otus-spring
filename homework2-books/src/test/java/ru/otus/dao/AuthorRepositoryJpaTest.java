package ru.otus.dao;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.domain.Author;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.tuple;

@RunWith(SpringRunner.class)
@Import(AuthorRepositoryJpa.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AuthorRepositoryJpaTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void insert() {
        Author saved = authorRepository.insert(new Author("Максим", "Гоголь"));
        Author found = authorRepository.getById(saved.getId());
        assertThat(found.getFirstName()).isEqualTo("Максим");
        assertThat(found.getLastName()).isEqualTo("Гоголь");
    }

    @Test
    void getById() {
        Author result = authorRepository.getById(1);
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getFirstName()).isEqualTo("Александр");
        assertThat(result.getLastName()).isEqualTo("Пушкин");
    }

    @Test
    void getAll() {
        assertThat(authorRepository.getAll())
                .extracting("id", "lastName", "firstName")
                .contains(tuple(1L, "Пушкин", "Александр"),
                        tuple(2L, "Толстой", "Лев"),
                        tuple(3L, "Макконнелл", "Стив"),
                        tuple(4L, "Мартин", "Роберт"));
    }

    @Test
    void deleteById() {
        authorRepository.deleteById(1);
        List<Author> allAuthors = authorRepository.getAll();
        assertThat(allAuthors.size()).isEqualTo(3);
        assertThat(allAuthors)
                .extracting("lastName")
                .contains("Толстой", "Макконнелл", "Мартин");
    }
}