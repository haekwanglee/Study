package com.sec.bestreviewer.goldenmaster;

import com.sec.bestreviewer.EmployeeManagement;
import org.approvaltests.Approvals;
import org.approvaltests.approvers.FileApprover;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class CommandExecutorGoldenMasterTest {
    private static final String GOLDEN_MASTER_DIRECTORY = "./src/test/java/com/sec/bestreviewer/goldenmaster/";
    private static final String SEARCH_DIRECTORY = "search/";
    private static final String MODIFY_DIRECTORY = "modify/";
    private static final String DELETE_DIRECTORY = "delete/";
    private static final String LOGICAL_DIRECTORY = "logical/";

    String inputSettingFileName;

    @BeforeEach
    public void setUpEachTest() {
        inputSettingFileName = "./src/test/java/com/sec/bestreviewer/command_add_test_input.txt";
    }

    @ParameterizedTest
    @MethodSource
    public void commandExecutorApprovedTest(String inputCommandFileName, String approvedResultFileName) throws IOException {

        final String realTestInputFile = concatenateInputFiles(inputSettingFileName, inputCommandFileName);
        final String outputFileName = "./src/test/java/com/sec/bestreviewer/goldenmaster/test_output.txt";

        String[] args = {realTestInputFile, outputFileName};

        File outputFile = new File(outputFileName);
        if (!outputFile.exists())
            outputFile.createNewFile();

        assertTrue(outputFile.exists());

        EmployeeManagement employeeManagement = new EmployeeManagement();
        employeeManagement.run(args);

        File approvalFile = new File(approvedResultFileName);
        assertTrue(approvalFile.exists());

        assertTrue(FileApprover.approveTextFile(outputFile, approvalFile));
    }

    static Stream<Arguments> commandExecutorApprovedTest() {
        return Stream.of(
                arguments(GOLDEN_MASTER_DIRECTORY + SEARCH_DIRECTORY + "command_search_test1.txt", GOLDEN_MASTER_DIRECTORY + SEARCH_DIRECTORY + "searchTest_1.approved.txt"),
                arguments(GOLDEN_MASTER_DIRECTORY + SEARCH_DIRECTORY + "command_search_test2.txt", GOLDEN_MASTER_DIRECTORY + SEARCH_DIRECTORY + "searchTest_2.approved.txt"),
                arguments(GOLDEN_MASTER_DIRECTORY + SEARCH_DIRECTORY + "command_search_test3.txt", GOLDEN_MASTER_DIRECTORY + SEARCH_DIRECTORY + "searchTest_3.approved.txt"),
                arguments(GOLDEN_MASTER_DIRECTORY + SEARCH_DIRECTORY + "command_search_test4.txt", GOLDEN_MASTER_DIRECTORY + SEARCH_DIRECTORY + "searchTest_4.approved.txt"),
                arguments(GOLDEN_MASTER_DIRECTORY + SEARCH_DIRECTORY + "command_search_test5.txt", GOLDEN_MASTER_DIRECTORY + SEARCH_DIRECTORY + "searchTest_5.approved.txt"),
                arguments(GOLDEN_MASTER_DIRECTORY + SEARCH_DIRECTORY + "command_search_test6.txt", GOLDEN_MASTER_DIRECTORY + SEARCH_DIRECTORY + "searchTest_6.approved.txt"),
                arguments(GOLDEN_MASTER_DIRECTORY + MODIFY_DIRECTORY + "modify_test1.txt", GOLDEN_MASTER_DIRECTORY + MODIFY_DIRECTORY + "modify_test1.approved.txt"),
                arguments(GOLDEN_MASTER_DIRECTORY + MODIFY_DIRECTORY + "modify_test2.txt", GOLDEN_MASTER_DIRECTORY + MODIFY_DIRECTORY + "modify_test2.approved.txt"),
                arguments(GOLDEN_MASTER_DIRECTORY + MODIFY_DIRECTORY + "modify_test3.txt", GOLDEN_MASTER_DIRECTORY + MODIFY_DIRECTORY + "modify_test3.approved.txt"),
                arguments(GOLDEN_MASTER_DIRECTORY + MODIFY_DIRECTORY + "modify_test4.txt", GOLDEN_MASTER_DIRECTORY + MODIFY_DIRECTORY + "modify_test4.approved.txt"),
                arguments(GOLDEN_MASTER_DIRECTORY + DELETE_DIRECTORY + "delete_test1.txt", GOLDEN_MASTER_DIRECTORY + DELETE_DIRECTORY + "delete_test1.approved.txt"),
                arguments(GOLDEN_MASTER_DIRECTORY + DELETE_DIRECTORY + "delete_test2.txt", GOLDEN_MASTER_DIRECTORY + DELETE_DIRECTORY + "delete_test2.approved.txt"),
                arguments(GOLDEN_MASTER_DIRECTORY + DELETE_DIRECTORY + "delete_test3.txt", GOLDEN_MASTER_DIRECTORY + DELETE_DIRECTORY + "delete_test3.approved.txt"),
                arguments(GOLDEN_MASTER_DIRECTORY + DELETE_DIRECTORY + "delete_test4.txt", GOLDEN_MASTER_DIRECTORY + DELETE_DIRECTORY + "delete_test4.approved.txt"),
                arguments(GOLDEN_MASTER_DIRECTORY + LOGICAL_DIRECTORY + "logical_test1.txt", GOLDEN_MASTER_DIRECTORY + LOGICAL_DIRECTORY + "logical_test1.approved.txt"),
                arguments(GOLDEN_MASTER_DIRECTORY + LOGICAL_DIRECTORY + "logical_test2.txt", GOLDEN_MASTER_DIRECTORY + LOGICAL_DIRECTORY + "logical_test2.approved.txt"),
                arguments(GOLDEN_MASTER_DIRECTORY + LOGICAL_DIRECTORY + "logical_test3.txt", GOLDEN_MASTER_DIRECTORY + LOGICAL_DIRECTORY + "logical_test3.approved.txt"),
                arguments(GOLDEN_MASTER_DIRECTORY + LOGICAL_DIRECTORY + "logical_test4.txt", GOLDEN_MASTER_DIRECTORY + LOGICAL_DIRECTORY + "logical_test4.approved.txt"),
                arguments(GOLDEN_MASTER_DIRECTORY + LOGICAL_DIRECTORY + "logical_test5.txt", GOLDEN_MASTER_DIRECTORY + LOGICAL_DIRECTORY + "logical_test5.approved.txt")

        );
    }

    private String concatenateInputFiles(String initFile, String commandFile) throws IOException {
        String testFileName = "./src/test/java/com/sec/bestreviewer/goldenmaster/actual_test_command.txt";
        PrintWriter pw = new PrintWriter(testFileName);

        // BufferedReader object for file1.txt
        BufferedReader br = new BufferedReader(new FileReader(initFile));
        String line = br.readLine();

        // loop to copy each line of
        // file1.txt to  file3.txt
        while (line != null) {
            pw.println(line);
            line = br.readLine();
        }

        br = new BufferedReader(new FileReader(commandFile));

        line = br.readLine();

        // loop to copy each line of
        // file2.txt to  file3.txt
        while (line != null) {
            pw.println(line);
            line = br.readLine();
        }

        pw.flush();

        // closing resources
        br.close();
        pw.close();

//        System.out.println("Merged file1.txt and file2.txt into file3.txt");
        return testFileName;
    }

}
