package ru.otus.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private long id;
    private String name;

    @ManyToOne
    private Author author;
    @ManyToOne
    private Genre genre;

    public Book(long id, String name, Author author, Genre genre) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    public Book(long id, String name, long authorId, long genreId) {
        this.id = id;
        this.name = name;
        this.author = new Author(authorId);
        this.genre = new Genre(genreId);
    }

    @Override
    public String toString() {
        return "id:" + id + " name:" + name +
                " author: " + author.getId() + " " + author.getLastName() + " " + author.getFirstName() +
                " genre: " + genre.getId() + " " + genre.getName();
    }

    public long getId() { return id; }

    public String getName() {
        return name;
    }

    public Author getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }
}
