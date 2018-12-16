package ru.otus.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by alina on 09.12.2018.
 */
public class InputOutputHelper {

    public static void writeToOutput(OutputStreamWriter writer, String str) {
        try {
            writer.write(str);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readlineFromInput(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(BufferedReader reader) {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close(OutputStreamWriter writer) {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
