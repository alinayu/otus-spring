package ru.otus.dao;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.domain.Comment;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
class CommentRepositoryJpaTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void insert() {
        commentRepository.save(new Comment(1, "Perfect"));
        assertThat(commentRepository.findByBookId(1))
                .extracting("text")
                .contains("Perfect");
    }

    @Test
    void getByBookId() {
        assertThat(commentRepository.findByBookId(1))
                .extracting("text")
                .contains("excellent");
    }
}