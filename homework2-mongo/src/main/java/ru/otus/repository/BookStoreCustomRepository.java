package ru.otus.repository;

import ru.otus.domain.Author;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;

import java.util.List;

public interface BookStoreCustomRepository {

    List<Author> findAllDistinctAuthors();

    List<Genre> findAllDistinctGenres();

    List<Comment> findCommentsByBookId(String id);

    void updateBookNameById(String id, String newName);

    void addCommentByBookId(String id, Comment comment);

}
