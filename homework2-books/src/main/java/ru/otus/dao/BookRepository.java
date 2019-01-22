package ru.otus.dao;

import ru.otus.domain.Book;

import java.util.List;

public interface BookRepository {

    Book insert(Book book);

    void deleteById(long id);

    List<Book> getAll();

    Book getById(long id);

    List<Book> getByAuthorId(long authorId);

    void updateNameById(long id, String newName);
}
