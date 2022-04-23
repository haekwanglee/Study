package com.sec.bestreviewer.command;

import com.sec.bestreviewer.data.CommandData;
import com.sec.bestreviewer.data.InqualitySignOption;
import com.sec.bestreviewer.data.SearchData;
import com.sec.bestreviewer.data.SearchOption;
import com.sec.bestreviewer.store.Employee;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SearchCLCommandTest {

    private boolean testCommand(Employee employee,
                                SearchOption searchOption,
                                InqualitySignOption inqualitySignOption,
                                String columnName,
                                String value) {
        List<SearchData> searchDataList = new ArrayList<>();
        searchDataList.add(new SearchData(searchOption, inqualitySignOption, columnName, value));
        CommandData commandData = new CommandData();
        commandData.setSearchDataList(searchDataList);
        SearchCommand command = new SearchCommand(commandData);
        Predicate<Employee> predicate = command.createFilter();
        return predicate.test(employee);
    }

    @Test
    void testSearchCL() {
        Employee employee = new Employee("01000000", "DAFLKDJ KIM", "CL2", "010-1234-5678", "20190102", "EX");

        assertTrue(testCommand(employee, SearchOption.CL, InqualitySignOption.NONE, "careerLevel", "CL2"));
        assertFalse(testCommand(employee, SearchOption.CL, InqualitySignOption.NONE, "careerLevel", "CL3"));
    }

    @Test
    void testSearchCLGreater() {
        Employee employee = new Employee("01000000", "DAFLKDJ KIM", "CL2", "010-1234-5678", "20190102", "EX");

        assertTrue(testCommand(employee, SearchOption.CL, InqualitySignOption.GREATER_THAN, "careerLevel", "CL1"));
        assertFalse(testCommand(employee, SearchOption.CL, InqualitySignOption.GREATER_THAN, "careerLevel", "CL2"));
        assertFalse(testCommand(employee, SearchOption.CL, InqualitySignOption.GREATER_THAN, "careerLevel", "CL3"));
    }


    @Test
    void testSearchCLGreaterEqual() {
        Employee employee = new Employee("01000000", "DAFLKDJ KIM", "CL2", "010-1234-5678", "20190102", "EX");

        assertTrue(testCommand(employee, SearchOption.CL, InqualitySignOption.GREATER_THAN_OR_EQUAL_TO, "careerLevel", "CL1"));
        assertTrue(testCommand(employee, SearchOption.CL, InqualitySignOption.GREATER_THAN_OR_EQUAL_TO, "careerLevel", "CL2"));
        assertFalse(testCommand(employee, SearchOption.CL, InqualitySignOption.GREATER_THAN_OR_EQUAL_TO, "careerLevel", "CL3"));
    }


    @Test
    void testSearchCLSmaller() {
        Employee employee = new Employee("01000000", "DAFLKDJ KIM", "CL2", "010-1234-5678", "20190102", "EX");

        assertFalse(testCommand(employee, SearchOption.CL, InqualitySignOption.SMALLER_THAN, "careerLevel", "CL1"));
        assertFalse(testCommand(employee, SearchOption.CL, InqualitySignOption.SMALLER_THAN, "careerLevel", "CL2"));
        assertTrue(testCommand(employee, SearchOption.CL, InqualitySignOption.SMALLER_THAN, "careerLevel", "CL3"));

    }

    @Test
    void testSearcCLSmallerEqual() {
        Employee employee = new Employee("01000000", "DAFLKDJ KIM", "CL2", "010-1234-5678", "20190102", "EX");

        assertFalse(testCommand(employee, SearchOption.CL, InqualitySignOption.SMALLER_THAN_OR_EQUAL_TO, "careerLevel", "CL1"));
        assertTrue(testCommand(employee, SearchOption.CL, InqualitySignOption.SMALLER_THAN_OR_EQUAL_TO, "careerLevel", "CL2"));
        assertTrue(testCommand(employee, SearchOption.CL, InqualitySignOption.SMALLER_THAN_OR_EQUAL_TO, "careerLevel", "CL3"));
    }
}
