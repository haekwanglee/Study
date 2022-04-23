package com.sec.bestreviewer;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmployeeManagementTest {
    @Test
    public void testArgumentsEmptyInputFile() throws Exception {
        File inputFile = File.createTempFile("empty", ".txt");
        String outputFileName = "./src/test/java/com/sec/bestreviewer/output.txt";
        String[] args = {inputFile.getAbsolutePath(), outputFileName};
        EmployeeManagement employeeManagement = new EmployeeManagement();
        employeeManagement.run(args);

        File file = new File(outputFileName);

        assertTrue(file.exists());
    }

    @Test
    public void testArgumentsWrongArgsCount() throws Exception {
        String[] args = {"input.txt"};

        EmployeeManagement employeeManagement = new EmployeeManagement();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            employeeManagement.run(args);
        });
    }

    @Test
    public void testArgumentsNotExistInputFile() throws Exception {
        String[] args = {"notexist.txt", "output.txt"};

        EmployeeManagement employeeManagement = new EmployeeManagement();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            employeeManagement.run(args);
        });
    }

    @Test
    public void testArgumentsWrongCommand() throws Exception {
        final String inputFileName = "./src/test/java/com/sec/bestreviewer/wrong_command_test_input.txt";
        final String outputFileName = "./src/test/java/com/sec/bestreviewer/wrong_command_test_output.txt";

        String[] args = {inputFileName, outputFileName};
        File outputFile = new File(outputFileName);

        EmployeeManagement employeeManagement = new EmployeeManagement();

        employeeManagement.run(args);

        Approvals.verify(outputFile);
    }

    @Test
    public void testIntegration() throws Exception {
        final String outputFileName = "./src/test/java/com/sec/bestreviewer/integration_test_output.txt";
        final String inputFileName = "./src/test/java/com/sec/bestreviewer/integration_test_input.txt";

        String[] args = {inputFileName, outputFileName};
        File outputFile = new File(outputFileName);

        EmployeeManagement employeeManagement = new EmployeeManagement();

        employeeManagement.run(args);

        Approvals.verify(outputFile);
    }

    @Test
    public void testLargeIntegration() throws Exception {
        final String inputFileName = "./src/test/java/com/sec/bestreviewer/large_test_input.txt";
        final String outputFileName = "./src/test/java/com/sec/bestreviewer/large_test_output.txt";

        testCreateLargeInputFile(inputFileName);

        String[] args = {inputFileName, outputFileName};
        File outputFile = new File(outputFileName);

        EmployeeManagement employeeManagement = new EmployeeManagement();

        employeeManagement.run(args);

        Approvals.verify(outputFile);
    }

    public void testCreateLargeInputFile(String inputFileName) {
        try {
            File file = new File(inputFileName);
            if (!file.createNewFile()) {
                return;
            }

            FileOutputStream output = new FileOutputStream(file);

            // ADD
            for (int i = 0; i < 100000; i++) {
                String inputStr = "ADD, , , ,"
                        + makeEmployeeNumber(i) + ","
                        + makeRandomName() + ","
                        + makeRandomCL() + ","
                        + makeRandomPhoneNumber() + ","
                        + makeRandomBirth() + ","
                        + makeRandomCerti() + "\n";

                output.write(inputStr.getBytes());
                output.flush();
            }
            output.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String makeEmployeeNumber(int i) {
        Random rand = new Random();
        int year = 90 + rand.nextInt(30);

        return String.format("%02d%06d", year % 100, i + 1);
    }

    String makeRandomName() {
        Random rand = new Random();
        String[] firstNames =
                {"UAFBOWU", "TYLNXWQN", "NSESFBLRK", "VWCGM", "QSJYRB", "VLPDHI", "JYFMBMT", "KYUMOK", "GILDONG"};
        String[] LastNames = {"KIM", "LEE", "PARK", "CHOI", "AN", "OH", "HAN", "HONG"};
        return firstNames[rand.nextInt(9) ] + " " + LastNames[rand.nextInt(8)];
    }

    String makeRandomCL() {
        Random rand = new Random();
        switch (rand.nextInt(4)) {
            case 0:
                return "CL1";
            case 1:
                return "CL2";
            case 2:
                return "CL3";
            case 3:
                return "CL4";
        }
        return "CL1";
    }

    String makeRandomPhoneNumber() {
        Random rand = new Random();
        int middle = rand.nextInt(10000);
        int last = rand.nextInt(10000);

        return String.format("010-%04d-%04d", middle, last);
    }

    String makeRandomBirth() {
        Random rand = new Random();
        int year = 1970 + (rand.nextInt(30));
        int month = 1 + (rand.nextInt(12));
        int day = 1;
        if (month == 2) {
            day += rand.nextInt(28);
        } else {
            day += rand.nextInt(30);
        }

        return String.format("%04d%02d%02d", year, month, day);
    }

    String makeRandomCerti() {
        Random rand = new Random();
        switch (rand.nextInt(3)) {
            case 0:
                return "ADV";
            case 1:
                return "PRO";
            case 2:
                return "EX";
        }
        return "ADV";
    }
}
