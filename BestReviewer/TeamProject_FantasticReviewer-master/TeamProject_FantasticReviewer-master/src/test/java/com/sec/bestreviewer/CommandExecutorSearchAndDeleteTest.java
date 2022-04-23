package com.sec.bestreviewer;

import com.sec.bestreviewer.base.FieldId;
import com.sec.bestreviewer.command.Command;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.store.Injection;
import com.sec.bestreviewer.util.ResultStringFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandExecutorSearchAndDeleteTest {
    private EmployeeStore employeeStore;
    private TestUtil testUtil;

    @BeforeEach
    void crateEmployeeStore() {
        employeeStore = Injection.provideEmployeeStore();
        testUtil = new TestUtil();
    }

    @Test
    void testDeleteEmployNumberWithPrintOption_CheckEachResult() {
        executeCommandWithPrintOptionByColumn(CommandFactory.CMD_DEL, FieldId.FIELD_EMPLOYEE_NUMBER);
    }

    @Test
    void testDeleteNameWithPrintOption_CheckEachResult() {
        executeCommandWithPrintOptionByColumn(CommandFactory.CMD_DEL, FieldId.FIELD_NAME);
    }

    @Test
    void testDeleteCLWithPrintOption_CheckEachResult() {
        executeCommandWithPrintOptionByColumn(CommandFactory.CMD_DEL, FieldId.FIELD_CAREER_LEVEL);
    }

    @Test
    void testDeletePhoneNumberWithPrintOption_CheckEachResult() {
        executeCommandWithPrintOptionByColumn(CommandFactory.CMD_DEL, FieldId.FIELD_PHONE_NUMBER);
    }

    @Test
    void testDeleteBirthdayWithPrintOption_CheckEachResult() {
        executeCommandWithPrintOptionByColumn(CommandFactory.CMD_DEL, FieldId.FIELD_BIRTH_DAY);
    }

    @Test
    void testSearchEmployeeNumberWithPrintOption_CheckEachResult() {
        executeCommandWithPrintOptionByColumn(CommandFactory.CMD_SCH, FieldId.FIELD_EMPLOYEE_NUMBER);
    }

    @Test
    void testSearchNameWithPrintOption_CheckEachResult() {
        executeCommandWithPrintOptionByColumn(CommandFactory.CMD_SCH, FieldId.FIELD_NAME);
    }

    @Test
    void testSearchPhoneNumWithPrintOption_CheckEachResult() {
        executeCommandWithPrintOptionByColumn(CommandFactory.CMD_SCH, FieldId.FIELD_PHONE_NUMBER);
    }

    @Test
    void testSearchBirthdayWithPrintOption_CheckEachResult() {
        executeCommandWithPrintOptionByColumn(CommandFactory.CMD_SCH, FieldId.FIELD_BIRTH_DAY);
    }

    private void executeCommandWithPrintOptionByColumn(String command, String fieldName) {
        final List<Employee> employeeList = testUtil.createTestEmployeeList(TestUtil.EMPLOYEE_LIST);
        testUtil.addTestEmployee(employeeStore, employeeList);
        for (Employee employee : employeeList) {
            TokenGroup tokenGroup = testUtil.createTokenGroupWithPrintOption(command, employee, fieldName);
            final List<String> resList = executeCommand(tokenGroup);
            assertEquals(ResultStringFormatter.getEmployeeToFormattedString(command, employee), resList.get(0));
        }
    }

    @Test
    void testDeleteCLWithPrintOption_SortedResult() {
        List<String> params = Arrays.asList(FieldId.FIELD_CAREER_LEVEL, "CL2");
        executeCommandReturnsSortedResult(CommandFactory.CMD_DEL, params);
    }

    @Test
    void testDeleteNameWithPrintOption_SortedResult() {
        List<String> params = Arrays.asList(FieldId.FIELD_NAME, "KYUMOK SIM");
        executeCommandReturnsSortedResult(CommandFactory.CMD_DEL, params);
    }

    @Test
    void testDeletePhoneNumberWithPrintOption_SortedResult() {
        List<String> params = Arrays.asList(FieldId.FIELD_PHONE_NUMBER, "010-9777-6054");
        executeCommandReturnsSortedResult(CommandFactory.CMD_DEL, params);
    }

    @Test
    void testDeleteBirthdayWithPrintOption_SortedResult() {
        List<String> params = Arrays.asList(FieldId.FIELD_BIRTH_DAY, "19980905");
        executeCommandReturnsSortedResult(CommandFactory.CMD_DEL, params);
    }

    @Test
    void testSearchCLCommandWithPrintOption_SortedResult() {
        List<String> params = Arrays.asList(FieldId.FIELD_CAREER_LEVEL, "CL2");
        executeCommandReturnsSortedResult(CommandFactory.CMD_SCH, params);
    }

    @Test
    void testSearchNameCommandWithPrintOption_SortedResult() {
        List<String> params = Arrays.asList(FieldId.FIELD_NAME, "KYUMOK SIM");
        executeCommandReturnsSortedResult(CommandFactory.CMD_SCH, params);
    }

    @Test
    void testSearchPhoneNumberCommandWithPrintOption_SortedResult() {
        List<String> params = Arrays.asList(FieldId.FIELD_PHONE_NUMBER, "010-9777-6054");
        executeCommandReturnsSortedResult(CommandFactory.CMD_SCH, params);
    }

    @Test
    void testSearchBirthdayCommandWithPrintOption_SortedResult() {
        List<String> params = Arrays.asList(FieldId.FIELD_BIRTH_DAY, "19980905");
        executeCommandReturnsSortedResult(CommandFactory.CMD_SCH, params);
    }


    void executeCommandReturnsSortedResult(String command, List<String> params) {
        final List<Employee> employeeList = testUtil.createTestEmployeeList(TestUtil.EMPLOY_LIST_SAME_VALUE);
        testUtil.addTestEmployee(employeeStore, employeeList);
        TokenGroup tokenGroup = testUtil.createTokenGroupWithPrintOption(command, params.get(0), params.get(1));
        final List<String> resList = executeCommand(tokenGroup);
        int index = employeeList.size() - 1;
        for (String res : resList)
            assertEquals(ResultStringFormatter.getEmployeeToFormattedString(command, employeeList.get(index--)), res);
    }

    @Test
    void testDeleteNameWithPrintOption_NoneResult() {
        List<String> params = Arrays.asList(FieldId.FIELD_NAME, "NOKYUMOK KIM");
        executeCommandWithPrintOption(CommandFactory.CMD_DEL, params);
    }

    @Test
    void testDeletePhoneNumberWithPrintOption_NoneResult() {
        List<String> params = Arrays.asList(FieldId.FIELD_PHONE_NUMBER, "111-9777-6054");
        executeCommandWithPrintOption(CommandFactory.CMD_DEL, params);
    }

    @Test
    void testSearchPhoneNumberWithPrintOption_NoneResult() {
        List<String> params = Arrays.asList(FieldId.FIELD_NAME, "NOKYUMOK KIM");
        executeCommandWithPrintOption(CommandFactory.CMD_SCH, params);
    }

    @Test
    void testSearchBirthdayWithPrintOption_NoneResult() {
        List<String> params = Arrays.asList(FieldId.FIELD_BIRTH_DAY, "18881208");
        executeCommandWithPrintOption(CommandFactory.CMD_SCH, params);
    }

    @Test
    void testDeleteNameWithOutPrintOption_NoneResult() {
        List<String> params = Arrays.asList(FieldId.FIELD_NAME, "NOKYUMOK KIM");
        executeCommandWithPrintOption(CommandFactory.CMD_DEL, params);
    }

    @Test
    void testDeletePhoneNumberWithOutPrintOption_NoneResult() {
        List<String> params = Arrays.asList(FieldId.FIELD_PHONE_NUMBER, "111-9777-6054");
        executeCommandWithPrintOption(CommandFactory.CMD_DEL, params);
    }

    @Test
    void testSearchPhoneNumberWithOutPrintOption_NoneResult() {
        List<String> params = Arrays.asList(FieldId.FIELD_PHONE_NUMBER, "111-9777-6054");
        executeCommandWithPrintOption(CommandFactory.CMD_SCH, params);
    }

    @Test
    void testSearchBirthdayWithOutPrintOption_NoneResult() {
        List<String> params = Arrays.asList(FieldId.FIELD_BIRTH_DAY, "18881208");
        executeCommandWithPrintOption(CommandFactory.CMD_SCH, params);
    }

    void executeCommandWithPrintOption(String command, List<String> params) {
        testUtil.createTestEmployeeList(TestUtil.EMPLOYEE_LIST);
        TokenGroup tokenGroup = testUtil.createTokenGroupWithPrintOption(command, params.get(0), params.get(1));
        final List<String> resList = executeCommand(tokenGroup);
        assertEquals(command + TestUtil.NONE_RESULT, resList.get(0));
    }

    @Test
    void testDeleteNameWithOutPrintOption_CheckEachResult() {
        executeCommandWithOutPrintOption_CheckEachResult(CommandFactory.CMD_DEL, FieldId.FIELD_NAME);
    }

    @Test
    void testDeleteCLWithOutPrintOption_CheckEachResult() {
        executeCommandWithOutPrintOption_CheckEachResult(CommandFactory.CMD_DEL, FieldId.FIELD_CAREER_LEVEL);
    }

    @Test
    void testDeletePhoneNumberWithOutPrintOption_CheckEachResult() {
        executeCommandWithOutPrintOption_CheckEachResult(CommandFactory.CMD_DEL, FieldId.FIELD_PHONE_NUMBER);
    }

    @Test
    void testSearchNameCommandWithOutPrintOption_CheckEachResult() {
        executeCommandWithOutPrintOption_CheckEachResult(CommandFactory.CMD_SCH, FieldId.FIELD_NAME);
    }

    @Test
    void testSearchPhoneNumberCommandWithOutPrintOption_CheckEachResult() {
        executeCommandWithOutPrintOption_CheckEachResult(CommandFactory.CMD_SCH, FieldId.FIELD_PHONE_NUMBER);
    }

    @Test
    void testSearchBirthdayCommandWithOutPrintOption_CheckEachResult() {
        executeCommandWithOutPrintOption_CheckEachResult(CommandFactory.CMD_SCH, FieldId.FIELD_BIRTH_DAY);
    }

    void executeCommandWithOutPrintOption_CheckEachResult(String command, String fieldName) {
        final List<Employee> employeeList = testUtil.createTestEmployeeList(TestUtil.EMPLOYEE_LIST);
        testUtil.addTestEmployee(employeeStore, employeeList);
        for (Employee employee : employeeList) {
            TokenGroup tokenGroup = testUtil.createTokenGroupWithOutPrint(command, employee, fieldName);
            final List<String> resList = executeCommand(tokenGroup);
            assertEquals(command + TestUtil.ONE_RESULT, resList.get(0));
        }
    }

    private List<String> executeCommand(TokenGroup token) {
        final Command command = CommandFactory.buildCommand(token);
        return (new CommandExecutor(employeeStore)).execute(command);
    }
}