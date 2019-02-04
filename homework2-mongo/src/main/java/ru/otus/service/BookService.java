package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.exception.BookNotFoundException;
import ru.otus.repository.BookStoreRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookStoreRepository bookStoreRepository;

    public void save(String name, String authorLastName, String authorFirstName, String genreName) {
        Book book = new Book(name, new Author(authorLastName, authorFirstName), new Genre(genreName), new ArrayList<>());
        bookStoreRepository.save(book);
    }

    public List<Book> findAll() {
        return bookStoreRepository.findAll();
    }

    public Book findById(String id) {
        return bookStoreRepository.findById(id).orElseThrow(() ->
                new BookNotFoundException("Book with id: " + id + " not found"));
    }

    public List<Book> findByAuthorLastNameAndFirstName(String authorLastName, String authorFirstName) {
        return bookStoreRepository.findByAuthor_LastNameAndAuthor_FirstName(authorLastName, authorFirstName);
    }

    public List<Book> findByGenreName(String genreName) {
        return bookStoreRepository.findByGenre_Name(genreName);
    }

    public void updateNameById(String id, String newName) {
        bookStoreRepository.updateBookNameById(id, newName);
    }

    public void deleteById(String id) {
        bookStoreRepository.deleteById(id);
    }
}
