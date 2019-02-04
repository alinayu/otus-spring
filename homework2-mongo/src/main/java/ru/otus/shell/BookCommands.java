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

    @ShellMethod("Save book with name, author last name, author first name and genre name.")
    public void saveBook(String name, String authorLastName, String authorFirstName, String genreName) {
        bookService.save(name, authorLastName, authorFirstName, genreName);
    }

    @ShellMethod("Find all books.")
    public List<Book> findAllBooks() {
        return bookService.findAll();
    }

    @ShellMethod("Find book by id.")
    public Book findBookById(String id) { return bookService.findById(id); }

    @ShellMethod("Find books by author last name and first name.")
    public List<Book> findByAuthorLastNameAndFirstName(String authorLastName, String authorFirstName) {
        return bookService.findByAuthorLastNameAndFirstName(authorLastName, authorFirstName);
    }

    @ShellMethod("Find books by genre name.")
    public List<Book> findByGenreName(String genreName) {
        return bookService.findByGenreName(genreName);
    }

    @ShellMethod("Update book name by id.")
    public void updateBookNameById(String id, String newName) {
        bookService.updateNameById(id, newName);
    }

    @ShellMethod("Delete book by id.")
    public void deleteBookById(String id) {
        bookService.deleteById(id);
    }

}
