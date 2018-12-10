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

    public TestServiceImpl(QuestionService questionService, AssessmentService assessmentService,
                           InputOutputUtils inputOutputUtils, String questionsCsvFileName) {
        this.questionService = questionService;
        this.assessmentService = assessmentService;
        this.inputOutputUtils = inputOutputUtils;
        this.questionsCsvFileName = questionsCsvFileName;
    }

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
