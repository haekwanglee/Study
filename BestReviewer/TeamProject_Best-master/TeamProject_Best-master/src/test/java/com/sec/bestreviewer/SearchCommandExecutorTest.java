package com.sec.bestreviewer;

import com.sec.bestreviewer.command.Command;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.store.Injection;
import com.sec.bestreviewer.util.ResultStringFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.sec.bestreviewer.CommandExecutor.MAX_RESULT_NUMBER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SearchCommandExecutorTest {

    private CommandExecutor executor;

    @Nested
    public class SearchCommandExecutorTestWithData {

        EmployeeStore employeeStore = Injection.provideEmployeeStore();

        @BeforeEach
        public void setupEmployeeStore() {
            CommandExecutorTestUtil.addEmployees(employeeStore);
            executor = new CommandExecutor(employeeStore);
        }

        @ParameterizedTest
        @CsvSource({"'02712462,UAFBOWU HONG,CL3,010-9054-6560,19780825,ADV'"})
        public void testSearchCommandWithDataWithPrintOption(String expectedResult) {
            final List<String> params = Arrays.asList(EmployeeStore.FIELD_NAME, "UAFBOWU HONG");
            final Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, CommandExecutorTestUtil.printOptions, params);
            final List<String> resList = executor.execute(command);

            assertEquals(CommandFactory.CMD_SCH + "," + expectedResult, resList.get(0));
        }

        @Test
        public void testSearchCommandWithDataWithPrintOption_NoneResult() {
            final List<String> params = Arrays.asList(EmployeeStore.FIELD_NAME, "SEO KFI");
            final Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, CommandExecutorTestUtil.printOptions, params);
            final List<String> resList = executor.execute(command);

            assertEquals(CommandFactory.CMD_SCH + CommandExecutorTestUtil.NONE, resList.get(0));
        }

        @Test
        public void testSearchCommandWithDataWithOutPrintOption() {
            final int searchedCount = Arrays.asList(CommandExecutorTestUtil.employees).stream()
                    .filter(employee -> employee.contains("CL3"))
                    .collect(Collectors.toList())
                    .size();

            final List<String> params = Arrays.asList(EmployeeStore.FIELD_CAREER_LEVEL, "CL3");
            final Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, CommandExecutorTestUtil.emptyList, params);
            final List<String> resList = executor.execute(command);
            assertEquals(CommandFactory.CMD_SCH + "," + searchedCount, resList.get(0));
        }

        @Test
        public void testSearchCommandWithDataWithOutPrintOption_NoneResult() {
            final List<String> params = Arrays.asList(EmployeeStore.FIELD_NAME, "SEO KFI");
            final Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, CommandExecutorTestUtil.emptyList, params);
            final List<String> resList = executor.execute(command);

            assertEquals(CommandFactory.CMD_SCH + CommandExecutorTestUtil.NONE, resList.get(0));
        }

        @Test
        public void testSearchCommandWithDataReturnsSortedEmployeeList() {
            final List<String> params = Arrays.asList(EmployeeStore.FIELD_CAREER_LEVEL, "CL3");
            final Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, CommandExecutorTestUtil.printOptions, params);
            final List<String> resList = executor.execute(command);
            assertEquals(CommandFactory.CMD_SCH + ",01234567,ASDF LEE,CL3,010-1234-1234,19890123,ADV", resList.get(0));
            assertEquals(CommandFactory.CMD_SCH + ",02345678,QWER PARK,CL3,010-3456-3456,19850909,PRO", resList.get(1));
        }
    }

    @Nested
    public class SearchCommandExecutorTestWithMock {

        private EmployeeStore employeeStore;

        @BeforeEach
        void createMockEmployeeStore() {
            employeeStore = mock(EmployeeStore.class);
            executor = new CommandExecutor(employeeStore);
        }


        @Test
        public void testSearchCommandWithPrintOption() {
            searchCommandWithMockWithPrintOption(1);
            searchCommandWithMockWithPrintOption(6);
        }

        public void searchCommandWithMockWithPrintOption(int count) {
            final List<Employee> employeeList = CommandExecutorTestUtil.getEmployees(count);
            when(employeeStore.search(any())).thenReturn(employeeList);

            final List<String> params = Arrays.asList(EmployeeStore.FIELD_NAME, "KYUMOK KIM");
            final Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, CommandExecutorTestUtil.printOptions, params);
            final List<String> resList = executor.execute(command);
            for (int i = 0; i < Math.min(count, MAX_RESULT_NUMBER); i++) {
                assertEquals(
                        ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_SCH, employeeList.get(i)),
                        resList.get(i));
            }
        }

        @Test
        public void testSearchCommandWithMockWithPrintOption_NoneResult() {
            final List<Employee> employeeList = CommandExecutorTestUtil.getEmployees(0);
            when(employeeStore.search(any())).thenReturn(employeeList);

            final List<String> params = Arrays.asList(EmployeeStore.FIELD_NAME, "KYUMOK KIM");
            final Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, CommandExecutorTestUtil.printOptions, params);
            final List<String> resList = executor.execute(command);
            assertEquals(CommandFactory.CMD_SCH + CommandExecutorTestUtil.NONE, resList.get(0));
        }

        @Test
        public void testSearchCommandWithMockWithOutPrintOption() {
            final int deletedCount = 10;
            final List<Employee> employeeList = CommandExecutorTestUtil.getEmployees(deletedCount);
            when(employeeStore.search(any())).thenReturn(employeeList);

            final List<String> params = Arrays.asList(EmployeeStore.FIELD_NAME, "KYUMOK KIM");
            final Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, CommandExecutorTestUtil.emptyList, params);
            final List<String> resList = executor.execute(command);
            assertEquals(CommandFactory.CMD_SCH + "," + deletedCount, resList.get(0));
        }

        @Test
        public void testSearchCommandWithMockWithOutPrintOption_NoneResult() {
            final List<Employee> employeeList = CommandExecutorTestUtil.getEmployees(0);
            when(employeeStore.search(any())).thenReturn(employeeList);

            final List<String> params = Arrays.asList(EmployeeStore.FIELD_NAME, "KYUMOK KIM");
            final Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, CommandExecutorTestUtil.emptyList, params);
            final List<String> resList = executor.execute(command);
            assertEquals(CommandFactory.CMD_SCH + CommandExecutorTestUtil.NONE, resList.get(0));
        }
    }
}
