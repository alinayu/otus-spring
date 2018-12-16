package ru.otus.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.model.Question;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

/**
 * Created by alina on 09.12.2018.
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Value("${csv.record.size}")
    private int csvRecordSize;

    public void setCsvRecordSize(int csvRecordSize) {
        this.csvRecordSize = csvRecordSize;
    }

    public List<Question> getQuestionsFromCsvFile(String fileName) {
        List<Question> questions = new ArrayList<>();
        try {
            CSVParser.parse(getReaderFromResource(fileName), CSVFormat.DEFAULT)
                    .getRecords()
                    .forEach(record -> questions.add(getQuestionFromRecord(record)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questions;
    }

    private Question getQuestionFromRecord(CSVRecord record) {
        if (record.size() != csvRecordSize) {
            throw new IncorrectFileFormatException("Incorrect format file");
        }
        return new Question(record.get(0), record.get(1));
    }

    private Reader getReaderFromResource(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new FileNotFoundException(format("File %s not found", fileName));
        }
        return new InputStreamReader(inputStream);
    }
}