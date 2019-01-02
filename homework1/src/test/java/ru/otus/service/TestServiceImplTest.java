package ru.otus.service;

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
    private TestService testService;

    @Test
    void doTest() {
        String name = "name";
        String greetings = "greetings";
        String resultMessage = "resultMessage";
        List<Question> questions = asList(new Question("question", "answer"));
        int score = 1;
        InputStream inputStream = new ByteArrayInputStream(name.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        when(messageSource.getMessage(any(), any(), any())).thenReturn(greetings).thenReturn(resultMessage);
        when(questionService.getQuestionsFromCsvFile("questions_test_en.csv")).thenReturn(questions);
        when(assessmentService.rate(any(), any(), anyList())).thenReturn(score);

        testService.doTest(inputStream, outputStream);

        verify(questionService, times(1)).getQuestionsFromCsvFile(eq("questions_test_en.csv"));
        verify(assessmentService, times(1)).rate(any(), any(), eq(questions));

        assertThat(outputStream.toString()).isEqualTo(greetings + resultMessage);
    }
}