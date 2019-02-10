package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import ru.otus.repository.CommentRepository;
import ru.otus.domain.Comment;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getByBookId(long bookId) {
        return commentRepository.findByBookId(bookId);
    }

    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }
}
