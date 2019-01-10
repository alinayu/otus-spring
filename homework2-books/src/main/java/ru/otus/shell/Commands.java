package ru.otus.shell;

import ru.otus.dao.AuthorDao;
import ru.otus.dao.BookDao;
import ru.otus.dao.GenreDao;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.domain.Genre;

import java.util.List;

@ShellComponent
public class Commands {

    private BookDao bookDao;

    private AuthorDao authorDao;

    private GenreDao genreDao;

    public Commands(BookDao bookDao, AuthorDao authorDao, GenreDao genreDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @ShellMethod("Get all books.")
    public List<Book> getAllBooks() {
        return bookDao.getAll();
    }

    @ShellMethod("Get by book id.")
    public Book getByBookId(long id) { return bookDao.getById(id); }

    @ShellMethod("Get books by author id.")
    public List<Book> getByAuthorId(long authorId) {
        return bookDao.getByAuthorId(authorId);
    }

    @ShellMethod("Delete by book id.")
    public void deleteByBookId(long id) {
        bookDao.deleteById(id);
    }

    @ShellMethod("Save books with id, name, authorId and genreId.")
    public void insertBook(long id, String name, long authorId, long genreId) {
        bookDao.insert(new Book(id, name, authorId, genreId));
    }

    @ShellMethod("Update book name by id.")
    public void updateBookName(long id, String newName) {
        bookDao.updateNameById(id, newName);
    }

    @ShellMethod("Get all authors.")
    public List<Author> getAllAuthors() {
        return  authorDao.getAll();
    }
    
    @ShellMethod("Save author with id, firstName and lastName.")
    public void insertAuthor(long id, String firstName, String lastName) {
        authorDao.insert(new Author(id, firstName, lastName));
    }

    @ShellMethod("Delete by author id.")
    public void deleteByAuthorId(long id) {
        authorDao.deleteById(id);
    }

    @ShellMethod("Get all genres.")
    public List<Genre> getAllGenres() {
        return genreDao.getAll();
    }

    @ShellMethod("Save genre with id and name.")
    public void insertGenre(long id, String name) {
        genreDao.insert(new Genre(id, name));
    }

    @ShellMethod("Delete by genre id.")
    public void deleteByGenreId(long id) {
        genreDao.deleteById(id);
    }

}
