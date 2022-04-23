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

class SearchEmployeeNumberCommandTest {

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
    void testSearchEmployeeNumber() {
        Employee employee = new Employee("01000000", "DAFLKDJ KIM", "CL2", "010-1234-5678", "20190102", "EX");

        assertTrue(testCommand(employee, SearchOption.EMPLOYEE_NUMBER, InqualitySignOption.NONE, "employeeNumber", "01000000"));
        assertFalse(testCommand(employee, SearchOption.EMPLOYEE_NUMBER, InqualitySignOption.NONE, "employeeNumber", "02000000"));
    }

    @Test
    void testSearchEmployeeNumberGreater() {
        Employee employee = new Employee("01000000", "DAFLKDJ KIM", "CL2", "010-1234-5678", "20190102", "EX");

        assertFalse(testCommand(employee, SearchOption.EMPLOYEE_NUMBER, InqualitySignOption.GREATER_THAN, "employeeNumber", "01000000"));
        assertTrue(testCommand(employee, SearchOption.EMPLOYEE_NUMBER, InqualitySignOption.GREATER_THAN, "employeeNumber", "91000000"));
        assertFalse(testCommand(employee, SearchOption.EMPLOYEE_NUMBER, InqualitySignOption.GREATER_THAN, "employeeNumber", "11000000"));
    }


    @Test
    void testSearchEmployeeNumberGreaterEqual() {
        Employee employee = new Employee("01000000", "DAFLKDJ KIM", "CL2", "010-1234-5678", "20190102", "EX");

        assertTrue(testCommand(employee, SearchOption.EMPLOYEE_NUMBER, InqualitySignOption.GREATER_THAN_OR_EQUAL_TO, "employeeNumber", "01000000"));
        assertTrue(testCommand(employee, SearchOption.EMPLOYEE_NUMBER, InqualitySignOption.GREATER_THAN_OR_EQUAL_TO, "employeeNumber", "91000000"));
        assertFalse(testCommand(employee, SearchOption.EMPLOYEE_NUMBER, InqualitySignOption.GREATER_THAN_OR_EQUAL_TO, "employeeNumber", "11000000"));
    }


    @Test
    void testSearchEmployeeNumberSmaller() {
        Employee employee = new Employee("01000000", "DAFLKDJ KIM", "CL2", "010-1234-5678", "20190102", "EX");

        assertFalse(testCommand(employee, SearchOption.EMPLOYEE_NUMBER, InqualitySignOption.SMALLER_THAN, "employeeNumber", "01000000"));
        assertFalse(testCommand(employee, SearchOption.EMPLOYEE_NUMBER, InqualitySignOption.SMALLER_THAN, "employeeNumber", "91000000"));
        assertTrue(testCommand(employee, SearchOption.EMPLOYEE_NUMBER, InqualitySignOption.SMALLER_THAN, "employeeNumber", "11000000"));

    }

    @Test
    void testSearcEmployeeNumberSmallerEqual() {
        Employee employee = new Employee("01000000", "DAFLKDJ KIM", "CL2", "010-1234-5678", "20190102", "EX");

        assertTrue(testCommand(employee, SearchOption.EMPLOYEE_NUMBER, InqualitySignOption.SMALLER_THAN_OR_EQUAL_TO, "employeeNumber", "01000000"));
        assertFalse(testCommand(employee, SearchOption.EMPLOYEE_NUMBER, InqualitySignOption.SMALLER_THAN_OR_EQUAL_TO, "employeeNumber", "91000000"));
        assertTrue(testCommand(employee, SearchOption.EMPLOYEE_NUMBER, InqualitySignOption.SMALLER_THAN_OR_EQUAL_TO, "employeeNumber", "11000000"));
    }
}
