package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.dao.BookRepository;
import ru.otus.domain.Book;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public void save(Book book) {
        bookRepository.insert(book);
    }

    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    public Book getById(long id) {
        return bookRepository.getById(id);
    }

    public List<Book> getByAuthorId(long authorId) {
        return bookRepository.getByAuthorId(authorId);
    }

    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    public void updateNameById(long id, String newName) {
        bookRepository.updateNameById(id, newName);
    }
}
