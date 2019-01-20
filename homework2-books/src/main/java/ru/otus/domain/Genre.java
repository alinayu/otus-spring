package ru.otus.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "author")
    private List<Book> books;

    public Genre(String name) {
        this.name = name;
    }

    public Genre(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id + " " + name;
    }
}
