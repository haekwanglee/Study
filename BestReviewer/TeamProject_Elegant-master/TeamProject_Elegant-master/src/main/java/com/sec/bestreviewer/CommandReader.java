package com.sec.bestreviewer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CommandReader {
    String inputFileName;

    CommandReader(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public List<String> readFile() {
        List<String> list = new ArrayList<>();
        try (
                FileInputStream fileInputStream = new FileInputStream(inputFileName);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(inputStreamReader)
        ) {
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                list.add(line);
            }
        } catch (FileNotFoundException exception) {
            throw new IllegalArgumentException("Input file " + inputFileName + " is NOT exist");
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return list;
    }
}
