package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Book;
import ru.otus.domain.BookHistory;
import ru.otus.exception.BookNotFoundException;
import ru.otus.integration.BookHistoryService;
import ru.otus.repository.BookRepository;

import java.util.List;

@Service
@Transactional
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookHistoryService bookHistoryService;

    public void save(Book book) {
        bookRepository.save(book);
    }

    public void update(Book book) {
        bookHistoryService.save(BookHistory.toBookHistory(book));
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
}
