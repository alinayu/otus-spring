package ru.otus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.domain.BookHistory;

public interface BookHistoryRepository extends JpaRepository<BookHistory, Long> {
}
