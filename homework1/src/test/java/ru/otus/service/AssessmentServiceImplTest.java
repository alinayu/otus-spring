package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.model.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class AssessmentServiceImplTest {

    private AssessmentService assessmentService;

    @BeforeEach
    void setUp() {
        assessmentService = new AssessmentServiceImpl();
    }

    @Test
    public void rateCountRightAnswerCorrectlyTest() throws IOException {
        OutputStreamWriter outputStreamWriter = mock(OutputStreamWriter.class);
        List<Question> questions = asList(new Question("question1", "answer"),
                new Question("question2", "answer"));
        BufferedReader reader = new BufferedReader(new StringReader("ANswer\nwrong"));

        assertThat(assessmentService.rate(reader, outputStreamWriter, questions)).isEqualTo(1);
        verify(outputStreamWriter, times(1)).write(eq(questions.get(0).getText()));
        verify(outputStreamWriter, times(1)).write(eq(questions.get(1).getText()));
        verify(outputStreamWriter, times(2)).flush();
    }

}