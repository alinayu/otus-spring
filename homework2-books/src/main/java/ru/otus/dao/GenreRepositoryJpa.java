package ru.otus.dao;

import org.springframework.stereotype.Repository;
import ru.otus.domain.Genre;

import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
public class GenreRepositoryJpa implements GenreRepository {
    @Override
    public Genre getById(long id) {
        return null;
    }

    @Override
    public List<Genre> getAll() {
        return null;
    }

    @Override
    public void insert(Genre genre) {

    }

    @Override
    public void deleteById(long id) {

    }
}
