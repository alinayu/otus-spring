package ru.otus.rest;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.domain.Book;
import ru.otus.service.BookService;

import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @Test
    void getAll() throws Exception {
        given(this.bookService.findAll()).willReturn(singletonList(new Book(1, "test")));
        this.mvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("test")));
    }

    @Test
    void getById() throws Exception {
        given(this.bookService.findById(1)).willReturn(new Book(1, "test"));
        this.mvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("test")));
    }

    @Test
    void deleteById() throws Exception {
        this.mvc.perform(delete("/books/1"))
                .andExpect(status().isOk());
        verify(bookService, times(1)).deleteById(1);
    }

    @Test
    void save() throws Exception {
        Book book = new Book("test");
        ObjectMapper objectMapper = new ObjectMapper();
        this.mvc.perform(post("/books")
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk());
        verify(bookService, times(1)).save(book);
    }

    @Test
    void update() throws Exception {
        Book book = new Book(1, "test");
        ObjectMapper objectMapper = new ObjectMapper();
        this.mvc.perform(put("/books/1")
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk());
        verify(bookService, times(1)).save(book);
    }
}