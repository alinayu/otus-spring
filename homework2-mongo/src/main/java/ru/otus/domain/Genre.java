package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Genre {

    private String name;

    @Override
    public String toString() {
        return "Жанр: " + name;
    }
}
