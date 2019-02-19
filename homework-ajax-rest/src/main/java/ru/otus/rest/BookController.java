package ru.otus.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.otus.domain.Book;
import ru.otus.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book/list")
    public List<BookDto> getAll() {
        return bookService.findAll().stream().map(BookDto::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/book/delete/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        bookService.deleteById(id);
    }

    @PostMapping("/book/save")
    public void save(@RequestBody Book book) {
        bookService.save(book);
    }

    @GetMapping("/book/get/{id}")
    public BookDto getById(@PathVariable("id") Long id) {return BookDto.toDto(bookService.findById(id));}
}
