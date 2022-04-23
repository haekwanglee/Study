package com.sec.bestreviewer;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmployeeManagementTest {

    public static final String EMPTY_TXT = "./src/test/java/com/sec/bestreviewer/empty.txt";
    public static final String WRONG_COMMAND_INPUT_TXT = "./src/test/java/com/sec/bestreviewer/wrong_command_input.txt";
    public static final String WRONG_COMMAND_OUTPUT_TXT = "./src/test/java/com/sec/bestreviewer/wrong_command_output.txt";
    public static final String OUTPUT_TXT = "./src/test/java/com/sec/bestreviewer/output.txt";
    public static final String INTEGRATION_TEST_OUTPUT_TXT = "./src/test/java/com/sec/bestreviewer/integration_test_output.txt";
    public static final String INTEGRATION_TEST_INPUT_TXT = "./src/test/java/com/sec/bestreviewer/integration_test_input.txt";
    public static final String INTEGRATION_TEST_OUTPUT_V2_TXT = "./src/test/java/com/sec/bestreviewer/integration_test_output_v2.txt";
    public static final String INTEGRATION_TEST_INPUT_V2_TXT = "./src/test/java/com/sec/bestreviewer/integration_test_input_v2.txt";

    @Test
    @DisplayName("빈 파일 input")
    public void testArgumentsEmptyInputFile() {
        String[] args = {EMPTY_TXT, OUTPUT_TXT};
        new EmployeeManagement().run(args);

        assertTrue(new File(OUTPUT_TXT).exists());
    }

    @Test
    @DisplayName("잘못된 argument count")
    public void testArgumentsWrongArgsCount() {
        String[] args = {"input.txt"};
        EmployeeManagement employeeManagement = new EmployeeManagement();

        Assertions.assertThrows(IllegalArgumentException.class, () -> employeeManagement.run(args));
    }

    @Test
    @DisplayName("파일이 없는 경우")
    public void testArgumentsNotExistInputFile() {
        String[] args = {"notexist.txt", "output.txt"};
        EmployeeManagement employeeManagement = new EmployeeManagement();

        Assertions.assertThrows(IllegalArgumentException.class, () -> employeeManagement.run(args));
    }

    @Test
    @DisplayName("잘못된 Command")
    public void testIllegalArgumentOfWrongCommand() {
        String[] args = {WRONG_COMMAND_INPUT_TXT, WRONG_COMMAND_OUTPUT_TXT};
        File outputFile = new File(WRONG_COMMAND_OUTPUT_TXT);
        new EmployeeManagement().run(args);

        Approvals.verify(outputFile);
    }

    @Test
    @DisplayName("Golden Master 테스트 with run")
    public void integrationTest() {
        String[] args = {INTEGRATION_TEST_INPUT_TXT, INTEGRATION_TEST_OUTPUT_TXT};
        File outputFile = new File(INTEGRATION_TEST_OUTPUT_TXT);
        new EmployeeManagement().run(args);

        Approvals.verify(outputFile);
    }

    @Test
    @DisplayName("Golden Master 테스트 with main")
    public void integrationTestWithMain() {
        String[] args = {INTEGRATION_TEST_INPUT_TXT, INTEGRATION_TEST_OUTPUT_TXT};
        File outputFile = new File(INTEGRATION_TEST_OUTPUT_TXT);
        EmployeeManagement.main(args);

        Approvals.verify(outputFile);
    }

    @Test
    @DisplayName("Golden Master 테스트 - 추가 요구사항")
    public void integrationTestWithAdditionalRequirements() {
        String[] args = {INTEGRATION_TEST_INPUT_V2_TXT, INTEGRATION_TEST_OUTPUT_V2_TXT};
        File outputFile = new File(INTEGRATION_TEST_OUTPUT_V2_TXT);
        new EmployeeManagement().run(args);

        Approvals.verify(outputFile);
    }
}
