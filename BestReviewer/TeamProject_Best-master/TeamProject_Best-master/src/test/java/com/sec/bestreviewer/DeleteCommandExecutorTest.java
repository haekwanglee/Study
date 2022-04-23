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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.sec.bestreviewer.CommandExecutor.MAX_RESULT_NUMBER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeleteCommandExecutorTest {

    private CommandExecutor executor;

    @Nested
    public class DeleteCommandExecutorTestWithData {

        private final EmployeeStore employeeStore = Injection.provideEmployeeStore();

        @BeforeEach
        public void setupEmployeeStore() {
            CommandExecutorTestUtil.addEmployees(employeeStore);
            executor = new CommandExecutor(employeeStore);
        }

        @ParameterizedTest
        @CsvSource({"'02712462,UAFBOWU HONG,CL3,010-9054-6560,19780825,ADV'"})
        public void testDeleteCommandWithDataWithPrintOption(String expectedResult) {
            final int originCount = employeeStore.count();
            final List<String> params = Arrays.asList(EmployeeStore.FIELD_NAME, "UAFBOWU HONG");
            final Command command = CommandFactory.buildCommand(CommandFactory.CMD_DEL, CommandExecutorTestUtil.printOptions, params);
            final List<String> resList = executor.execute(command);

            assertEquals(CommandFactory.CMD_DEL + "," + expectedResult, resList.get(0));
            assertEquals(originCount - 1, employeeStore.count());
        }

        @Test
        public void testDeleteCommandWithDataWithPrintOption_NoneResult() {
            final List<String> params = Arrays.asList(EmployeeStore.FIELD_NAME, "SEO KFI");
            final Command command = CommandFactory.buildCommand(CommandFactory.CMD_DEL, CommandExecutorTestUtil.printOptions, params);
            final List<String> resList = executor.execute(command);

            assertEquals(CommandFactory.CMD_DEL + CommandExecutorTestUtil.NONE, resList.get(0));
        }

        @ParameterizedTest
        @CsvSource({"1"})
        public void testDeleteCommandWithDataWithOutPrintOption(int expectedResult) {
            final int originCount = employeeStore.count();
            final List<String> params = Arrays.asList(EmployeeStore.FIELD_NAME, "UAFBOWU HONG");
            final Command command = CommandFactory.buildCommand(CommandFactory.CMD_DEL, CommandExecutorTestUtil.emptyList, params);
            final List<String> resList = executor.execute(command);

            assertEquals(CommandFactory.CMD_DEL + "," + expectedResult, resList.get(0));
            assertEquals(originCount - 1, employeeStore.count());
        }

        @Test
        public void testDeleteCommandWithDataWithOutPrintOption_NoneResult() {
            final List<String> params = Arrays.asList(EmployeeStore.FIELD_NAME, "SEO KFI");
            final Command command = CommandFactory.buildCommand(CommandFactory.CMD_DEL, CommandExecutorTestUtil.emptyList, params);
            final List<String> resList = executor.execute(command);
            assertEquals(CommandFactory.CMD_DEL + CommandExecutorTestUtil.NONE, resList.get(0));
        }

        @Test
        public void testDeleteCommandWithDataReturnsSortedEmployeeList() {
            final int originCount = employeeStore.count();
            final List<String> params = Arrays.asList(EmployeeStore.FIELD_CAREER_LEVEL, "CL3");
            Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, CommandExecutorTestUtil.emptyList, params);
            List<String> resList = executor.execute(command);
            int searchedCount = Integer.parseInt(resList.get(0).split(",")[1]);

            command = CommandFactory.buildCommand(CommandFactory.CMD_DEL, CommandExecutorTestUtil.printOptions, params);
            resList = executor.execute(command);
            assertEquals(CommandFactory.CMD_DEL + ",01234567,ASDF LEE,CL3,010-1234-1234,19890123,ADV", resList.get(0));
            assertEquals(CommandFactory.CMD_DEL + ",02345678,QWER PARK,CL3,010-3456-3456,19850909,PRO", resList.get(1));
            assertEquals(originCount - searchedCount, employeeStore.count());
        }
    }

    @Nested
    public class DeleteCommandExecutorTestWithMock {

        private EmployeeStore employeeStore;

        @BeforeEach
        public void createMockEmployeeStore() {
            employeeStore = mock(EmployeeStore.class);
            executor = new CommandExecutor(employeeStore);
        }

        @Test
        public void testDeleteCommandWithMockWithPrintOption() {
            deleteCommandWithMockWithPrintOption(1);
            deleteCommandWithMockWithPrintOption(6);
        }

        private void deleteCommandWithMockWithPrintOption(int count) {
            final List<Employee> employeeList = CommandExecutorTestUtil.getEmployees(count);
            when(employeeStore.delete(any())).thenReturn(employeeList);

            final List<String> params = Arrays.asList(EmployeeStore.FIELD_NAME, "KYUMOK KIM");
            final Command command = CommandFactory.buildCommand(CommandFactory.CMD_DEL, CommandExecutorTestUtil.printOptions, params);
            final List<String> resList = executor.execute(command);

            for (int i = 0; i < Math.min(count, MAX_RESULT_NUMBER); i++) {
                assertEquals(ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_DEL, employeeList.get(i)),
                        resList.get(i));
            }
        }

        @Test
        public void testDeleteCommandWithMockWithPrintOption_NoneResult() {
            final List<Employee> employeeList = CommandExecutorTestUtil.getEmployees(0);
            when(employeeStore.delete(any())).thenReturn(employeeList);

            final List<String> params = Arrays.asList(EmployeeStore.FIELD_NAME, "KYUMOK KIM");
            final Command command = CommandFactory.buildCommand(CommandFactory.CMD_DEL, CommandExecutorTestUtil.printOptions, params);
            final List<String> resList = executor.execute(command);
            assertEquals(CommandFactory.CMD_DEL + CommandExecutorTestUtil.NONE, resList.get(0));
        }

        @Test
        public void testDeleteCommandWithMockWithOutPrintOption() {
            final int deletedCount = 10;
            final List<Employee> employeeList = CommandExecutorTestUtil.getEmployees(deletedCount);
            when(employeeStore.delete(any())).thenReturn(employeeList);

            final List<String> params = Arrays.asList(EmployeeStore.FIELD_NAME, "KYUMOK KIM");
            final Command command = CommandFactory.buildCommand(CommandFactory.CMD_DEL, CommandExecutorTestUtil.emptyList, params);
            final List<String> resList = executor.execute(command);
            assertEquals(CommandFactory.CMD_DEL + "," + deletedCount, resList.get(0));
        }

        @Test
        public void testDeleteCommandWithMockWithOutPrintOption_NoneResult() {
            final List<Employee> employeeList = CommandExecutorTestUtil.getEmployees(0);
            when(employeeStore.delete(any())).thenReturn(employeeList);

            final List<String> params = Arrays.asList(EmployeeStore.FIELD_NAME, "KYUMOK KIM");
            final Command command = CommandFactory.buildCommand(CommandFactory.CMD_DEL, CommandExecutorTestUtil.emptyList, params);
            final List<String> resList = executor.execute(command);
            assertEquals(CommandFactory.CMD_DEL + CommandExecutorTestUtil.NONE, resList.get(0));
        }

        @Test
        public void testDeleteCommandWithMockReturnsSortedEmployeeList() {
            final int deletedCount = 10;
            final List<Employee> employeeList = CommandExecutorTestUtil.getEmployees(deletedCount);
            final List<Employee> reversedEmployeeList = employeeList.stream()
                    .sorted(Comparator.comparing(Employee::getEmployeeNumber).reversed())
                    .collect(Collectors.toList());
            when(employeeStore.delete(any())).thenReturn(reversedEmployeeList);

            final List<String> params = Arrays.asList(EmployeeStore.FIELD_NAME, "KYUMOK KIM");
            final Command command = CommandFactory.buildCommand(CommandFactory.CMD_DEL, CommandExecutorTestUtil.printOptions, params);
            final List<String> resList = executor.execute(command);
            for (int i = 0; i < Math.min(deletedCount, MAX_RESULT_NUMBER); i++) {
                assertEquals(
                        ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_DEL, employeeList.get(i)),
                        resList.get(i));
            }
        }
    }
}
