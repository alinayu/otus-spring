package ru.otus.repository;

import com.mongodb.client.result.UpdateResult;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.domain.Author;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;

import java.util.List;

public interface BookCustomRepository {

    Flux<Author> findAllDistinctAuthors();

    Flux<Genre> findAllDistinctGenres();

    Flux<Comment> findCommentsByBookId(String id);

    Mono<UpdateResult> updateBookNameById(String id, String newName);

    Mono<UpdateResult> addCommentByBookId(String id, Comment comment);

    Mono<UpdateResult> deleteCommentsByBookId(String id);

}
