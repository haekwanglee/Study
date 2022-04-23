package com.sec.bestreviewer.command;

import com.sec.bestreviewer.CommandFactory;
import com.sec.bestreviewer.data.*;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.util.ResultStringFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static com.sec.bestreviewer.CommandExecutor.MAX_RESULT_NUMBER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeleteCommandTest {

    private EmployeeStore employeeStore;
    private static final Map<String, String> fieldMap = new HashMap<>();

    private static final String EMPLOYEE_NUMBER = "employeeNum";
    private static final String NAME = "name";
    private static final String CAREER_LEVEL = "cl";
    private static final String PHONE_NUMBER = "phoneNum";
    private static final String BIRTHDAY = "birthday";

    static {
        fieldMap.put(EMPLOYEE_NUMBER, EmployeeStore.FIELD_EMPLOYEE_NUMBER);
        fieldMap.put(NAME, EmployeeStore.FIELD_NAME);
        fieldMap.put(CAREER_LEVEL, EmployeeStore.FIELD_CAREER_LEVEL);
        fieldMap.put(PHONE_NUMBER, EmployeeStore.FIELD_PHONE_NUMBER);
        fieldMap.put(BIRTHDAY, EmployeeStore.FIELD_BIRTH_DAY);
    }

    private List<Employee> getEmployees(int count) {
        final List<Employee> employeeList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            final String employeeNumber = Integer.toString(90_000000 + (i * 10_000000));
            employeeList.add(
                    new Employee(employeeNumber, "SEO KFI", "CL1", "010-1234-5678", "20190101", "ADV"));
        }
        return employeeList;
    }

    private void deleteCommandWithPrintOption(int count) {
        final List<Employee> employeeList = getEmployees(count);
        when(employeeStore.delete(any())).thenReturn(employeeList);

        CommandData commandData = new CommandData();
        commandData.setSearchDataList(Collections.singletonList(
                new SearchData(SearchOption.FULL_NAME, InqualitySignOption.NONE, "name", "KYUMOK KIM")));
        commandData.setPrintOption(PrintOption.PRINT);
        Command deleteCommand = new DeleteCommand(commandData);
      
        final List<String> resList = deleteCommand.execute(employeeStore);

        for (int i = 0; i < Math.min(count, MAX_RESULT_NUMBER); i++) {
            assertEquals(ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_DEL, employeeList.get(i)),
                    resList.get(i));
        }
    }

    @BeforeEach
    void createMockEmployeeStore() {
        employeeStore = mock(EmployeeStore.class);
    }

    @Test
    void testDeleteCommandWithPrintOption() {
        deleteCommandWithPrintOption(1);
        deleteCommandWithPrintOption(6);
    }

    @Test
    void testDeleteCommandWithPrintOption_NoneResult() {
        final List<Employee> employeeList = getEmployees(0);
        when(employeeStore.delete(any())).thenReturn(employeeList);
      
        CommandData commandData = new CommandData();
        commandData.setSearchDataList(Collections.singletonList(
                new SearchData(SearchOption.FULL_NAME, InqualitySignOption.NONE, "name", "KYUMOK KIM")));
        commandData.setPrintOption(PrintOption.PRINT);
        Command deleteCommand = new DeleteCommand(commandData);
        final List<String> resList = deleteCommand.execute(employeeStore);

        assertEquals(CommandFactory.CMD_DEL + ",NONE", resList.get(0));
    }

    @Test
    void testDeleteCommandWithOutPrintOption() {
        final int deletedCount = 10;
        final List<Employee> employeeList = getEmployees(deletedCount);
        when(employeeStore.delete(any())).thenReturn(employeeList);

        CommandData commandData = new CommandData();
        commandData.setSearchDataList(Collections.singletonList(
                new SearchData(SearchOption.FULL_NAME, InqualitySignOption.NONE, "name", "KYUMOK KIM")));
        Command deleteCommand = new DeleteCommand(commandData);

        final List<String> resList = deleteCommand.execute(employeeStore);

        assertEquals(CommandFactory.CMD_DEL + "," + deletedCount, resList.get(0));
    }

    @Test
    void testDeleteCommandWithOutPrintOption_NoneResult() {
        final List<Employee> employeeList = getEmployees(0);
        when(employeeStore.delete(any())).thenReturn(employeeList);

        CommandData commandData = new CommandData();
        commandData.setSearchDataList(Collections.singletonList(
                new SearchData(SearchOption.FULL_NAME, InqualitySignOption.NONE, "name", "KYUMOK KIM")));
        Command deleteCommand = new DeleteCommand(commandData);

        final List<String> resList = deleteCommand.execute(employeeStore);

        assertEquals(CommandFactory.CMD_DEL + ",NONE", resList.get(0));
    }

    @Test
    void testDeleteCommandReturnsSortedEmployeeList() {
        final int deletedCount = 10;
        final List<Employee> employeeList = getEmployees(deletedCount);
        final List<Employee> reversedEmployeeList = employeeList.stream()
                .sorted(Comparator.comparing(Employee::getEmployeeNumber).reversed())
                .collect(Collectors.toList());
        when(employeeStore.delete(any())).thenReturn(reversedEmployeeList);

        CommandData commandData = new CommandData();
        commandData.setSearchDataList(Collections.singletonList(
                new SearchData(SearchOption.FULL_NAME, InqualitySignOption.NONE, "name", "KYUMOK KIM")));
        commandData.setPrintOption(PrintOption.PRINT);
        Command deleteCommand = new DeleteCommand(commandData);

        final List<String> resList = deleteCommand.execute(employeeStore);

        for (int i = 0; i < Math.min(deletedCount, MAX_RESULT_NUMBER); i++) {
            assertEquals(
                    ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_DEL, employeeList.get(i)),
                    resList.get(i));
        }
    }
}