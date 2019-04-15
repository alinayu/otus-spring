package ru.otus.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.domain.Book;
import ru.otus.domain.BookHistory;

@MessagingGateway
public interface BookHistoryService {

    @Gateway(requestChannel = "saveNameHistory.input")
    void save(BookHistory bookHistory);
}
