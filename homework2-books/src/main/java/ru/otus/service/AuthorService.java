package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.dao.AuthorRepository;
import ru.otus.domain.Author;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public void save(Author author) {
        authorRepository.insert(author);
    }

    public void deleteById(long id) {
        authorRepository.deleteById(id);
    }

    public Author getById(long id) {
        return authorRepository.getById(id);
    }

    public List<Author> getAll() {
        return authorRepository.getAll();
    }

}
