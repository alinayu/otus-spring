package ru.otus.domain;

public class Genre {
    private long id;
    private String name;

    public Genre(long id) {
        this.id = id;
    }

    public Genre(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return id +  " " + name;
    }
}
