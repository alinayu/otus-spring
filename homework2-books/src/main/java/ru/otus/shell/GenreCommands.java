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

    @ShellMethod("Save genre with name.")
    public void saveGenre(String name) {
        genreService.save(new Genre(name));
    }

    @ShellMethod("Delete genre by id.")
    public void deleteGenreById(long id) {
        genreService.deleteById(id);
    }

    @ShellMethod("Get all genres.")
    public List<Genre> getAllGenres() {
        return genreService.getAll();
    }
}
