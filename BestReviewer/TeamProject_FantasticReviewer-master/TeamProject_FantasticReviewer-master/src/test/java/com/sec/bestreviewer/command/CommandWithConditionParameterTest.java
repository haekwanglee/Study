package com.sec.bestreviewer.command;

import com.sec.bestreviewer.CommandFactory;
import com.sec.bestreviewer.TestUtil;
import com.sec.bestreviewer.base.*;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.store.Injection;
import com.sec.bestreviewer.util.ResultStringFormatter;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandWithConditionParameterTest {

    public static final String NEW_NAME = "NEW NAME";
    private EmployeeStore employeeStore;
    private ConditionParameter conditionParameter;
    private List<Employee> employeeList;

    @BeforeEach
    void createConditionParameter() {
        employeeStore = Injection.provideEmployeeStore();
        TestUtil testUtil = new TestUtil();
        employeeList = testUtil.createTestEmployeeList(TestUtil.EMPLOYEE_LIST);
        testUtil.addTestEmployee(employeeStore, employeeList);
    }

    @Test
    void testDeleteWithConditionParameter() {
        conditionParameter = createConditionParameter(employeeList.get(0));
        String expected = createExpectedResult(CommandFactory.CMD_DEL);
        Command command = new DeleteCommand(conditionParameter);
        executeCommandAndCheckResult(expected, command);
    }

    @Test
    void testSearchWithConditionParameter() {
        executeSearchCommand();
    }

    @Test
    void testModifyWithConditionParameter() {
        conditionParameter = createConditionModifyParameter(employeeList.get(0));
        String expected = createExpectedResult(CommandFactory.CMD_MOD);
        Command command = new ModifyCommand(conditionParameter);
        executeCommandAndCheckResult(expected, command);
        executeSearchCommand();
    }

    private void executeSearchCommand() {
        conditionParameter = createConditionParameter(employeeList.get(0));
        String expected = createExpectedResult(CommandFactory.CMD_SCH);
        Command command = new SearchCommand(conditionParameter);
        executeCommandAndCheckResult(expected, command);
    }

    private ConditionParameter createConditionParameter(Employee employee) {
        ConditionValue conditionValue = new ConditionValue(FieldId.FIELD_NAME, employee.getName(), OptionId.OPTION_PRINT, OptionId.OPTION_NONE, OptionId.OPTION_NONE);
        return new ConditionParameter(conditionValue, null, null, null);
    }

    private ConditionParameter createConditionModifyParameter(Employee employee) {
        ConditionValue conditionValue = new ConditionValue(FieldId.FIELD_NAME, employee.getName(), OptionId.OPTION_PRINT, OptionId.OPTION_NONE, OptionId.OPTION_NONE);
        Value toModifyValue = new Value(FieldId.FIELD_NAME, NEW_NAME);
        return new ConditionParameter(conditionValue, null, toModifyValue, null);
    }

    @NotNull
    private String createExpectedResult(String cmdDel) {
        return ResultStringFormatter.getEmployeeToFormattedString(cmdDel, employeeList.get(0));
    }

    private void executeCommandAndCheckResult(String expected, Command command) {
        List<String> resList = command.execute(employeeStore);
        assertEquals(expected, resList.get(0));
    }
}
