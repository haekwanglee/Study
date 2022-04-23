package com.sec.bestreviewer.command;

import com.sec.bestreviewer.data.*;
import com.sec.bestreviewer.store.Employee;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class SearchNameCommandTest {

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
    void testSearchFullName() {
        Employee employee = new Employee("01000000", "DAFLKDJ KIM", "CL1", "010-1234-5678", "20190102", "EX");

        assertTrue(testCommand(employee, SearchOption.FULL_NAME, InqualitySignOption.NONE, "name", "DAFLKDJ KIM"));
        assertFalse(testCommand(employee, SearchOption.FULL_NAME, InqualitySignOption.NONE, "name", "DAFLKDJ"));
    }

    @Test
    void testSearchFirstName() {
        Employee employee = new Employee("01000000", "DAFLKDJ KIM", "CL1", "010-1234-5678", "20190102", "EX");

        assertFalse(testCommand(employee, SearchOption.FIRST_NAME, InqualitySignOption.NONE, "name", "DAFLKDJ KIM"));
        assertTrue(testCommand(employee, SearchOption.FIRST_NAME, InqualitySignOption.NONE, "name", "DAFLKDJ"));
    }

    @Test
    void testSearchLastName() {
        Employee employee = new Employee("01000000", "DAFLKDJ KIM", "CL1", "010-1234-5678", "20190102", "EX");

        assertFalse(testCommand(employee, SearchOption.LAST_NAME, InqualitySignOption.NONE, "name", "DAFLKDJ KIM"));
        assertTrue(testCommand(employee, SearchOption.LAST_NAME, InqualitySignOption.NONE, "name", "KIM"));
    }

    @Test
    void testSearchFullNameGreater() {
        Employee employee = new Employee("01000000", "DAFLKDJ KIM", "CL1", "010-1234-5678", "20190102", "EX");

        assertFalse(testCommand(employee, SearchOption.FULL_NAME, InqualitySignOption.GREATER_THAN, "name", "DAFLKDJ KIM"));
        assertTrue(testCommand(employee, SearchOption.FULL_NAME, InqualitySignOption.GREATER_THAN, "name", "AAFLKDJ"));
        assertFalse(testCommand(employee, SearchOption.FULL_NAME, InqualitySignOption.GREATER_THAN, "name", "KIM"));
    }

    @Test
    void testSearchFirstNameGreater() {
        Employee employee = new Employee("01000000", "DAFLKDJ KIM", "CL1", "010-1234-5678", "20190102", "EX");

        assertFalse(testCommand(employee, SearchOption.FIRST_NAME, InqualitySignOption.GREATER_THAN, "name", "DAFLKDJ"));
        assertTrue(testCommand(employee, SearchOption.FIRST_NAME, InqualitySignOption.GREATER_THAN, "name", "AAFLKDJ"));
        assertFalse(testCommand(employee, SearchOption.FIRST_NAME, InqualitySignOption.GREATER_THAN, "name", "KIM"));
    }

    @Test
    void testSearchLastNameGreater() {
        Employee employee = new Employee("01000000", "DAFLKDJ KIM", "CL1", "010-1234-5678", "20190102", "EX");

        assertFalse(testCommand(employee, SearchOption.LAST_NAME, InqualitySignOption.GREATER_THAN, "name", "KIM"));
        assertTrue(testCommand(employee, SearchOption.LAST_NAME, InqualitySignOption.GREATER_THAN, "name", "AAFLKDJ"));
        assertFalse(testCommand(employee, SearchOption.LAST_NAME, InqualitySignOption.GREATER_THAN, "name", "XIM"));

    }

    @Test
    void testSearchFullNameGreaterEqual() {
        Employee employee = new Employee("01000000", "DAFLKDJ KIM", "CL1", "010-1234-5678", "20190102", "EX");

        assertTrue(testCommand(employee, SearchOption.FULL_NAME, InqualitySignOption.GREATER_THAN_OR_EQUAL_TO, "name", "DAFLKDJ KIM"));
        assertTrue(testCommand(employee, SearchOption.FULL_NAME, InqualitySignOption.GREATER_THAN_OR_EQUAL_TO, "name", "AAFLKDJ"));
        assertFalse(testCommand(employee, SearchOption.FULL_NAME, InqualitySignOption.GREATER_THAN_OR_EQUAL_TO, "name", "KIM"));
    }

    @Test
    void testSearchFirstNameGreaterEqual() {
        Employee employee = new Employee("01000000", "DAFLKDJ KIM", "CL1", "010-1234-5678", "20190102", "EX");

        assertTrue(testCommand(employee, SearchOption.FIRST_NAME, InqualitySignOption.GREATER_THAN_OR_EQUAL_TO, "name", "DAFLKDJ"));
        assertTrue(testCommand(employee, SearchOption.FIRST_NAME, InqualitySignOption.GREATER_THAN_OR_EQUAL_TO, "name", "AAFLKDJ"));
        assertFalse(testCommand(employee, SearchOption.FIRST_NAME, InqualitySignOption.GREATER_THAN_OR_EQUAL_TO, "name", "KIM"));
    }

    @Test
    void testSearchLastNameGreaterEqual() {
        Employee employee = new Employee("01000000", "DAFLKDJ KIM", "CL1", "010-1234-5678", "20190102", "EX");

        assertTrue(testCommand(employee, SearchOption.LAST_NAME, InqualitySignOption.GREATER_THAN_OR_EQUAL_TO, "name", "KIM"));
        assertTrue(testCommand(employee, SearchOption.LAST_NAME, InqualitySignOption.GREATER_THAN_OR_EQUAL_TO, "name", "DAFLKDJ"));
        assertFalse(testCommand(employee, SearchOption.LAST_NAME, InqualitySignOption.GREATER_THAN_OR_EQUAL_TO, "name", "XIM"));
    }

    @Test
    void testSearchFullNameSmaller() {
        Employee employee = new Employee("01000000", "DAFLKDJ KIM", "CL1", "010-1234-5678", "20190102", "EX");

        assertFalse(testCommand(employee, SearchOption.FULL_NAME, InqualitySignOption.SMALLER_THAN, "name", "DAFLKDJ KIM"));
        assertFalse(testCommand(employee, SearchOption.FULL_NAME, InqualitySignOption.SMALLER_THAN, "name", "AAFLKDJ"));
        assertTrue(testCommand(employee, SearchOption.FULL_NAME, InqualitySignOption.SMALLER_THAN, "name", "KIM"));

    }

    @Test
    void testSearchFirstNameSmaller() {
        Employee employee = new Employee("01000000", "DAFLKDJ KIM", "CL1", "010-1234-5678", "20190102", "EX");

        assertFalse(testCommand(employee, SearchOption.FIRST_NAME, InqualitySignOption.SMALLER_THAN, "name", "DAFLKDJ"));
        assertFalse(testCommand(employee, SearchOption.FIRST_NAME, InqualitySignOption.SMALLER_THAN, "name", "AAFLKDJ"));
        assertTrue(testCommand(employee, SearchOption.FIRST_NAME, InqualitySignOption.SMALLER_THAN, "name", "KIM"));
    }

    @Test
    void testSearchLastNameSmaller() {
        Employee employee = new Employee("01000000", "DAFLKDJ KIM", "CL1", "010-1234-5678", "20190102", "EX");

        assertFalse(testCommand(employee, SearchOption.LAST_NAME, InqualitySignOption.SMALLER_THAN, "name", "KIM"));
        assertFalse(testCommand(employee, SearchOption.LAST_NAME, InqualitySignOption.SMALLER_THAN, "name", "DAFLKDJ"));
        assertTrue(testCommand(employee, SearchOption.LAST_NAME, InqualitySignOption.SMALLER_THAN, "name", "XIM"));
    }

    @Test
    void testSearchFullNameSmallerEqual() {
        Employee employee = new Employee("01000000", "DAFLKDJ KIM", "CL1", "010-1234-5678", "20190102", "EX");

        assertTrue(testCommand(employee, SearchOption.FULL_NAME, InqualitySignOption.SMALLER_THAN_OR_EQUAL_TO, "name", "DAFLKDJ KIM"));
        assertFalse(testCommand(employee, SearchOption.FULL_NAME, InqualitySignOption.SMALLER_THAN_OR_EQUAL_TO, "name", "AAFLKDJ"));
        assertTrue(testCommand(employee, SearchOption.FULL_NAME, InqualitySignOption.SMALLER_THAN_OR_EQUAL_TO, "name", "KIM"));
    }

    @Test
    void testSearchFirstNameSmallerEqual() {
        Employee employee = new Employee("01000000", "DAFLKDJ KIM", "CL1", "010-1234-5678", "20190102", "EX");

        assertTrue(testCommand(employee, SearchOption.FIRST_NAME, InqualitySignOption.SMALLER_THAN_OR_EQUAL_TO, "name", "DAFLKDJ"));
        assertFalse(testCommand(employee, SearchOption.FIRST_NAME, InqualitySignOption.SMALLER_THAN_OR_EQUAL_TO, "name", "AAFLKDJ"));
        assertTrue(testCommand(employee, SearchOption.FIRST_NAME, InqualitySignOption.SMALLER_THAN_OR_EQUAL_TO, "name", "KIM"));
    }

    @Test
    void testSearchLastNameSmallerEqual() {
        Employee employee = new Employee("01000000", "DAFLKDJ KIM", "CL1", "010-1234-5678", "20190102", "EX");

        assertTrue(testCommand(employee, SearchOption.LAST_NAME, InqualitySignOption.SMALLER_THAN_OR_EQUAL_TO, "name", "KIM"));
        assertFalse(testCommand(employee, SearchOption.LAST_NAME, InqualitySignOption.SMALLER_THAN_OR_EQUAL_TO, "name", "DAFLKDJ"));
        assertTrue(testCommand(employee, SearchOption.LAST_NAME, InqualitySignOption.SMALLER_THAN_OR_EQUAL_TO, "name", "XIM"));
    }
}
