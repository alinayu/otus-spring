package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.model.Question;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.util.List;
import java.util.Locale;

import static java.lang.String.valueOf;
import static ru.otus.utils.InputOutputHelper.*;

/**
 * Created by alina on 09.12.2018.
 */
@Service
public class TestServiceImpl implements TestService  {

    private QuestionService questionService;

    private AssessmentService assessmentService;

    private MessageSource messageSource;

    @Value("${locale.language:en}")
    private String localeLanguage;

    @Value("${locale.country:US}")
    private String localeCountry;

    @Value("${questions.csv.file.name.${locale.language:en}:${questions.csv.file.name.default}}")
    private String actualQuestionsCsvFileName;

    private BufferedReader reader;

    private OutputStreamWriter writer;

    private Locale locale;

    private int score;

    @Autowired
    public TestServiceImpl(QuestionService questionService, AssessmentService assessmentService,
                           MessageSource messageSource) {
        this.questionService = questionService;
        this.assessmentService = assessmentService;
        this.messageSource = messageSource;
    }

    @PostConstruct
    public void init() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.writer = new OutputStreamWriter(System.out);
        this.locale = new Locale(localeLanguage, localeCountry);
    }

    @PreDestroy
    public void destroy() {
        close(reader);
        close(writer);
    }

    @Override
    public void login() {
        writeToOutput(writer, messageSource.getMessage("test.greetings", null, locale));
        readlineFromInput(reader);
    }

    @Override
    public void doTest() {
        List<Question> questions = questionService
                .getQuestionsFromCsvFile(actualQuestionsCsvFileName);
        score = assessmentService.rate(reader, writer, questions);
    }

    @Override
    public void writeScore() {
        writeToOutput(writer, messageSource.getMessage("test.result", new String[] { valueOf(score) },
                locale));
    }

    @Override
    public void setReader(InputStream in) {
        this.reader = new BufferedReader(new InputStreamReader(in));
    }

    @Override
    public void setWriter(OutputStream out) {
        this.writer = new OutputStreamWriter(out);
    }

    @Override
    public int getScore() {
        return  score;
    }


}
