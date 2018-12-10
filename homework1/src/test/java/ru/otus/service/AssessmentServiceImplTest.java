package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.model.Question;
import ru.otus.utils.InputOutputUtils;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class AssessmentServiceImplTest {

    @Mock
    private InputOutputUtils inputOutputUtils;

    private AssessmentService assessmentService;

    @BeforeEach
    void setUp() {
        assessmentService = new AssessmentServiceImpl();
        ((AssessmentServiceImpl) assessmentService).setInputOutputUtils(inputOutputUtils);
    }

    @Test
    public void rateCountRightAnswerCorrectlyTest() {
        List<Question> questions = asList(new Question("question1", "answer"),
                new Question("question2", "answer"));
        given(inputOutputUtils.readlineFromInput(any())).willReturn("ANswer").willReturn("wrong");
        assertThat(assessmentService.rate(any(), any(), questions))
            .isEqualTo(1);
        verify(inputOutputUtils, times(2)).readlineFromInput(any());
        verify(inputOutputUtils, times(1)).writeToOutput(any(), eq(questions.get(0).getText()));
        verify(inputOutputUtils, times(1)).writeToOutput(any(), eq(questions.get(1).getText()));
    }

}