package com.sec.bestreviewer.command;

import com.sec.bestreviewer.CommandFactory;
import com.sec.bestreviewer.data.*;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.util.ResultStringFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sec.bestreviewer.CommandExecutor.MAX_RESULT_NUMBER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SearchCommandTest {

    private EmployeeStore employeeStore;

    private static final String EMPLOYEE_NUMBER = "employeeNum";
    private static final String NAME = "name";
    private static final String CAREER_LEVEL = "cl";
    private static final String PHONE_NUMBER = "phoneNum";
    private static final String BIRTHDAY = "birthday";
    private static final String CERTI = "certi";

    private static final Map<String, String> fieldMap = new HashMap<>();

    static {
        fieldMap.put(EMPLOYEE_NUMBER, EmployeeStore.FIELD_EMPLOYEE_NUMBER);
        fieldMap.put(NAME, EmployeeStore.FIELD_NAME);
        fieldMap.put(CAREER_LEVEL, EmployeeStore.FIELD_CAREER_LEVEL);
        fieldMap.put(PHONE_NUMBER, EmployeeStore.FIELD_PHONE_NUMBER);
        fieldMap.put(BIRTHDAY, EmployeeStore.FIELD_BIRTH_DAY);
        fieldMap.put(CERTI, EmployeeStore.FIELD_CERTI);
    }

    private List<Employee> getEmployees(int count) {
        final List<Employee> employeeList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            final String employeeNumber = Integer.toString(90_000000 + (i * 10_000000));
            employeeList.add(
                    new Employee(employeeNumber, "SEO KFI", "CL1", "010-1234-5678", "20190101", "PRO"));
        }
        return employeeList;
    }

    void searchCommandWithPrintOption(int count) {
        final List<Employee> employeeList = getEmployees(count);
        when(employeeStore.search(any())).thenReturn(employeeList);

        List<SearchData> searchDataList = new ArrayList<>();
        searchDataList.add(new SearchData(SearchOption.FULL_NAME, InqualitySignOption.NONE, "name", "KYUMOK KIM"));
        CommandData commandData = new CommandData();
        commandData.setType(CommandType.SCH);
        commandData.setSearchDataList(searchDataList);
        commandData.setPrintOption(PrintOption.PRINT);

        final SearchCommand searchCommand = new SearchCommand(commandData);
        final List<String> resList = searchCommand.execute(employeeStore);
        for (int i = 0; i < Math.min(count, MAX_RESULT_NUMBER); i++) {
            assertEquals(
                    ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_SCH, employeeList.get(i)),
                    resList.get(i));
        }
    }

    @BeforeEach
    void createMockEmployeeStore() {
        employeeStore = mock(EmployeeStore.class);
    }

    @Test
    void testSearchCommandWithPrintOption() {
        searchCommandWithPrintOption(1);
        searchCommandWithPrintOption(6);
    }

    @Test
    void testSearchCommandWithPrintOption_NoneResult() {
        final List<Employee> employeeList = getEmployees(0);
        when(employeeStore.search(any())).thenReturn(employeeList);

        List<SearchData> searchDataList = new ArrayList<>();
        searchDataList.add(new SearchData(SearchOption.FULL_NAME, InqualitySignOption.NONE, "name", "KYUMOK KIM"));
        CommandData commandData = new CommandData();
        commandData.setType(CommandType.SCH);
        commandData.setSearchDataList(searchDataList);
        commandData.setPrintOption(PrintOption.PRINT);

        final SearchCommand searchCommand = new SearchCommand(commandData);
        final List<String> resList = searchCommand.execute(employeeStore);
        assertEquals(CommandFactory.CMD_SCH + ",NONE", resList.get(0));
    }

    @Test
    void testSearchCommandWithOutPrintOption() {
        final int searchCount = 10;
        final List<Employee> employeeList = getEmployees(searchCount);
        when(employeeStore.search(any())).thenReturn(employeeList);

        List<SearchData> searchDataList = new ArrayList<>();
        searchDataList.add(new SearchData(SearchOption.FULL_NAME, InqualitySignOption.NONE, "name", "KYUMOK KIM"));
        CommandData commandData = new CommandData();
        commandData.setType(CommandType.SCH);
        commandData.setSearchDataList(searchDataList);
        commandData.setPrintOption(PrintOption.NONE);

        final SearchCommand searchCommand = new SearchCommand(commandData);
        final List<String> resList = searchCommand.execute(employeeStore);
        assertEquals(CommandFactory.CMD_SCH + "," + searchCount, resList.get(0));
    }

    @Test
    void testSearchCommandWithOutPrintOption_NoneResult() {
        final List<Employee> employeeList = getEmployees(0);
        when(employeeStore.search(any())).thenReturn(employeeList);

        List<SearchData> searchDataList = new ArrayList<>();
        searchDataList.add(new SearchData(SearchOption.FULL_NAME, InqualitySignOption.NONE, "name", "KYUMOK KIM"));
        CommandData commandData = new CommandData();
        commandData.setType(CommandType.SCH);
        commandData.setSearchDataList(searchDataList);
        commandData.setPrintOption(PrintOption.NONE);

        final SearchCommand searchCommand = new SearchCommand(commandData);
        final List<String> resList = searchCommand.execute(employeeStore);
        assertEquals(CommandFactory.CMD_SCH + ",NONE", resList.get(0));
    }

    @Test
    void testSearchCommandWithCertiWithOutPrintOption() {
        final int searchCount = 10;
        final List<Employee> employeeList = getEmployees(searchCount);
        when(employeeStore.search(any())).thenReturn(employeeList);

        List<SearchData> searchDataList = new ArrayList<>();
        searchDataList.add(new SearchData(SearchOption.FULL_NAME, InqualitySignOption.NONE, "certi", "PRO"));
        CommandData commandData = new CommandData();
        commandData.setType(CommandType.SCH);
        commandData.setSearchDataList(searchDataList);
        commandData.setPrintOption(PrintOption.NONE);

        final SearchCommand searchCommand = new SearchCommand(commandData);
        final List<String> resList = searchCommand.execute(employeeStore);
        assertEquals(CommandFactory.CMD_SCH + "," + searchCount, resList.get(0));
    }
}