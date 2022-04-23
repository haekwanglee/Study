package com.sec.bestreviewer;

import com.sec.bestreviewer.command.Command;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.util.ResultStringFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static com.sec.bestreviewer.CommandExecutor.MAX_RESULT_NUMBER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CommandExecutorTest {

    private EmployeeStore employeeStore;

    @BeforeEach
    void createMockEmployeeStore() {
        employeeStore = mock(EmployeeStore.class);
    }

    @Test
    void queryExecutorReturnsResultString() {
        final List<String> options = Collections.singletonList("-p");
        final List<String> params = Arrays.asList(" ", " ", "18050301", "KYUMOK KIM", "CL2", "010-9777-6055", "19980906", "PRO");
        final Command command = CommandFactory.buildCommand("ADD", options, params);
        final List<String> res = (new CommandExecutor()).execute(command);
        assertNotNull(res);
    }

    @Test
    void testAddCommandReturnsEmptyList() {
        final List<String> options = Collections.emptyList();
        final List<String> params = Arrays.asList(" ", " ", "18050301", "KYUMOK KIM", "CL2", "010-9777-6055", "19980906", "PRO");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_ADD, options, params);
        final List<String> res = (new CommandExecutor()).execute(command);
        assertNotNull(res);
        assertEquals(0, res.size());
    }

    @Test
    void testDeleteCommandWithPrintOption() {
        deleteCommandWithPrintOption(1);
        deleteCommandWithPrintOption(6);
    }

    private void deleteCommandWithPrintOption(int count) {
        final List<Employee> employeeList = getEmployees(count);
        when(employeeStore.delete(any())).thenReturn(employeeList);

        final List<String> options = Collections.singletonList("-p");
        final List<String> params = Arrays.asList(" ", " " , "name", "KYUMOK KIM");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_DEL, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);

        for (int i = 0; i < Math.min(count, MAX_RESULT_NUMBER); i++) {
            assertEquals(ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_DEL, employeeList.get(i)),
                    resList.get(i));
        }
    }

    private List<Employee> getEmployees(int count) {
        final List<Employee> employeeList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            final String employeeNumber = Integer.toString(90_000000 + (i * 10_000000));
            employeeList.add(
                    new Employee(employeeNumber, "SEO KFI", "CL1",
                            "010-1234-5678", "20190101", "PRO"));
        }
        return employeeList;
    }

    @Test
    void testDeleteCommandWithPrintOption_NoneResult() {
        final List<Employee> employeeList = getEmployees(0);
        when(employeeStore.delete(any())).thenReturn(employeeList);

        final List<String> options = Collections.singletonList("-p");
        final List<String> params = Arrays.asList(" ", " " , "name", "KYUMOK KIM");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_DEL, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(CommandFactory.CMD_DEL + ",NONE", resList.get(0));
    }

    @Test
    void testDeleteCommandWithOutPrintOption() {
        final int deletedCount = 10;
        final List<Employee> employeeList = getEmployees(deletedCount);
        when(employeeStore.delete(any())).thenReturn(employeeList);

        final List<String> options = Collections.emptyList();
        final List<String> params = Arrays.asList(" ", " " , "name", "KYUMOK KIM");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_DEL, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(CommandFactory.CMD_DEL + "," + deletedCount, resList.get(0));
    }

    @Test
    void testDeleteCommandWithOutPrintOption_NoneResult() {
        final List<Employee> employeeList = getEmployees(0);
        when(employeeStore.delete(any())).thenReturn(employeeList);

        final List<String> options = Collections.emptyList();
        final List<String> params = Arrays.asList(" ", " ", "name", "KYUMOK KIM");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_DEL, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(CommandFactory.CMD_DEL + ",NONE", resList.get(0));
    }

    @Test
    void testSearchCommandWithPrintOption() {
        searchCommandWithPrintOption(1);
        searchCommandWithPrintOption(6);
    }

    void searchCommandWithPrintOption(int count) {
        final List<Employee> employeeList = getEmployees(count);
        when(employeeStore.search(any())).thenReturn(employeeList);

        final List<String> options = Collections.singletonList("-p");
        final List<String> params = Arrays.asList(" ", " " , "name", "KYUMOK KIM");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        for (int i = 0; i < Math.min(count, MAX_RESULT_NUMBER); i++) {
            assertEquals(
                    ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_SCH, employeeList.get(i)),
                    resList.get(i));
        }
    }

    @Test
    void testSearchCommandWithPrintOption_NoneResult() {
        final List<Employee> employeeList = getEmployees(0);
        when(employeeStore.search(any())).thenReturn(employeeList);

        final List<String> options = Collections.singletonList("-p");
        final List<String> params = Arrays.asList(" ", " ", "name", "KYUMOK KIM");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(CommandFactory.CMD_SCH + ",NONE", resList.get(0));
    }

    @Test
    void testSearchCommandWithOutPrintOption() {
        final int deletedCount = 10;
        final List<Employee> employeeList = getEmployees(deletedCount);
        when(employeeStore.search(any())).thenReturn(employeeList);

        final List<String> options = Collections.emptyList();
        final List<String> params = Arrays.asList(" ", " ", "name", "KYUMOK KIM");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(CommandFactory.CMD_SCH + "," + deletedCount, resList.get(0));
    }

    @Test
    void testSearchCommandWithOutPrintOption_NoneResult() {
        final List<Employee> employeeList = getEmployees(0);
        when(employeeStore.search(any())).thenReturn(employeeList);

        final List<String> options = Collections.emptyList();
        final List<String> params = Arrays.asList(" ", " ", "name", "KYUMOK KIM");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(CommandFactory.CMD_SCH + ",NONE", resList.get(0));
    }

    @Test
    void testCountCommandShouldReturnCountNumberString() {
        when(employeeStore.count()).thenReturn(1);

        final List<String> options = Collections.emptyList();
        final List<String> params = Collections.emptyList();
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_CNT, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(CommandFactory.CMD_CNT + ",1", resList.get(0));
    }

    @Test
    void testDeleteCommandReturnsSortedEmployeeList() {
        final int deletedCount = 10;
        final List<Employee> employeeList = getEmployees(deletedCount);
        final List<Employee> reversedEmployeeList = employeeList.stream()
                .sorted(Comparator.comparing(Employee::getEmployeeNumber).reversed())
                .collect(Collectors.toList());
        when(employeeStore.delete(any())).thenReturn(reversedEmployeeList);

        final List<String> options = Collections.singletonList("-p");
        final List<String> params = Arrays.asList(" ", " ", "name", "KYUMOK KIM");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_DEL, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        for (int i = 0; i < Math.min(deletedCount, MAX_RESULT_NUMBER); i++) {
            assertEquals(
                    ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_DEL, employeeList.get(i)),
                    resList.get(i));
        }
    }

    @Test
    void testModifyCommandWithPrintOption_NoneResult() {
        final List<Employee> employeeList = getEmployees(0);
        when(employeeStore.modify(any(), any())).thenReturn(employeeList);

        final List<String> options = Collections.singletonList("-p");
        final List<String> params = Arrays.asList(" ", " ", "name", "KYUMOK KIM", "name", "KYUMOK LEE");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_MOD, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(CommandFactory.CMD_MOD + ",NONE", resList.get(0));
    }

    @Test
    void testModifyCommandWithOutPrintOption() {
        final int deletedCount = 10;
        final List<Employee> employeeList = getEmployees(deletedCount);
        when(employeeStore.modify(any(), any())).thenReturn(employeeList);

        final List<String> options = Collections.emptyList();
        final List<String> params = Arrays.asList(" ", " ", "name", "KYUMOK KIM", "name", "KYUMOK LEE");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_MOD, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(CommandFactory.CMD_MOD + "," + deletedCount, resList.get(0));
    }

    @Test
    void testModifyCommandWithOutPrintOption_NoneResult() {
        final List<Employee> employeeList = getEmployees(0);
        when(employeeStore.modify(any(), any())).thenReturn(employeeList);

        final List<String> options = Collections.emptyList();
        final List<String> params = Arrays.asList(" ", " ", "name", "KYUMOK KIM", "name", "KYUMOK LEE");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_MOD, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(CommandFactory.CMD_MOD + ",NONE", resList.get(0));
    }

    @Test
    void testDeleteCommandWithAndOption() {
        final List<Employee> employeeList = getEmployees(0);
        when(employeeStore.delete(any())).thenReturn(employeeList);

        final List<String> options = Collections.emptyList();
        final List<String> params = Arrays.asList(" ", " ", "name", "KYUMOK KIM", "-a", " ", " ", "cl", "CL4");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_DEL, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(CommandFactory.CMD_DEL + ",NONE", resList.get(0));
    }

    @Test
    void testDeleteCommandWithOrOption() {
        final List<Employee> employeeList = getEmployees(0);
        when(employeeStore.delete(any())).thenReturn(employeeList);

        final List<String> options = Collections.emptyList();
        final List<String> params = Arrays.asList(" ", " ", "name", "KYUMOK KIM", "-o", " ", " ", "cl", "CL4");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_DEL, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(CommandFactory.CMD_DEL + ",NONE", resList.get(0));
    }

    @Test
    void testSearchCommandWithAndOption() {
        final List<Employee> employeeList = getEmployees(0);
        when(employeeStore.search(any())).thenReturn(employeeList);

        final List<String> options = Collections.emptyList();
        final List<String> params = Arrays.asList(" ", " ", "name", "KYUMOK KIM", "-a", " ", " ", "cl", "CL4");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(CommandFactory.CMD_SCH + ",NONE", resList.get(0));
    }

    @Test
    void testSearchCommandWithOrOption() {
        final List<Employee> employeeList = getEmployees(0);
        when(employeeStore.search(any())).thenReturn(employeeList);

        final List<String> options = Collections.emptyList();
        final List<String> params = Arrays.asList(" ", " ", "name", "KYUMOK KIM", "-o", " ", " ", "cl", "CL4");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(CommandFactory.CMD_SCH + ",NONE", resList.get(0));
    }

    @Test
    void testModifyCommandWithAndOption() {
        final List<Employee> employeeList = getEmployees(0);
        when(employeeStore.modify(any(), any())).thenReturn(employeeList);

        final List<String> options = Collections.emptyList();
        final List<String> params = Arrays.asList(" ", " ", "name", "KYUMOK KIM", "-a", " ", " ", "cl", "CL4", "name", "KYMOK LEE");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_MOD, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(CommandFactory.CMD_MOD + ",NONE", resList.get(0));
    }

    @Test
    void testModifyCommandWithOrOption() {
        final List<Employee> employeeList = getEmployees(0);
        when(employeeStore.modify(any(), any())).thenReturn(employeeList);

        final List<String> options = Collections.emptyList();
        final List<String> params = Arrays.asList(" ", " ", "name", "KYUMOK KIM", "-o", " ", " ", "cl", "CL4", "name", "KYMOK LEE");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_MOD, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(CommandFactory.CMD_MOD + ",NONE", resList.get(0));
    }
}
