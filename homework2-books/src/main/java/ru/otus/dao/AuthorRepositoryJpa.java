package ru.otus.dao;

import org.springframework.stereotype.Repository;
import ru.otus.domain.Author;

import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
public class AuthorRepositoryJpa implements AuthorRepository {
    @Override
    public Author getById(long id) {
        return null;
    }

    @Override
    public List<Author> getAll() {
        return null;
    }

    @Override
    public void insert(Author author) {

    }

    @Override
    public void deleteById(long id) {

    }
}
