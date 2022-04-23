package com.sec.bestreviewer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CommandReader {

    public List<String> readFile(final String inputFileName) {

        final List<String> lineList = new LinkedList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFileName))) {
            String line = bufferedReader.readLine();

            while (line != null) {
                lineList.add(line);
                line = bufferedReader.readLine();
            }

        } catch (FileNotFoundException exception) {
            throw new IllegalArgumentException("Input file " + inputFileName + " is NOT exist");

        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return lineList;
    }
}
