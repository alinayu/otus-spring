package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.dao.GenreRepository;
import ru.otus.domain.Genre;
import ru.otus.exception.GenreNotFoundException;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public void save(Genre genre) {
        genreRepository.save(genre);
    }

    public void deleteById(long id) {
        genreRepository.deleteById(id);
    }

    public Genre getById(long id) {
        return genreRepository.findById(id).orElseThrow(() -> new GenreNotFoundException("Book with id: " + id + " not found"));
    }

    public List<Genre> getAll() {
        return genreRepository.findAll();
    }
}
