package ru.otus.rest;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.otus.domain.Book;
import ru.otus.service.BookService;

import java.util.Arrays;
import java.util.Collections;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @Test
    void listPage() throws Exception {
        given(this.bookService.findAll()).willReturn(singletonList(new Book("test")));
        this.mvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    void editPage() throws Exception {
        given(this.bookService.findById(1)).willReturn(new Book("test"));
        this.mvc.perform(get("/edit/1")).andExpect(status().isOk());
    }

    @Test
    void updatePage() throws Exception {
        given(this.bookService.findAll()).willReturn(singletonList(new Book("test")));
        this.mvc.perform(post("/update/1")).andExpect(status().isOk());
    }

    @Test
    void addSave() throws Exception {
        given(this.bookService.findAll()).willReturn(singletonList(new Book("test")));
        this.mvc.perform(post("/add")).andExpect(status().isOk());
    }

    @Test
    void addReturnView() throws Exception {
        this.mvc.perform(get("/add"))
                .andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {
        this.mvc.perform(get("/delete/1")).andExpect(status().isOk());
    }
}