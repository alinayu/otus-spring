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

    @ShellMethod("Find all authors.")
    public List<Author> findAllAuthors() {
        return authorService.findAll();
    }

    @ShellMethod("Delete books by author last name and first name.")
    public void deleteByAuthorLastNameAndFirstName(String lastName, String firstName) {
        authorService.deleteByLastNameAndFirstName(lastName, firstName);
    }

}
