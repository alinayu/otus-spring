package ru.otus.service;

import ru.otus.model.Question;

import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * Created by alina on 09.12.2018.
 */
public interface AssessmentService {

    int rate(BufferedReader reader, OutputStreamWriter writer, List<Question> questions);
}
