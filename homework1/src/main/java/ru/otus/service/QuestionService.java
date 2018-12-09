package ru.otus.service;

import ru.otus.model.Question;

import java.util.List;

/**
 * Created by alina on 09.12.2018.
 */
public interface QuestionService {
    List<Question> getQuestionsFromCsvFile(String fileName);
}
