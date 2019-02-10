package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import ru.otus.repository.AuthorRepository;
import ru.otus.domain.Author;
import ru.otus.exception.AuthorNotFoundException;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public void save(Author author) {
        authorRepository.save(author);
    }

    public void deleteById(long id) {
        authorRepository.deleteById(id);
    }

    public Author getById(long id) {
        return authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException("Author with id: " + id + " not found"));
    }

    public List<Author> getAll() {
        return authorRepository.findAll();
    }

}
