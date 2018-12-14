package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import ru.otus.model.Question;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestServiceImplTest {
    @Mock
    private QuestionService questionService;
    @Mock
    private AssessmentService assessmentService;
    @Mock
    private MessageSource messageSource;

    private TestServiceImpl testService;

    @BeforeEach
    void setUp() {
        testService = new TestServiceImpl(questionService, assessmentService, messageSource);
        testService.setQuestionsCsvFileName("questionsCsvFileName");
        testService.setLocaleLanguage("ru");
        testService.setLocaleCountry("RU");
    }

    @Test
    void doTest() {
        String name = "name";
        String greetings = "greetings";
        String resultMessage = "resultMessage";
        InputStream inputStream = new ByteArrayInputStream(name.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        List<Question> questions = asList(new Question("question", "answer"));
        int score = 1;

        when(messageSource.getMessage(any(), any(), any())).thenReturn(greetings).thenReturn(resultMessage);
        when(questionService.getQuestionsFromCsvFile("questionsCsvFileName")).thenReturn(questions);
        when(assessmentService.rate(any(), any(), anyList())).thenReturn(score);

        testService.doTest(inputStream, outputStream);

        verify(questionService, times(1)).getQuestionsFromCsvFile(eq("questionsCsvFileName"));
        verify(assessmentService, times(1)).rate(any(), any(), eq(questions));

        assertThat(outputStream.toString()).isEqualTo(greetings + resultMessage);
    }
}