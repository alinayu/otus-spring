package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.domain.Comment;
import ru.otus.repository.BookStoreRepository;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private BookStoreRepository bookStoreRepository;

    public List<Comment> findByBookId(String id) {
        return bookStoreRepository.findCommentsByBookId(id);
    }

    public void addCommentByBookId(String id, Comment comment) {
        bookStoreRepository.addCommentByBookId(id, comment);
    }

    public void deleteCommentsByBookId(String id) {
        bookStoreRepository.deleteCommentsByBookId(id);
    }
}
