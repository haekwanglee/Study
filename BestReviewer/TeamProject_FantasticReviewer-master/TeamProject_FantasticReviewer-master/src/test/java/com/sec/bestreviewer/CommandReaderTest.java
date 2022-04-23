package com.sec.bestreviewer;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CommandReaderTest {

    @Test
    void testRead() {
        String inputFileName = "command_reader_test_input.txt";
        String inputFilePath = "./src/test/java/com/sec/bestreviewer/" + inputFileName;
        CommandReader reader = new CommandReader(inputFilePath);

        String outputPath = "./src/test/java/com/sec/bestreviewer/command_reader_test_output.txt";
        try (BufferedReader output = new BufferedReader(new FileReader(outputPath, StandardCharsets.UTF_8))) {
            List<String> lines = reader.readFile();
            for (String line : lines) {
                assertEquals(line, output.readLine());
            }
            assertNull(output.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}