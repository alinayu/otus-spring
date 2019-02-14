package ru.otus.rest;

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
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
        this.mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("list"))
                .andExpect(model().attribute("books", hasSize(1)))
                .andExpect(model().attribute("books", hasItem(allOf(hasProperty("name", is("test"))))));
    }

    @Test
    void editPage() throws Exception {
        given(this.bookService.findById(1)).willReturn(new Book("test"));
        this.mvc.perform(get("/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("edit"))
                .andExpect(model().attribute("book", hasProperty("name", is("test"))))
        ;
    }

    @Test
    void updatePage() throws Exception {
        given(this.bookService.findAll()).willReturn(singletonList(new Book("test")));
        this.mvc.perform(post("/update/1"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void addSave() throws Exception {
        given(this.bookService.findAll()).willReturn(singletonList(new Book("test")));
        this.mvc.perform(post("/add"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void addReturnView() throws Exception {
        this.mvc.perform(get("/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-book"));
    }

    @Test
    void delete() throws Exception {
        given(this.bookService.findAll()).willReturn(singletonList(new Book("test")));
        this.mvc.perform(post("/delete/1"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"));
    }
}