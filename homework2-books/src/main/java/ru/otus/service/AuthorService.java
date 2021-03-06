package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.dao.AuthorRepository;
import ru.otus.domain.Author;
import ru.otus.exception.AuthorNotFoundException;

import java.util.List;

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
