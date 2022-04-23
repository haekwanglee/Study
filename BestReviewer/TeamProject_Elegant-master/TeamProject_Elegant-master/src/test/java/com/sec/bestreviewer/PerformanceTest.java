package com.sec.bestreviewer;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.io.File;

public class PerformanceTest {

    @Test
    public void testSearchAndOr() {
        final String inputFileName = "./src/test/java/com/sec/bestreviewer/performance_test_input.txt";
        final String outputFileName = "./src/test/java/com/sec/bestreviewer/performance_test_output.txt";

        String[] args = {inputFileName, outputFileName};
        File outputFile = new File(outputFileName);

        EmployeeManagement employeeManagement = new EmployeeManagement();

        employeeManagement.run(args);

        Approvals.verify(outputFile);
    }

}
