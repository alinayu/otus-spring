package ru.otus.model;

/**
 * Created by alina on 09.12.2018.
 */
public class Question {

    private String text;

    private String answer;

    public Question(String text, String answer) {
        this.text = text;
        this.answer = answer;
    }

    public String getText() {
        return text;
    }

    public String getAnswer() {
        return answer;
    }
}
