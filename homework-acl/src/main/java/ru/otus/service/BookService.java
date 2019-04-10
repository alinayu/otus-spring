package ru.otus.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Book;
import ru.otus.exception.BookNotFoundException;
import ru.otus.repository.BookRepository;

import java.util.List;

@Service
@Transactional
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    public Book findById(long id) {
        return bookRepository.findById(id).orElseThrow(() ->
                new BookNotFoundException("Book with id: " + id + " not found"));
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public void updateNameById(long id, String newName) {
        bookRepository.updateNameById(id, newName);
    }
}
