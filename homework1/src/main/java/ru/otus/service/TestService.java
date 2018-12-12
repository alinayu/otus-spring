package ru.otus.service;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by alina on 09.12.2018.
 */
public interface TestService {
    void doTest(InputStream in, OutputStream out);
}
