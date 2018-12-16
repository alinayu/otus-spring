package ru.otus.service;

public class IncorrectFileFormatException extends RuntimeException {
    public IncorrectFileFormatException(String message) {
        super(message);
    }
}
