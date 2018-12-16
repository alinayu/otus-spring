package ru.otus.service;

import org.springframework.stereotype.Service;
import ru.otus.model.Question;
import ru.otus.utils.InputOutputHelper;

import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;
import static ru.otus.utils.InputOutputHelper.readlineFromInput;
import static ru.otus.utils.InputOutputHelper.writeToOutput;

/**
 * Created by alina on 09.12.2018.
 */
@Service
public class AssessmentServiceImpl implements AssessmentService {

    public int rate(BufferedReader reader, OutputStreamWriter writer, List<Question> questions) {
        int score = 0;
        for (Question q : questions) {
            writeToOutput(writer, q.getText());
            String answer = readlineFromInput(reader);
            if (nonNull(answer) && answer.equalsIgnoreCase(q.getAnswer())) {
                score++;
            }
        }
        return score;
    }
}
