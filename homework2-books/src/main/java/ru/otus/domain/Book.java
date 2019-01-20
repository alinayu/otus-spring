package ru.otus.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToOne
    private Author author;
    @ManyToOne
    private Genre genre;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "book")
    private List<Comment> comments;

    public Book(long id) { this.id = id; }

    public Book(String name, long authorId, long genreId) {
        this.name = name;
        this.author = new Author(authorId);
        this.genre = new Genre(genreId);
    }

    @Override
    public String toString() {
        return id + " " + name + " author: " + author + " genre: " + genre;
    }

}
