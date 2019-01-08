package ru.otus.service;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by alina on 09.12.2018.
 */
public interface TestService {
    void login();
    void doTest();
    void writeScore();
    void setReader(InputStream in);
    void setWriter(OutputStream out);
    int getScore();
}
