package ru.otus.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.domain.Comment;
import ru.otus.service.CommentService;

import java.util.List;

@ShellComponent
public class CommentCommands {

    private CommentService commentService;

    public CommentCommands(CommentService commentService) {
        this.commentService = commentService;
    }

    @ShellMethod("Find comments by book id.")
    public List<Comment> findCommentsByBookId(String bookId) {
        return commentService.findByBookId(bookId);
    }

    @ShellMethod("Add comment with book id and text.")
    public void addComment(String bookId, String text) {
        commentService.addCommentByBookId(bookId, new Comment(text));
    }
}
