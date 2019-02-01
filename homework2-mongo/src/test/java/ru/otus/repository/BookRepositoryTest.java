package ru.otus.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@RunWith(SpringRunner.class)
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void save() {
        Author author = new Author("Николай", "Гоголь");
        Genre genre = new Genre("Поэма");
        Book book = new Book("Мертвые души", author, genre);
        bookRepository.save(book);

        bookRepository.findAll().forEach(System.out::println);
    }
}