package ru.otus.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String text;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Book book;

    public Comment(long bookId, String text) {
        this.book = new Book(bookId);
        this.text = text;
    }

    @Override
    public String toString() {
        return book.getName() + " : " + text;
    }
}
