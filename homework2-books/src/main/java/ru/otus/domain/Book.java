package ru.otus.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Author author;
    @ManyToOne
    private Genre genre;

    public Book(long id, String name, long authorId, long genreId) {
        this.id = id;
        this.name = name;
        this.author = new Author(authorId);
        this.genre = new Genre(genreId);
    }

}
