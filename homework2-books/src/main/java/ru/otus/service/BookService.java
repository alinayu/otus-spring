package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.dao.BookRepository;
import ru.otus.domain.Book;
import ru.otus.exception.BookNotFoundException;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public void save(Book book) {
        bookRepository.save(book);
    }

    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    public Book getById(long id) {
        return bookRepository.findById(id).orElseThrow(() ->
                new BookNotFoundException("Book with id: " + id + " not found"));
    }

    public List<Book> getByAuthorId(long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public void updateNameById(long id, String newName) {
        bookRepository.updateNameById(id, newName);
    }
}
