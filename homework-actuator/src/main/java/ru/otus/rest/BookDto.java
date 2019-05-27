package ru.otus.rest;

import ru.otus.domain.Book;

public class BookDto {

    private long id = -1;
    private String name;

    public BookDto() {
    }

    public BookDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static BookDto toDto(Book book) {
        return new BookDto(book.getId(), book.getName());
    }
}
