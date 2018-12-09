package ru.otus.service;

import ru.otus.model.Question;
import ru.otus.utils.InputOutputUtils;

import java.io.*;
import java.util.List;

/**
 * Created by alina on 09.12.2018.
 */
public class TestServiceImpl implements TestService  {

    private QuestionService questionService;

    private AssessmentService assessmentService;

    private InputOutputUtils inputOutputUtils;

    private String questionsCsvFileName;

    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    public void setAssessmentService(AssessmentService assessmentService) { this.assessmentService = assessmentService; }

    public void setInputOutputUtils(InputOutputUtils inputOutputUtils) { this.inputOutputUtils = inputOutputUtils; }

    public void setQuestionsCsvFileName(String questionsCsvFileName) { this.questionsCsvFileName = questionsCsvFileName; }

    public void doTest(InputStream in, OutputStream out) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        OutputStreamWriter writer = new OutputStreamWriter(out);

        inputOutputUtils.writeToOutput(writer, "Представьтесь, пожалуйста: \n");
        inputOutputUtils.readlineFromInput(reader);

        List<Question> questions = questionService.getQuestionsFromCsvFile(questionsCsvFileName);


        inputOutputUtils.writeToOutput(writer, "Количество правильных ответов : " +
                assessmentService.rate(reader, writer, questions));
        inputOutputUtils.close(reader);
        inputOutputUtils.close(writer);
    }



}
