package ru.otus.dao;

import ru.otus.domain.Author;

import java.util.List;

public interface AuthorRepository {

    Author insert(Author author);

    void deleteById(long id);

    Author getById(long id);

    List<Author> getAll();

}
