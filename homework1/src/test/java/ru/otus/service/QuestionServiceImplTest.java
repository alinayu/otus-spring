package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.model.Question;

import java.util.List;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QuestionServiceImplTest {

    private QuestionService questionService;

    @BeforeEach
    void setUp() {
        questionService = new QuestionServiceImpl();
    }

    @Test
    void getQuestionsFromCsvFile() {
        List<Question> result = questionService.getQuestionsFromCsvFile("test.csv");
        assertThat(result).hasSize(2);
        assertThat(result).extracting("text").contains("q1", "q2");
        assertThat(result).extracting("answer").contains("a1", "a2");
    }

    @Test
    void getQuestionsFromCsvFileShouldReturnEmptyListWhenFileIsEmpty() {
        List<Question> result = questionService.getQuestionsFromCsvFile("empty.csv");
        assertThat(result).hasSize(0);
    }

    @Test
    void getQuestionsFromCsvFileShouldThrowExceptionWhenFileNotFound() {
        String notExistFileName = "not_exist.csv";
        assertThrows(FileNotFoundException.class,
                () -> questionService.getQuestionsFromCsvFile(notExistFileName),
                format("Файл %s не найден", notExistFileName));
    }
}