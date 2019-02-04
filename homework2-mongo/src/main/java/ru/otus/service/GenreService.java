package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.domain.Genre;
import ru.otus.repository.BookStoreRepository;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    private BookStoreRepository bookStoreRepository;

    public List<Genre> findAll() {
        return bookStoreRepository.findAllDistinctGenres();
    }

    public void deleteByName(String name) {
        bookStoreRepository.removeByGenre_Name(name);
    }
}
