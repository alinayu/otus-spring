package ru.otus.dao;

import ru.otus.domain.Book;

import java.util.List;

public interface BookDao {

    List<Book> getAll();

    Book getById(int id);

    List<Book> getByAuthorId(int authorId);

    void deleteById(int id);

    void insert(int id, String name, int authorId, int genreId);

    void updateNameById(int id, String newName);
}
