package ru.otus.domain;

public class Book {

    private int id;
    private String name;
    private int authorId;
    private int genreId;

    public Book(int id, String name, int authorId, int genreId) {
        this.id = id;
        this.name = name;
        this.authorId = authorId;
        this.genreId = genreId;
    }

    @Override
    public String toString() {
        return "id:" + id + " name:" + name +
                " authorId: " + authorId + " genreId: " + genreId;
    }

    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public int getAuthorId() {
        return authorId;
    }

    public int getGenreId() {
        return genreId;
    }
}
