package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import ru.otus.model.Question;
import sun.util.locale.LocaleUtils;

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

    @Autowired
    public TestServiceImpl(QuestionService questionService, AssessmentService assessmentService,
                           MessageSource messageSource) {
        this.questionService = questionService;
        this.assessmentService = assessmentService;
        this.messageSource = messageSource;
    }

    public void doTest(InputStream in, OutputStream out) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        OutputStreamWriter writer = new OutputStreamWriter(out);
        Locale locale = new Locale(localeLanguage, localeCountry);

        writeToOutput(writer, messageSource.getMessage("test.greetings", null, locale));
        readlineFromInput(reader);

        List<Question> questions = questionService
                .getQuestionsFromCsvFile(actualQuestionsCsvFileName);

        writeToOutput(writer, messageSource.getMessage("test.result",
                new String[] { valueOf(assessmentService.rate(reader, writer, questions)) },
                locale));
        close(reader);
        close(writer);
    }



}
