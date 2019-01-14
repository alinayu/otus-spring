package ru.otus.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.dao.AuthorRepository;
import ru.otus.dao.BookRepository;
import ru.otus.dao.GenreRepository;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.util.List;

@ShellComponent
public class Commands {

    private BookRepository bookRepository;

    private AuthorRepository authorRepository;

    private GenreRepository genreRepository;

    public Commands(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @ShellMethod("Get all books.")
    public List<Book> getAllBooks() {
        return bookRepository.getAll();
    }

    @ShellMethod("Get by book id.")
    public Book getByBookId(long id) { return bookRepository.getById(id); }

    @ShellMethod("Get books by author id.")
    public List<Book> getByAuthorId(long authorId) {
        return bookRepository.getByAuthorId(authorId);
    }

    @ShellMethod("Delete by book id.")
    public void deleteByBookId(long id) {
        bookRepository.deleteById(id);
    }

    @ShellMethod("Save books with id, name, authorId and genreId.")
    public void insertBook(long id, String name, long authorId, long genreId) {
        bookRepository.insert(new Book(id, name, authorId, genreId));
    }

    @ShellMethod("Update book name by id.")
    public void updateBookName(long id, String newName) {
        bookRepository.updateNameById(id, newName);
    }

    @ShellMethod("Get all authors.")
    public List<Author> getAllAuthors() {
        return  authorRepository.getAll();
    }

    @ShellMethod("Save author with id, firstName and lastName.")
    public void insertAuthor(long id, String firstName, String lastName) {
        authorRepository.insert(new Author(id, firstName, lastName));
    }

    @ShellMethod("Delete by author id.")
    public void deleteByAuthorId(long id) {
        authorRepository.deleteById(id);
    }

    @ShellMethod("Get all genres.")
    public List<Genre> getAllGenres() {
        return genreRepository.getAll();
    }

    @ShellMethod("Save genre with id and name.")
    public void insertGenre(long id, String name) {
        genreRepository.insert(new Genre(id, name));
    }

    @ShellMethod("Delete by genre id.")
    public void deleteByGenreId(long id) {
        genreRepository.deleteById(id);
    }

}
