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

    @ShellMethod("Get comments by book id")
    public List<Comment> getCommentByBookId(long bookId) {
        return commentService.getByBookId(bookId);
    }

    @ShellMethod("Save comment with bookId and text")
    public void saveComment(long bookId, String text) {
        commentService.saveComment(new Comment(bookId, text));
    }
}
