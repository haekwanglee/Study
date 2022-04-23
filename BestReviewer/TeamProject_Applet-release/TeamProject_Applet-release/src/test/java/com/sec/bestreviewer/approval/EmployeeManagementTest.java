package com.sec.bestreviewer.approval;

import com.sec.bestreviewer.EmployeeManagement;
import com.sec.bestreviewer.Printer;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmployeeManagementTest {
    private static final String PATH = "./src/test/java/com/sec/bestreviewer/approval/";

    @Test
    public void testArgumentsEmptyInputFile() throws Exception {
        String[] args = {PATH + "empty.txt", PATH + "output.txt"};

        EmployeeManagement employeeManagement = new EmployeeManagement();
        employeeManagement.run(args);

        File file = new File(PATH + "output.txt");

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
    @DisplayName("10000 개 add, 1개 search - without print option")
    public void integrationBigDataTest() throws Exception {
        final String inputFileName = PATH + "integrationBigDataTest_input.txt";
        final String outputFileName = PATH + "integrationBigDataTest_output.txt";

        String[] args = {inputFileName, outputFileName};
        File outputFile = new File(outputFileName);

        long start = System.currentTimeMillis();

        EmployeeManagement employeeManagement = new EmployeeManagement();
        employeeManagement.run(args);

        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println("time : " + time / 1000.0 + " s");

        Approvals.verify(outputFile);
    }

    @Test
    @DisplayName("10 개 add, search 2개(sort) - with print option")
    public void testSearchCommandWithPrintOption() throws Exception {
        final String inputFileName = PATH + "searchCommandWithPrintOption_input.txt";
        final String outputFileName = PATH + "searchCommandWithPrintOption_output.txt";

        String[] args = {inputFileName, outputFileName};
        File outputFile = new File(outputFileName);

        EmployeeManagement employeeManagement = new EmployeeManagement();
        employeeManagement.run(args);

        Approvals.verify(outputFile);
    }

    @Test
    @DisplayName("executeCommand의 IllegalStateException 확인")
    public void testExecutionCommandWithPrintWrongMessage() {
        EmployeeManagement management = new EmployeeManagement();
        String line = "INV,99004327,KYUMOK KIM,CL1,010-1234-5758,19800321";
        management.executeEach(line);
        Assertions.assertTrue(management.peekCommandOutput().contains(Printer.ERR_MSG_WRONG_COMMAND));
    }

    @Test
    @DisplayName("printOutput의 Exception 확인")
    public void testPrintOuputWithExceptionFromRead() {
        EmployeeManagement management = new EmployeeManagement();
        management.printOutFile("src/test/NotExistingFileName");
        Assertions.assertTrue(management.peekCommandOutput().contains(Printer.FILE_READ_ERROR));

    }

    @Test
    @DisplayName("search 10,000개 역순")
    public void testSearchReverseOrder() throws Exception {
        final String inputFileName = PATH + "EmployeeManagementTest.testSearchReverseOrder_input.txt";
        final String outputFileName = PATH + "EmployeeManagementTest.testSearchReverseOrder_output.txt";

        String[] args = {inputFileName, outputFileName};
        File outputFile = new File(outputFileName);

        long start = System.currentTimeMillis();

        EmployeeManagement employeeManagement = new EmployeeManagement();
        employeeManagement.run(args);

        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println("time : " + time / 1000.0 + " s");

        Approvals.verify(outputFile);
    }
}
