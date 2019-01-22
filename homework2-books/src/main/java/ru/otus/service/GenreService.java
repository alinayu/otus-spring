package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.dao.GenreRepository;
import ru.otus.domain.Genre;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public void save(Genre genre) {
        genreRepository.insert(genre);
    }

    public void deleteById(long id) {
        genreRepository.deleteById(id);
    }

    public Genre getById(long id) {
        return genreRepository.getById(id);
    }

    public List<Genre> getAll() {
        return genreRepository.getAll();
    }
}
