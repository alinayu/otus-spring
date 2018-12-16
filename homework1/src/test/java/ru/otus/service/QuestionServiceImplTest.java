package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import ru.otus.model.Question;

import java.util.List;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QuestionServiceImplTest {

    private QuestionServiceImpl questionService;

    @BeforeEach
    void setUp() {
        questionService = new QuestionServiceImpl();
        questionService.setCsvRecordSize(2);
    }

    @Test
    void getQuestionsFromCsvFileTest() {
        List<Question> result = questionService.getQuestionsFromCsvFile("test.csv");
        assertThat(result).hasSize(2);
        assertThat(result).extracting("text").contains("q1", "q2");
        assertThat(result).extracting("answer").contains("a1", "a2");
    }

    @Test
    void getQuestionsFromCsvFileShouldReturnEmptyListWhenFileIsEmptyTest() {
        List<Question> result = questionService.getQuestionsFromCsvFile("empty.csv");
        assertThat(result).hasSize(0);
    }

    @Test
    void getQuestionsFromCsvFileShouldThrowExceptionWhenFileNotFoundTest() {
        String notExistFileName = "not_exist.csv";
        assertThrows(FileNotFoundException.class,
                () -> questionService.getQuestionsFromCsvFile(notExistFileName),
                format("File %s not found", notExistFileName));
    }

    @Test
    void getQuestionsFromCsvFileShouldThrowExceptionWhenFileHasIncorrectFormatTest() {
        String incorectFormatFileName = "incorrect_format.csv";
        assertThrows(IncorrectFileFormatException.class,
                () -> questionService.getQuestionsFromCsvFile(incorectFormatFileName), "Incorrect format file");
    }
}