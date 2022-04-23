package com.sec.bestreviewer;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Printer {
    String outputFilName;
    private final StringBuilder stdStringBuilder;

    public Printer(String outputFilename) {
        this(outputFilename, new StringBuilder());
    }

    public Printer(String outputFilename, StringBuilder stdStringBuilder) {
        this.outputFilName = outputFilename;
        this.stdStringBuilder = stdStringBuilder;
    }

    public void add(List<String> lines) {
        for (String line : lines)
            stdStringBuilder.append(line).append("\n");
    }

    public void printOutputFile() {
        try (OutputStream output = new FileOutputStream(outputFilName)) {
            output.write(stdStringBuilder.toString().getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
