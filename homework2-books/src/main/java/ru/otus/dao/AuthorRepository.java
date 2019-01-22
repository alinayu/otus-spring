package ru.otus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
