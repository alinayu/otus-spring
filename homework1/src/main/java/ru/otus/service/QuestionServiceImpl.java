package ru.otus.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import ru.otus.model.Question;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

/**
 * Created by alina on 09.12.2018.
 */
public class QuestionServiceImpl implements QuestionService {

    public List<Question> getQuestionsFromCsvFile(String fileName) {
        List<Question> questions = new ArrayList<>();
        File file = getFileFromResource(fileName);

        try {
            CSVParser.parse(new FileReader(file), CSVFormat.DEFAULT)
                    .forEach(record -> questions.add(new Question(record.get(0), record.get(1))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questions;
    }

    private File getFileFromResource(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new FileNotFoundException(format("Файл %s не найден", fileName));
        }
        return new File(resource.getFile());
    }
}
