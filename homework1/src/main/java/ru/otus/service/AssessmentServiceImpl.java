package ru.otus.service;

import ru.otus.model.Question;
import ru.otus.utils.InputOutputUtils;

import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * Created by alina on 09.12.2018.
 */
public class AssessmentServiceImpl implements AssessmentService {

    private InputOutputUtils inputOutputUtils;

    public void setInputOutputUtils(InputOutputUtils inputOutputUtils) { this.inputOutputUtils = inputOutputUtils; }

    public int rate(BufferedReader reader, OutputStreamWriter writer, List<Question> questions) {
        int score = 0;
        for (Question q : questions) {
            inputOutputUtils.writeToOutput(writer, q.getText());
            if (inputOutputUtils.readlineFromInput(reader).equalsIgnoreCase(q.getAnswer())) {
                score++;
            }
        }
        return score;
    }
}
