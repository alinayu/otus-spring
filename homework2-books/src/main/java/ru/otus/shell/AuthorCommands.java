package ru.otus.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.domain.Author;
import ru.otus.service.AuthorService;

import java.util.List;

@ShellComponent
public class AuthorCommands {

    private AuthorService authorService;

    public AuthorCommands(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ShellMethod("Save author with firstName and lastName.")
    public void saveAuthor(String firstName, String lastName) {
        authorService.save(new Author(firstName, lastName));
    }

    @ShellMethod("Delete author by id.")
    public void deleteAuthorById(long id) {
        authorService.deleteById(id);
    }

    @ShellMethod("Get all authors.")
    public List<Author> getAllAuthors() {
        return  authorService.getAll();
    }
}
