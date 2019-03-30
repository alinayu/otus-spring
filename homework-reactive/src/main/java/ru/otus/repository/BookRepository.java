package ru.otus.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import ru.otus.domain.Book;

import java.util.List;

public interface BookRepository extends ReactiveMongoRepository<Book, String>, BookCustomRepository {

    Flux<Book> findByAuthor_LastNameAndAuthor_FirstName(String authorLastName, String authorFirstName);

    Flux<Book> findByGenre_Name(String genreName);

    void removeByAuthor_LastNameAndAuthor_FirstName(String authorLastName, String authorFirstName);

    void removeByGenre_Name(String genreName);

}
