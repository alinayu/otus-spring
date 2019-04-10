package ru.otus.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.otus.domain.Book;
import ru.otus.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class  BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<BookDto> getAll() {
        return bookService.findAll().stream().map(BookDto::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/books/{id}")
    public BookDto getById(@PathVariable("id") Long id) {return BookDto.toDto(bookService.findById(id));}

    @DeleteMapping("/books/{id}")
    @PreAuthorize("hasRole('EDITOR')")
    public void deleteById(@PathVariable("id") Long id) {
        bookService.deleteById(id);
    }

    @PostMapping("/books")
    @PreAuthorize("hasRole('EDITOR')")
    public void save(@RequestBody Book book) {
        bookService.save(book);
    }

    @PutMapping("/books/{id}")
    @PreAuthorize("hasRole('EDITOR')")
    public void update(@PathVariable("id") Long id, @RequestBody Book book) {
        bookService.save(book);
    }
}
