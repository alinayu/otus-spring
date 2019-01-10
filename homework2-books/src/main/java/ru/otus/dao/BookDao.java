package ru.otus.dao;

import ru.otus.domain.Book;

import java.util.List;

public interface BookDao {

    List<Book> getAll();

    Book getById(long id);

    List<Book> getByAuthorId(long authorId);

    void deleteById(long id);

    void insert(Book book);

    void updateNameById(long id, String newName);
}
