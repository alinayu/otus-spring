package ru.otus.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.domain.Book;
import ru.otus.service.BookService;

import java.util.List;

@ShellComponent
public class BookCommands {

    private BookService bookService;

    public BookCommands(BookService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod("Save book with name, authorId and genreId.")
    public void saveBook(String name, long authorId, long genreId) {
        bookService.save(new Book(name, authorId, genreId));
    }

    @ShellMethod("Delete book by id.")
    public void deleteBookById(long id) {
        bookService.deleteById(id);
    }

    @ShellMethod("Get all books.")
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    @ShellMethod("Get book by id.")
    public Book getBookById(long id) { return bookService.getById(id); }

    @ShellMethod("Get books by author id.")
    public List<Book> getBookByAuthorId(long authorId) {
        return bookService.getByAuthorId(authorId);
    }

    @ShellMethod("Update book name by id.")
    public void updateBookNameById(long id, String newName) {
        bookService.updateNameById(id, newName);
    }
}
