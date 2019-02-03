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

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

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
        return mongoTemplate.aggregate(newAggregation(
                match(Criteria.where("_id").is(id)),
                unwind("comments"),
                project().andExclude("_id").andInclude("comments.text")), Book.class, Comment.class).getMappedResults();
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

    @Override
    public void deleteCommentsByBookId(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.unset("comments");
        mongoTemplate.updateFirst(query, update, Book.class);
    }
}
