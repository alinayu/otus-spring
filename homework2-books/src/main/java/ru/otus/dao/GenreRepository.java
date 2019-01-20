package ru.otus.dao;

import ru.otus.domain.Genre;

import java.util.List;

public interface GenreRepository {

    Genre insert(Genre genre);

    void deleteById(long id);

    Genre getById(long id);

    List<Genre> getAll();
}
