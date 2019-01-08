package ru.otus.shell;

import ru.otus.dao.AuthorDao;
import ru.otus.dao.BookDao;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
public class Commands {

    private BookDao bookDao;

    private AuthorDao authorDao;

    public Commands(BookDao bookDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
    }

    @ShellMethod("Get all books.")
    public List<Book> getAllBooks() {
        return bookDao.getAll();
    }

    @ShellMethod("Get by book id.")
    public Book getByBookId(int id) { return bookDao.getById(id); }

    @ShellMethod("Get all authors.")
    public List<Author> getAllAuthors() {
        return  authorDao.getAll();
    }

    @ShellMethod("Get books by author id.")
    public List<Book> getByAuthorId(int authorId) {
        return bookDao.getByAuthorId(authorId);
    }

    @ShellMethod("Delete by book id.")
    public void deleteByBookId(int id) {
        bookDao.deleteById(id);
    }

    @ShellMethod("Save books with name, authorId and genreId")
    public void insertBook(int id, String name, int authorId, int genreId) {
        bookDao.insert(id, name, authorId, genreId);
    }

    @ShellMethod("Update book name by id")
    public void updateBookName(int id, String newName) {
        bookDao.updateNameById(id, newName);
    }

}
