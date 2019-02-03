package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.domain.Author;
import ru.otus.repository.BookStoreRepository;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private BookStoreRepository bookStoreRepository;

    public List<Author> findAll() {
        return bookStoreRepository.findAllDistinctAuthors();
    }

    public void deleteByLastNameAndFirstName(String lastName, String firstName) {
        bookStoreRepository.removeByAuthor_LastNameAndAuthor_FirstName(lastName, firstName);
    }

}
