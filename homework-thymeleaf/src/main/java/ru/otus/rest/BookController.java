package ru.otus.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import ru.otus.domain.Book;
import ru.otus.service.BookService;

import javax.validation.Valid;

@Controller
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String listPage(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") int id, Model model) {
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        return "edit";
    }

    @PostMapping("/edit")
    public String editPage(@Valid Book book, Model model) {
        bookService.save(book);
        model.addAttribute("books", bookService.findAll());
        return "list";
    }

    @PostMapping("/add")
    public String add(@Valid Book book, Model model) {
        bookService.save(book);
        model.addAttribute("books", bookService.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String add(Book book) {
        return "add-book";
    }
}
