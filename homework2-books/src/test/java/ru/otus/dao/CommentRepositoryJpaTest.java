package ru.otus.dao;

import org.assertj.core.api.Java6Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.domain.Comment;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@Import(CommentRepositoryJpa.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CommentRepositoryJpaTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void insert() {
        commentRepository.insert(new Comment(1, "Perfect"));
        assertThat(commentRepository.getByBookId(1))
                .extracting("text")
                .contains("Perfect");
    }

    @Test
    void getByBookId() {
        assertThat(commentRepository.getByBookId(1))
                .extracting("text")
                .contains("excellent");
    }
}