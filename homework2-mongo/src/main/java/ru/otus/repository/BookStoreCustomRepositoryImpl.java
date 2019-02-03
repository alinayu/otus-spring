package ru.otus.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import ru.otus.exception.BookNotFoundException;

import java.util.List;

import static java.util.Optional.ofNullable;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

public class BookStoreCustomRepositoryImpl implements BookStoreCustomRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Author> findAllDistinctAuthors() {
        return mongoTemplate.aggregate(newAggregation(group("author.lastName", "author.firstName")),
                Book.class, Author.class)
                .getMappedResults();
    }

    @Override
    public List<Genre> findAllDistinctGenres() {
        return mongoTemplate.findDistinct("genre.name", Book.class, Genre.class);
    }

    @Override
    public List<Comment> findCommentsByBookId(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        query.fields().include("comments");
        Book book = ofNullable(mongoTemplate.findOne(query, Book.class))
                .orElseThrow(() -> new BookNotFoundException("Book with id: " + id + " not found"));
        return book.getComments();
    }

    @Override
    public void updateBookNameById(String id, String newName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("name", newName);
        mongoTemplate.updateFirst(query, update, Book.class);
    }

    @Override
    public void addCommentByBookId(String id, Comment comment) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.push("comments", comment);
        mongoTemplate.updateFirst(query, update, Book.class);
    }
}
