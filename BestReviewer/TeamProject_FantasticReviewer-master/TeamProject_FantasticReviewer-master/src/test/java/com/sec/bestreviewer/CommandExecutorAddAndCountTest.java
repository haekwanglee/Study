package com.sec.bestreviewer;

import com.sec.bestreviewer.command.Command;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.store.Injection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CommandExecutorAddAndCountTest {

    private EmployeeStore employeeStore;
    private List<Employee> employeeList;

    @BeforeEach
    void createMockEmployeeStore() {
        employeeStore = Injection.provideEmployeeStore();
        TestUtil testUtil = new TestUtil();
        employeeList = testUtil.createTestEmployeeList(TestUtil.EMPLOYEE_LIST);
    }

    @Test
    void testAddCommandReturnsResultString() {
        final List<String> resList = executeAddCommand(employeeList.get(0));
        assertNotNull(resList);
    }

    @Test
    void testAddCommandReturnsEmptyList() {
        final List<String> resList = executeAddCommand(employeeList.get(0));
        assertNotNull(resList);
        assertEquals(0, resList.size());
    }

    private List<String> executeAddCommand(Employee employee) {
        TokenGroup tokenGroup = new TokenGroup(CommandFactory.CMD_ADD, employee);
        final Command command = CommandFactory.buildCommand(tokenGroup);
        return (new CommandExecutor(employeeStore)).execute(command);
    }

    @Test
    void testCountCommandShouldReturnOneCount() {
        executeAddCommand(employeeList.get(0));
        List<String> resList = executeCountCommand();
        assertEquals(CommandFactory.CMD_CNT + TestUtil.ONE_RESULT, resList.get(0));
    }

    @Test
    void testCountCommandShouldReturnMultiCount() {
        for(Employee employee : employeeList)
            executeAddCommand(employee);
        List<String> resList = executeCountCommand();
        assertEquals(CommandFactory.CMD_CNT + TestUtil.COMMA_RESULT + employeeList.size(), resList.get(0));
    }

    private List<String> executeCountCommand() {
        TokenGroup tokenGroup = new TokenGroup(CommandFactory.CMD_CNT, employeeList.get(0));
        Command command = CommandFactory.buildCommand(tokenGroup);
        return (new CommandExecutor(employeeStore)).execute(command);
    }
}