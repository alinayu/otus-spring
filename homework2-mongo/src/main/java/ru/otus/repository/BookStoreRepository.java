package ru.otus.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.domain.Book;

import java.util.List;

public interface BookStoreRepository extends MongoRepository<Book, String>, BookStoreCustomRepository {

    List<Book> findByAuthor_LastNameAndAuthor_FirstName(String authorLastName, String authorFirstName);

    List<Book> findByGenre_Name(String genreName);

    void removeByAuthor_LastNameAndAuthor_FirstName(String authorLastName, String authorFirstName);

    void removeByGenre_Name(String genreName);

}
