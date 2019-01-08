package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.test.context.TestPropertySource;
import ru.otus.model.Question;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestPropertySource(value = "classpath:application-test.properties")
class TestServiceImplTest {

    @MockBean
    private QuestionService questionService;
    @MockBean
    private AssessmentService assessmentService;
    @MockBean
    private MessageSource messageSource;
    @Autowired
    private TestServiceImpl testService;

    @Test
    void loginTest() {
        String name = "name";
        String greetings = "greetings";
        InputStream inputStream = new ByteArrayInputStream(name.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        testService.setReader(inputStream);
        testService.setWriter(outputStream);
        when(messageSource.getMessage(any(), any(), any())).thenReturn(greetings);

        testService.login();

        assertThat(outputStream.toString()).isEqualTo(greetings);
    }

    @Test
    void doTest() {
        List<Question> questions = asList(new Question("question", "answer"));
        int score = 1;
        when(questionService.getQuestionsFromCsvFile("questions_test_en.csv")).thenReturn(questions);
        when(assessmentService.rate(any(), any(), anyList())).thenReturn(score);

        testService.doTest();

        verify(questionService, times(1)).getQuestionsFromCsvFile(eq("questions_test_en.csv"));
        verify(assessmentService, times(1)).rate(any(), any(), eq(questions));
        assertThat(testService.getScore()).isEqualTo(score);
    }

    @Test
    void writeScoreTest() {
        String resultMessage = "resultMessage";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        testService.setWriter(outputStream);
        when(messageSource.getMessage(any(), any(), any())).thenReturn(resultMessage);

        testService.writeScore();

        assertThat(outputStream.toString()).isEqualTo(resultMessage);
    }

}