package ru.otus.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.domain.Genre;
import ru.otus.service.GenreService;

import java.util.List;

@ShellComponent
public class GenreCommands {

    private GenreService genreService;

    public GenreCommands(GenreService genreService) {
        this.genreService = genreService;
    }

    @ShellMethod("Find all genres.")
    public List<Genre> findAllGenres() {
        return genreService.findAll();
    }

    @ShellMethod("Delete books by genre name.")
    public void deleteByGenreName(String genreName) {
        genreService.deleteByName(genreName);
    }

}
