package ru.otus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Modifying
    @Query("update Book set name = :newName where id = :id")
    void updateNameById(@Param("id") long id, @Param("newName") String newName);
}
