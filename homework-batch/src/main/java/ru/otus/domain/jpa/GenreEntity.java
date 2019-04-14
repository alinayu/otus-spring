package ru.otus.domain.jpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Entity
@Table(name = "genres")
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    public GenreEntity(String name) {
        this.name = name;
    }

    public GenreEntity(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id + " " + name;
    }
}
