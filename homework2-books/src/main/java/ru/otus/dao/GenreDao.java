package ru.otus.dao;

import ru.otus.domain.Genre;

import java.util.List;

public interface GenreDao {

    Genre getById(long id);

    List<Genre> getAll();

    void insert(Genre genre);

    void deleteById(long id);
}
