package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.model.Question;
import ru.otus.utils.InputOutputUtils;

import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TestServiceImplTest {

    @Mock
    private InputOutputUtils inputOutputUtils;
    @Mock
    private QuestionService questionService;
    @Mock
    private AssessmentService assessmentService;

    private String questionsCsvFileName;

    private TestService testService;

    @BeforeEach
    void setUp() {
        questionsCsvFileName = "questionsCsvFileName";
        testService = new TestServiceImpl(questionService, assessmentService, inputOutputUtils, questionsCsvFileName);
    }

    @Test
    void doTest() {
        List<Question> questions = asList(new Question("question", "answer"));
        int score = 5;
        given(questionService.getQuestionsFromCsvFile(anyString())).willReturn(questions);
        given(assessmentService.rate(any(), any(), anyList())).willReturn(score);

        testService.doTest(System.in, System.out);

        verify(inputOutputUtils, times(1)).writeToOutput(any(), eq("Представьтесь, пожалуйста: \n"));
        verify(inputOutputUtils, times(1)).readlineFromInput(any());
        verify(questionService, times(1)).getQuestionsFromCsvFile(eq(questionsCsvFileName));
        verify(assessmentService, times(1)).rate(any(), any(), anyList());
        verify(inputOutputUtils, times(1)).writeToOutput(any(), eq("Количество правильных ответов : " + score));
        verify(inputOutputUtils, times(1)).close(any(BufferedReader.class));
        verify(inputOutputUtils, times(1)).close(any(OutputStreamWriter.class));
    }
}