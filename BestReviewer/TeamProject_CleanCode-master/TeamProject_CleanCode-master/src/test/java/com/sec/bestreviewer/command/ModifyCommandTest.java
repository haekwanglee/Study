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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ModifyCommandTest {

    private EmployeeStore employeeStore;
    private static final Map<String, String> fieldMap = new HashMap<>();

    private static final String EMPLOYEE_NUMBER = "employeeNum";
    private static final String NAME = "name";
    private static final String CAREER_LEVEL = "cl";
    private static final String PHONE_NUMBER = "phoneNum";
    private static final String BIRTHDAY = "birthday";
    private static final String CERTI = "certi";

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
                    new Employee(employeeNumber, "KYUMOK KIM", "CL1", "010-1234-5678", "20190101","PRO"));
        }
        return employeeList;
    }

    private void modifyCommandWithPrintOption(int count) {
        final List<Employee> employeeList = getEmployees(count);
        when(employeeStore.modify(any(), eq("name"), eq("KYUMOK LEE"))).thenReturn(employeeList);

        CommandData commandData = new CommandData();
        commandData.setSearchDataList(Collections.singletonList(
                new SearchData(SearchOption.FULL_NAME, InqualitySignOption.NONE, "name", "KYUMOK KIM")));
        commandData.setModifyData(new ModifyData("name", "KYUMOK LEE"));
        commandData.setPrintOption(PrintOption.PRINT);
        ModifyCommand modifyCommand = new ModifyCommand(commandData);

        final List<String> resList = modifyCommand.execute(employeeStore);

        for (int i = 0; i < Math.min(count, MAX_RESULT_NUMBER); i++) {
            assertEquals(ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_MOD, employeeList.get(i)),
                    resList.get(i));
        }
    }

    @BeforeEach
    void createMockEmployeeStore() {
        employeeStore = mock(EmployeeStore.class);
    }

    @Test
    void testModifyCommandWithPrintOption() {
        modifyCommandWithPrintOption(1);
        modifyCommandWithPrintOption(6);
    }

    @Test
    void testModifyCommandWithPrintOption_NoneResult() {
        final List<Employee> employeeList = getEmployees(0);
        when(employeeStore.modify(any(), eq("name"), eq("KYUMOK LEE"))).thenReturn(employeeList);

        CommandData commandData = new CommandData();
        commandData.setSearchDataList(Collections.singletonList(
                new SearchData(SearchOption.FULL_NAME, InqualitySignOption.NONE, "name", "KYUMOK KIM")));
        commandData.setModifyData(new ModifyData("name", "KYUMOK LEE"));
        commandData.setPrintOption(PrintOption.PRINT);
        ModifyCommand modifyCommand = new ModifyCommand(commandData);

        final List<String> resList = modifyCommand.execute(employeeStore);

        assertEquals(CommandFactory.CMD_MOD + ",NONE", resList.get(0));
    }

    @Test
    void testModifyCommandWithOutPrintOption() {
        final int modifyCount = 10;
        final List<Employee> employeeList = getEmployees(modifyCount);
        when(employeeStore.modify(any(), eq("name"), eq("KYUMOK LEE"))).thenReturn(employeeList);

        CommandData commandData = new CommandData();
        commandData.setSearchDataList(Collections.singletonList(
                new SearchData(SearchOption.FULL_NAME, InqualitySignOption.NONE, "name", "KYUMOK KIM")));
        commandData.setModifyData(new ModifyData("name", "KYUMOK LEE"));
        ModifyCommand modifyCommand = new ModifyCommand(commandData);

        final List<String> resList = modifyCommand.execute(employeeStore);

        assertEquals(CommandFactory.CMD_MOD + "," + modifyCount, resList.get(0));
    }

    @Test
    void testModifyCommandWithOutPrintOption_NoneResult() {
        final List<Employee> employeeList = getEmployees(0);
        when(employeeStore.modify(any(), eq("name"), eq("KYUMOK LEE"))).thenReturn(employeeList);

        CommandData commandData = new CommandData();
        commandData.setSearchDataList(Collections.singletonList(
                new SearchData(SearchOption.FULL_NAME, InqualitySignOption.NONE, "name", "KYUMOK KIM")));
        commandData.setModifyData(new ModifyData("name", "KYUMOK LEE"));
        ModifyCommand modifyCommand = new ModifyCommand(commandData);

        final List<String> resList = modifyCommand.execute(employeeStore);

        assertEquals(CommandFactory.CMD_MOD + ",NONE", resList.get(0));
    }

    @Test
    void testModifyCommandReturnsSortedEmployeeList() {
        final int modifyCount = 10;
        final List<Employee> employeeList = getEmployees(modifyCount);
        final List<Employee> reversedEmployeeList = employeeList.stream()
                .sorted(Comparator.comparing(Employee::getEmployeeNumber).reversed())
                .collect(Collectors.toList());
        when(employeeStore.modify(any(), eq("name"), eq("KYUMOK LEE"))).thenReturn(reversedEmployeeList);

        CommandData commandData = new CommandData();
        commandData.setSearchDataList(Collections.singletonList(
                new SearchData(SearchOption.FULL_NAME, InqualitySignOption.NONE, "name", "KYUMOK KIM")));
        commandData.setModifyData(new ModifyData("name", "KYUMOK LEE"));
        commandData.setPrintOption(PrintOption.PRINT);
        ModifyCommand modifyCommand = new ModifyCommand(commandData);

        final List<String> resList = modifyCommand.execute(employeeStore);

        for (int i = 0; i < Math.min(modifyCount, MAX_RESULT_NUMBER); i++) {
            assertEquals(
                    ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_MOD, employeeList.get(i)),
                    resList.get(i));
        }
    }

    @Test
    void testModifyCommandWithCertiModifyPrintOption_NoneResult() {
        final List<Employee> employeeList = getEmployees(0);
        when(employeeStore.modify(any(),  eq("name"), eq("KYUMOK LEE"))).thenReturn(employeeList);

        CommandData commandData = new CommandData();
        commandData.setSearchDataList(Collections.singletonList(
                new SearchData(SearchOption.FULL_NAME, InqualitySignOption.NONE, "name", "KYUMOK KIM")));
        commandData.setModifyData(new ModifyData("certi", "PRO"));
        commandData.setPrintOption(PrintOption.PRINT);
        ModifyCommand modifyCommand = new ModifyCommand(commandData);

        final List<String> resList = modifyCommand.execute(employeeStore);

        assertEquals(CommandFactory.CMD_MOD + ",NONE", resList.get(0));
    }
}