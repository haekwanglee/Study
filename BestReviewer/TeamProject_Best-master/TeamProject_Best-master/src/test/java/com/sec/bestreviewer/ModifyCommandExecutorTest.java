package com.sec.bestreviewer;

import com.sec.bestreviewer.command.Command;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.store.Injection;
import com.sec.bestreviewer.util.Pair;
import com.sec.bestreviewer.util.ResultStringFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.sec.bestreviewer.CommandExecutor.MAX_RESULT_NUMBER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ModifyCommandExecutorTest {

    private CommandExecutor executor;

    @Nested
    public class ModifyCommandExecutorTestWithData {

        EmployeeStore employeeStore = Injection.provideEmployeeStore();

        @BeforeEach
        public void setupEmployeeStore() {
            CommandExecutorTestUtil.addEmployees(employeeStore);
            executor = new CommandExecutor(employeeStore);
        }

        @Test
        public void testModifyCommandWithIllegalParams() {
            List<String> params = Arrays.asList(EmployeeStore.FIELD_NAME, "UAFBOWU HONG", EmployeeStore.FIELD_EMPLOYEE_NUMBER, "01234567");
            assertThrows(IllegalArgumentException.class, () -> {
                Command command = CommandFactory.buildCommand(CommandFactory.CMD_MOD, CommandExecutorTestUtil.printOptions, params);
                List<String> resList = executor.execute(command);
            });
        }

        @Test
        public void testModifyCommandWithUnknownParams() {
            List<String> params = Arrays.asList(EmployeeStore.FIELD_NAME, "UAFBOWU HONG", "careerLevel", "CL4");
            assertThrows(IllegalArgumentException.class, () -> {
                Command command = CommandFactory.buildCommand(CommandFactory.CMD_MOD, CommandExecutorTestUtil.printOptions, params);
                List<String> resList = executor.execute(command);
            });
        }

        @Test
        public void testModifyCommandWithNotEnoughParams() {
            List<String> params = Arrays.asList(EmployeeStore.FIELD_NAME, "UAFBOWU HONG");
            assertThrows(IllegalArgumentException.class, () -> {
                Command command = CommandFactory.buildCommand(CommandFactory.CMD_MOD, CommandExecutorTestUtil.printOptions, params);
                List<String> resList = executor.execute(command);
            });
        }

        @ParameterizedTest
        @CsvSource({"'02712462,UAFBOWU HONG,CL3,010-9054-6560,19780825,ADV'"})
        public void testModifyCommandWithDataWithPrintOption(String expectedResult) {
            List<String> params = Arrays.asList(EmployeeStore.FIELD_NAME, "UAFBOWU HONG", EmployeeStore.FIELD_NAME, "KILDONG HONG");
            Command command = CommandFactory.buildCommand(CommandFactory.CMD_MOD, CommandExecutorTestUtil.printOptions, params);
            List<String> resList = executor.execute(command);
            assertEquals(CommandFactory.CMD_MOD + "," + expectedResult, resList.get(0));

            resList = CommandExecutorTestUtil.getResultData(executor, Arrays.asList(EmployeeStore.FIELD_NAME, "KILDONG HONG"));
            assertEquals(CommandFactory.CMD_SCH + "," +
                    expectedResult.replace("UAFBOWU HONG", "KILDONG HONG"), resList.get(0));
        }

        @Test
        public void testModifyCommandWithDataWithPrintOption_NoneResult() {
            final List<String> params = Arrays.asList(EmployeeStore.FIELD_NAME, "SEO KFI", EmployeeStore.FIELD_NAME, "KILDONG HONG");
            final Command command = CommandFactory.buildCommand(CommandFactory.CMD_MOD, CommandExecutorTestUtil.printOptions, params);
            final List<String> resList = executor.execute(command);

            assertEquals(CommandFactory.CMD_MOD + CommandExecutorTestUtil.NONE, resList.get(0));
        }

        @ParameterizedTest
        @CsvSource({"'1', '02712462,KILDONG HONG,CL3,010-9054-6560,19780825,ADV'"})
        public void testModifyCommandWithDataWithOutPrintOption(int expectedCount, String expectedResult) {
            List<String> params = Arrays.asList(EmployeeStore.FIELD_NAME, "UAFBOWU HONG", EmployeeStore.FIELD_NAME, "KILDONG HONG");
            Command command = CommandFactory.buildCommand(CommandFactory.CMD_MOD, CommandExecutorTestUtil.emptyList, params);
            List<String> resList = executor.execute(command);
            assertEquals(CommandFactory.CMD_MOD + "," + expectedCount, resList.get(0));

            resList = CommandExecutorTestUtil.getResultData(executor, Arrays.asList(EmployeeStore.FIELD_NAME, "KILDONG HONG"));
            assertEquals(CommandFactory.CMD_SCH + "," + expectedResult, resList.get(0));
        }

        @Test
        public void testModifyCommandWithDataWithOutPrintOption_NoneResult() {
            final List<String> params = Arrays.asList(EmployeeStore.FIELD_NAME, "SEO KFI", EmployeeStore.FIELD_NAME, "KILDONG HONG");
            final Command command = CommandFactory.buildCommand(CommandFactory.CMD_MOD, CommandExecutorTestUtil.emptyList, params);
            final List<String> resList = executor.execute(command);
            assertEquals(CommandFactory.CMD_MOD + CommandExecutorTestUtil.NONE, resList.get(0));
        }

        @ParameterizedTest
        @CsvSource({"',01234567,ASDF LEE,CL3,010-1234-1234,19890123,ADV', ',02345678,QWER PARK,CL3,010-3456-3456,19850909,PRO'"})
        public void testModifyCommandWithDataReturnsSortedEmployeeList(String firstExpectedResult, String secondExpectedResult) {
            List<String> params = Arrays.asList(EmployeeStore.FIELD_CAREER_LEVEL, "CL3", EmployeeStore.FIELD_NAME, "KILDONG HONG");
            Command command = CommandFactory.buildCommand(CommandFactory.CMD_MOD, CommandExecutorTestUtil.printOptions, params);
            List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
            assertEquals(CommandFactory.CMD_MOD + firstExpectedResult, resList.get(0));
            assertEquals(CommandFactory.CMD_MOD + secondExpectedResult, resList.get(1));

            resList = CommandExecutorTestUtil.getResultData(executor, Arrays.asList(EmployeeStore.FIELD_NAME, "KILDONG HONG"));
            assertEquals(CommandFactory.CMD_SCH +
                    firstExpectedResult.replace("ASDF LEE", "KILDONG HONG"), resList.get(0));
            assertEquals(CommandFactory.CMD_SCH +
                    secondExpectedResult.replace("QWER PARK", "KILDONG HONG"), resList.get(1));
        }
    }


    @Nested
    public class ModifyCommandExecutorTestWithMock {

        private EmployeeStore employeeStore;

        @BeforeEach
        void createMockEmployeeStore() {
            employeeStore = mock(EmployeeStore.class);
            executor = new CommandExecutor(employeeStore);
        }

        @Test
        public void testModifyCommandWithMockWithPrintOption() {
            modifyCommandWithMockWithPrintOption(1);
            modifyCommandWithMockWithPrintOption(6);
        }

        private void modifyCommandWithMockWithPrintOption(int count) {
            final List<Employee> employeeList = CommandExecutorTestUtil.getEmployees(count);
            when(employeeStore.modify(any(), eq(Pair.create(EmployeeStore.FIELD_NAME, "HYUNMOK KIM")))).thenReturn(employeeList);

            final List<String> params = Arrays.asList(EmployeeStore.FIELD_NAME, "KYUMOK KIM", EmployeeStore.FIELD_NAME, "HYUNMOK KIM");
            final Command command = CommandFactory.buildCommand(CommandFactory.CMD_MOD, CommandExecutorTestUtil.printOptions, params);
            final List<String> resList = executor.execute(command);

            for (int i = 0; i < Math.min(count, MAX_RESULT_NUMBER); i++) {
                assertEquals(ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_MOD, employeeList.get(i)),
                        resList.get(i));
            }
        }

        @Test
        public void testModifyCommandWithMockWithPrintOption_NoneResult() {
            final List<Employee> employeeList = CommandExecutorTestUtil.getEmployees(0);
            when(employeeStore.modify(any(), eq(Pair.create(EmployeeStore.FIELD_NAME, "HYUNMOK KIM")))).thenReturn(employeeList);

            final List<String> options = Collections.singletonList("-p");
            final List<String> params = Arrays.asList(EmployeeStore.FIELD_NAME, "KYUMOK KIM", EmployeeStore.FIELD_NAME, "HYUNMOK KIM");
            final Command command = CommandFactory.buildCommand(CommandFactory.CMD_MOD, CommandExecutorTestUtil.printOptions, params);
            final List<String> resList = executor.execute(command);
            assertEquals(CommandFactory.CMD_MOD + CommandExecutorTestUtil.NONE, resList.get(0));
        }

        @Test
        public void testModifyCommandWithMockWithOutPrintOption() {
            final int modifiedCount = 10;
            final List<Employee> employeeList = CommandExecutorTestUtil.getEmployees(modifiedCount);
            when(employeeStore.modify(any(), eq(Pair.create(EmployeeStore.FIELD_NAME, "HYUNMOK KIM")))).thenReturn(employeeList);

            final List<String> params = Arrays.asList(EmployeeStore.FIELD_NAME, "KYUMOK KIM", EmployeeStore.FIELD_NAME, "HYUNMOK KIM");
            final Command command = CommandFactory.buildCommand(CommandFactory.CMD_MOD, CommandExecutorTestUtil.emptyList, params);
            final List<String> resList = executor.execute(command);
            assertEquals(CommandFactory.CMD_MOD + "," + modifiedCount, resList.get(0));
        }

        @Test
        public void testModifyCommandWithMockWithOutPrintOption_NoneResult() {
            final List<Employee> employeeList = CommandExecutorTestUtil.getEmployees(0);
            when(employeeStore.modify(any(), eq(Pair.create(EmployeeStore.FIELD_NAME, "HYUNMOK KIM")))).thenReturn(employeeList);

            final List<String> params = Arrays.asList(EmployeeStore.FIELD_NAME, "KYUMOK KIM", EmployeeStore.FIELD_NAME, "HYUNMOK KIM");
            final Command command = CommandFactory.buildCommand(CommandFactory.CMD_MOD, CommandExecutorTestUtil.emptyList, params);
            final List<String> resList = executor.execute(command);
            assertEquals(CommandFactory.CMD_MOD + CommandExecutorTestUtil.NONE, resList.get(0));
        }

        @Test
        public void testModifyCommandWithMockReturnsSortedEmployeeList() {
            final int modifiedCount = 10;
            final List<Employee> employeeList = CommandExecutorTestUtil.getEmployees(modifiedCount);
            final List<Employee> reversedEmployeeList = employeeList.stream()
                    .sorted(Comparator.comparing(Employee::getEmployeeNumber).reversed())
                    .collect(Collectors.toList());
            when(employeeStore.modify(any(), eq(Pair.create(EmployeeStore.FIELD_NAME, "HYUNMOK KIM")))).thenReturn(employeeList);

            final List<String> params = Arrays.asList(EmployeeStore.FIELD_NAME, "KYUMOK KIM", EmployeeStore.FIELD_NAME, "HYUNMOK KIM");
            final Command command = CommandFactory.buildCommand(CommandFactory.CMD_MOD, CommandExecutorTestUtil.printOptions, params);
            final List<String> resList = executor.execute(command);
            for (int i = 0; i < Math.min(modifiedCount, MAX_RESULT_NUMBER); i++) {
                assertEquals(
                        ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_MOD, employeeList.get(i)),
                        resList.get(i));
            }
        }
    }
}
