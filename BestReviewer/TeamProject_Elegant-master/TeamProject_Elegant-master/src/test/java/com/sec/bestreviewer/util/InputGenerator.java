package com.sec.bestreviewer.util;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class InputGenerator {

    public static final String COMMA = ",";
    List<String> clList = Arrays.asList("CL1", "CL2", "CL3", "CL4");
    List<String> certiList = Arrays.asList("ADV", "PRO", "EX");
    Set<String> redundantChecker = new HashSet<>();

    void generateInputFile(String fileName, int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(generate()).append("\n");
        }

        try (OutputStream output = new FileOutputStream(fileName)) {
            output.write(sb.toString().getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    String generate() {
        StringBuilder sb = new StringBuilder();

        sb.append("ADD, , , ,");
        append(sb, getId());
        append(sb, getFirstName() + " " + getLastName());
        append(sb, getCl());
        append(sb, getPhoneNumber());
        append(sb, getBirthDay());
        sb.append(getCerti());

        return sb.toString();
    }

    private void append(StringBuilder sb, String id) {
        sb.append(id).append(COMMA);
    }

    String getId() {
        String id = generateId();
        while (!redundantChecker.add(id)) {
            id = generateId();
        }
        return id;
    }

    private String generateId() {
        return String.format("%02d", randomRange(1990, 2000) % 100) + String.format("%06d", randomRange(0, 999999));
    }

    String getFirstName() {
        StringBuilder sb = new StringBuilder();
        int count = randomRange(4, 10);
        for (int i = 0; i < count; i++) {
            sb.append(getAlphabet());
        }
        return sb.toString();
    }

    String getLastName() {
        StringBuilder sb = new StringBuilder();

        int count = randomRange(2, 5);
        for (int i = 0; i < count; i++) {
            sb.append(getAlphabet());
        }
        return sb.toString();
    }

    String getAlphabet() {
        return Character.toString((char) randomRange(65, 90));
    }

    String getCl() {
        return clList.get(randomRange(0, 3));
    }

    String getPhoneNumber() {
        return String.format("010-%04d-%04d", randomRange(100, 9999), randomRange(1000, 9999));
    }

    String getBirthDay() {
        return String.format("%02d%02d%02d", randomRange(1990, 2021), randomRange(1, 12), randomRange(1, 30));
    }

    String getCerti() {
        return certiList.get(randomRange(0, 2));
    }

    private int randomRange(int n1, int n2) {
        return (int) (Math.random() * (n2 - n1 + 1)) + n1;
    }
}
