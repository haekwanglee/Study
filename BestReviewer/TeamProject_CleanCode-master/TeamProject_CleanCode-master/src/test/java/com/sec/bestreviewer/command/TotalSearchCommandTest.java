package com.sec.bestreviewer.command;

import com.sec.bestreviewer.data.*;
import com.sec.bestreviewer.store.Employee;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

// TODO : TotalSearchCommand will be changed SearchCommand later.
class TotalSearchCommandTest {

    @Test
    void testConstructor() {
        CommandData commandData = new CommandData();
        SearchCommand command = new SearchCommand(commandData);
        assertNotNull(command);
    }

    @Test
    void testNameSearch() {
        SearchNameCommandTest test = new SearchNameCommandTest();
        test.testSearchFullName();
        test.testSearchFirstName();
        test.testSearchLastName();
    }

    @Test
    void testPhoneNumberSearch() {
        SearchPhoneNumberCommandTest test = new SearchPhoneNumberCommandTest();
        test.testSearchFullNumber();
        test.testSearchMiddleNumber();
        test.testSearchLastNumber();
    }

    @Test
    void testBirthdaySearch() {
        SearchBirthdayCommandTest test = new SearchBirthdayCommandTest();
        test.testSearchBirthdayDay();
        test.testSearchBirthdayYear();
        test.testSearchBirthdayMonth();
        test.testSearchBirthdayDay();
    }

    @Test
    void testAndSearchSearch_True() {
        Employee employee = new Employee("01000000", "DAFLKDJ KIM", "CL1", "010-1234-5678", "20190102", "EX");
        List<SearchData> searchDataList = new ArrayList<>();
        searchDataList.add(new SearchData(SearchOption.LAST_NAME, InqualitySignOption.NONE, "name", "KIM"));
        searchDataList.add(new SearchData(SearchOption.CL, InqualitySignOption.NONE, "cl", "CL1"));
        CommandData commandData = new CommandData();
        commandData.setSearchDataList(searchDataList);
        commandData.setAndOrOption(AndOrOption.AND);

        SearchCommand command = new SearchCommand(commandData);

        Predicate<Employee> predicate = command.createFilter();
        assertTrue(predicate.test(employee));
    }

    @Test
    void testAndSearchSearch_False() {
        Employee employee = new Employee("01000000", "DAFLKDJ KIM", "CL1", "010-1234-5678", "20190102", "EX");
        List<SearchData> searchDataList = new ArrayList<>();
        searchDataList.add(new SearchData(SearchOption.LAST_NAME, InqualitySignOption.NONE, "name", "KIM"));
        searchDataList.add(new SearchData(SearchOption.CL, InqualitySignOption.NONE, "cl", "CL3"));
        CommandData commandData = new CommandData();
        commandData.setSearchDataList(searchDataList);
        commandData.setAndOrOption(AndOrOption.AND);

        SearchCommand command = new SearchCommand(commandData);

        Predicate<Employee> predicate = command.createFilter();
        assertFalse(predicate.test(employee));
    }

    @Test
    void testOrSearchSearch_True_First() {
        Employee employee = new Employee("01000000", "DAFLKDJ KIM", "CL1", "010-1234-5678", "20190102", "EX");
        List<SearchData> searchDataList = new ArrayList<>();
        searchDataList.add(new SearchData(SearchOption.LAST_NAME, InqualitySignOption.NONE, "name", "KIM"));
        searchDataList.add(new SearchData(SearchOption.CL, InqualitySignOption.NONE, "cl", "CL2"));
        CommandData commandData = new CommandData();
        commandData.setSearchDataList(searchDataList);
        commandData.setAndOrOption(AndOrOption.OR);

        SearchCommand command = new SearchCommand(commandData);

        Predicate<Employee> predicate = command.createFilter();
        assertTrue(predicate.test(employee));
    }

    @Test
    void testOrSearchSearch_True_Second() {
        Employee employee = new Employee("01000000", "DAFLKDJ KIM", "CL1", "010-1234-5678", "20190102", "EX");
        List<SearchData> searchDataList = new ArrayList<>();
        searchDataList.add(new SearchData(SearchOption.LAST_NAME, InqualitySignOption.NONE, "name", "LEE"));
        searchDataList.add(new SearchData(SearchOption.CL, InqualitySignOption.NONE, "cl", "CL1"));
        CommandData commandData = new CommandData();
        commandData.setSearchDataList(searchDataList);
        commandData.setAndOrOption(AndOrOption.OR);

        SearchCommand command = new SearchCommand(commandData);

        Predicate<Employee> predicate = command.createFilter();
        assertTrue(predicate.test(employee));
    }

    @Test
    void testOrSearchSearch_False() {
        Employee employee = new Employee("01000000", "DAFLKDJ KIM", "CL1", "010-1234-5678", "20190102", "EX");
        List<SearchData> searchDataList = new ArrayList<>();
        searchDataList.add(new SearchData(SearchOption.LAST_NAME, InqualitySignOption.NONE, "name", "LEE"));
        searchDataList.add(new SearchData(SearchOption.CL, InqualitySignOption.NONE, "cl", "CL3"));
        CommandData commandData = new CommandData();
        commandData.setSearchDataList(searchDataList);
        commandData.setAndOrOption(AndOrOption.OR);

        SearchCommand command = new SearchCommand(commandData);

        Predicate<Employee> predicate = command.createFilter();
        assertFalse(predicate.test(employee));
    }
}