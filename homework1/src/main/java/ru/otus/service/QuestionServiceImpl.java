package ru.otus.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import ru.otus.model.Question;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alina on 09.12.2018.
 */
public class QuestionServiceImpl implements QuestionService {

    public List<Question> getQuestionsFromCsvFile(String fileName) {
        List<Question> questions = new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        try {
            CSVParser.parse(new FileReader(file), CSVFormat.DEFAULT)
                    .forEach(record -> questions.add(new Question(record.get(0), record.get(1))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questions;
    }
}
