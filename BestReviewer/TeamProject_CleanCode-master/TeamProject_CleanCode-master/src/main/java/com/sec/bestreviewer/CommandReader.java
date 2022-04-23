package com.sec.bestreviewer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommandReader {
    String inputFileName;

    CommandReader(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public List<String> readFile() {
        List<String> list = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFileName))) {
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                list.add(line);
            }
        } catch (IOException exception) {
            throw new IllegalArgumentException("Input file " + inputFileName + " is NOT exist");
        }

        return list;
    }
}
