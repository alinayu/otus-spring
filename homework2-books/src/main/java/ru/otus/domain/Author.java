package ru.otus.domain;

public class Author {

    private long id;
    private String firstName;
    private String lastName;

    public Author(long id) {
        this.id = id;
    }

    public Author(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return id + " " + lastName + " " + firstName;
    }
}
