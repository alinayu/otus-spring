package ru.otus.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.domain.Book;

@MessagingGateway
public interface BookProcessingService {

    @Gateway(requestChannel = "updatedBooks")
    Book updateName(Book input);
}
