package ru.otus.dao;

import ru.otus.domain.Comment;

import java.util.List;

public interface CommentRepository {

    Comment insert(Comment comment);

    List<Comment> getByBookId(long bookId);
}
