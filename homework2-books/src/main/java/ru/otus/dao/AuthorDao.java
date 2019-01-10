package ru.otus.dao;

import ru.otus.domain.Author;

import java.util.List;

public interface AuthorDao {

    Author getById(long id);

    List<Author> getAll();

    void insert(Author author);

    void deleteById(long id);
}
