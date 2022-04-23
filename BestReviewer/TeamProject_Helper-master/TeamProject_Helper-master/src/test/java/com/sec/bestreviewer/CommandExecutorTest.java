package com.sec.bestreviewer;

import com.sec.bestreviewer.command.Command;
import com.sec.bestreviewer.store.ConditionParam;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.util.ResultStringFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static com.sec.bestreviewer.CommandExecutor.MAX_RESULT_NUMBER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CommandExecutorTest {

    private EmployeeStore employeeStore;

    @BeforeEach
    void createMockEmployeeStore() {
        employeeStore = mock(EmployeeStore.class);
    }

    @Test
    public void testQueryExecutorReturnsResultString() {
        final List<String> options = Collections.singletonList("-p");
        final List<String> params = Arrays.asList("18050301", "KYUMOK KIM", "CL2", "010-9777-6055", "19980906", "ADV");
        final Command command = CommandFactory.buildCommand("ADD", options, params);
        final List<String> res = (new CommandExecutor()).execute(command);
        assertNotNull(res);
    }

    @Test
    public void testAddCommandReturnsEmptyList() {
        final List<String> options = Arrays.asList(" ", " ", " ");
        final List<String> params = Arrays.asList("18050301", "KYUMOK KIM", "CL2", "010-9777-6055", "19980906", "ADV");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_ADD, options, params);
        final List<String> res = (new CommandExecutor()).execute(command);
        assertNotNull(res);
        assertEquals(0, res.size());
    }

    @Test
    public void testDeleteCommandWithPrintOption() {
        deleteCommandWithPrintOption(1);
        deleteCommandWithPrintOption(6);
    }

    private void deleteCommandWithPrintOption(int count) {
        final List<Employee> employeeList = getEmployees(count);
        when(employeeStore.search(new ConditionParam("name", "KYUMOK KIM"))).thenReturn(employeeList);

        final List<String> options = Collections.singletonList("-p");
        final List<String> params = Arrays.asList("name", "KYUMOK KIM");
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
                    new Employee(employeeNumber, "SEO KFI", "CL1", "010-1234-5678", "20190101", "ADV"));
        }
        return employeeList;
    }

    @Test
    public void testDeleteCommandWithOutPrintOption() {
        final int deletedCount = 10;
        final List<Employee> employeeList = getEmployees(deletedCount);
        when(employeeStore.search(new ConditionParam("name", "KYUMOK KIM"))).thenReturn(employeeList);

        final List<String> options = Arrays.asList(" ", " ", " ");
        final List<String> params = Arrays.asList("name", "KYUMOK KIM");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_DEL, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(CommandFactory.CMD_DEL + "," + deletedCount, resList.get(0));
    }

    @Test
    public void testDeleteCommandWithOutPrintOption_NoneResult() {
        final List<Employee> employeeList = getEmployees(0);
        when(employeeStore.search(new ConditionParam("name", "KYUMOK KIM"))).thenReturn(employeeList);

        final List<String> options = Arrays.asList(" ", " ", " ");
        final List<String> params = Arrays.asList("name", "KYUMOK KIM");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_DEL, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(CommandFactory.CMD_DEL + ",NONE", resList.get(0));
    }

    @Test
    public void testSearchCommandWithPrintOption() {
        searchCommandWithPrintOption(1);
        searchCommandWithPrintOption(6);
    }

    void searchCommandWithPrintOption(int count) {
        final List<Employee> employeeList = getEmployees(count);
        when(employeeStore.search(new ConditionParam("name", "KYUMOK KIM"))).thenReturn(employeeList);

        final List<String> options = Collections.singletonList("-p");
        final List<String> params = Arrays.asList("name", "KYUMOK KIM");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        for (int i = 0; i < Math.min(count, MAX_RESULT_NUMBER); i++) {
            assertEquals(
                    ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_SCH, employeeList.get(i)),
                    resList.get(i));
        }
    }

    @Test
    public void testSearchCommandWithPrintOption_NoneResult() {
        final List<Employee> employeeList = getEmployees(0);
        when(employeeStore.search(new ConditionParam("name", "KYUMOK KIM"))).thenReturn(employeeList);

        final List<String> options = Collections.singletonList("-p");
        final List<String> params = Arrays.asList("name", "KYUMOK KIM");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(CommandFactory.CMD_SCH + ",NONE", resList.get(0));
    }

    @Test
    public void testSearchCommandWithOutPrintOption() {
        final int deletedCount = 10;
        final List<Employee> employeeList = getEmployees(deletedCount);
        when(employeeStore.search(new ConditionParam("name", "KYUMOK KIM"))).thenReturn(employeeList);

        final List<String> options = Arrays.asList(" ", " ", " ");
        final List<String> params = Arrays.asList("name", "KYUMOK KIM");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(CommandFactory.CMD_SCH + "," + deletedCount, resList.get(0));
    }

    @Test
    public void testSearchCommandWithOutPrintOption_NoneResult() {
        final List<Employee> employeeList = getEmployees(0);
        when(employeeStore.search(new ConditionParam("name", "KYUMOK KIM"))).thenReturn(employeeList);

        final List<String> options = Arrays.asList(" ", " ", " ");
        final List<String> params = Arrays.asList("name", "KYUMOK KIM");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(CommandFactory.CMD_SCH + ",NONE", resList.get(0));
    }

    @Test
    public void testCountCommandShouldReturnCountNumberString() {
        when(employeeStore.count()).thenReturn(1);

        final List<String> options = Arrays.asList(" ", " ", " ");
        final List<String> params = Arrays.asList(" ", " ");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_CNT, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(CommandFactory.CMD_CNT + ",1", resList.get(0));
    }

    @Test
    public void testDeleteCommandReturnsSortedEmployeeList() {
        final int deletedCount = 10;
        final List<Employee> employeeList = getEmployees(deletedCount);
        final List<Employee> reversedEmployeeList = employeeList.stream()
                .sorted(Comparator.comparing(Employee::getEmployeeNumber).reversed())
                .collect(Collectors.toList());
        when(employeeStore.search(new ConditionParam("name", "KYUMOK KIM"))).thenReturn(reversedEmployeeList);

        final List<String> options = Collections.singletonList("-p");
        final List<String> params = Arrays.asList("name", "KYUMOK KIM");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_DEL, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        for (int i = 0; i < Math.min(deletedCount, MAX_RESULT_NUMBER); i++) {
            assertEquals(
                    ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_DEL, employeeList.get(i)),
                    resList.get(i));
        }
    }

    @Test
    public void testModifyCommandWithPrintOption() {
        modifyCommandWithPrintOption(1);
        modifyCommandWithPrintOption(6);
    }

    private void modifyCommandWithPrintOption(int count) {
        when(employeeStore.search(new ConditionParam("name", "SEO KFI"))).thenReturn(new ArrayList<>());

        final List<String> options = Arrays.asList("-p", " ", " ");
        final List<String> params = Arrays.asList("name", "SEO KFI", "name", "SEO KFC");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_MOD, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);

        for (int i = 0; i < Math.min(count, MAX_RESULT_NUMBER); i++) {
            assertEquals(ResultStringFormatter.getEmployeeListToFormattedString(new ArrayList<>(), CommandFactory.CMD_MOD),
                    resList);
        }
    }

    @Test
    public void testModifyCommandWithOutPrintOption() {
        final int modifiedCount = 10;
        final List<Employee> employeeList = getEmployees(modifiedCount);
        when(employeeStore.search(new ConditionParam("name", "KYUMOK KIM"))).thenReturn(employeeList);

        final List<String> options = Arrays.asList(" ", " ", " ");
        final List<String> params = Arrays.asList("name", "KYUMOK KIM", "name", "KYUMOK LEE");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_MOD, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(CommandFactory.CMD_MOD + "," + modifiedCount, resList.get(0));
    }

    @Test
    public void testModifyCommandWithOutPrintOption_NoneResult() {
        final List<Employee> employeeList = getEmployees(0);
        when(employeeStore.search(new ConditionParam("name", "KYUMOK KIM"))).thenReturn(employeeList);

        final List<String> options = Arrays.asList(" ", " ", " ");
        final List<String> params = Arrays.asList("name", "KYUMOK KIM", "name", "KYUMOK LEE");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_MOD, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(CommandFactory.CMD_MOD + ",NONE", resList.get(0));
    }

    private List<Employee> getEmployeesForOptionTest() {
        final List<Employee> employeeList = new ArrayList<>();
        employeeList.add(
                new Employee("90000001", "AAA KIM", "CL1", "010-1234-5678", "20170101", "ADV"));
        employeeList.add(
                new Employee("90000002", "BBB LEE", "CL2", "010-1234-6789", "20180202", "PRO"));
        employeeList.add(
                new Employee("90000003", "CCC CHA", "CL3", "010-2345-6789", "20190303", "EX"));
        return employeeList;
    }

    @Test
    @DisplayName("이름 조건에 의한 Command 실행")
    void testCommandWithNameConditionOption() {
        final List<Employee> employeeList = getEmployeesForOptionTest();
        List<Employee> result = new ArrayList<>();

        result.add(employeeList.get(0));
        when(employeeStore.search(new ConditionParam("name", "AAA", "-f"))).thenReturn(result);
        when(employeeStore.search(new ConditionParam("name", "KIM", "-l"))).thenReturn(result);

        List<String> options = Arrays.asList("-p", "-f", " ");
        List<String> params = Arrays.asList("name", "AAA");
        Command command = CommandFactory.buildCommand(CommandFactory.CMD_DEL, options, params);
        List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_DEL, employeeList.get(0)),
                resList.get(0));


        options = Arrays.asList("-p", "-f", " ");
        params = Arrays.asList("name", "AAA");
        command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_SCH, employeeList.get(0)),
                resList.get(0));

        options = Arrays.asList("-p", "-l", " ");
        params = Arrays.asList("name", "KIM", "cl", "CL2");
        command = CommandFactory.buildCommand(CommandFactory.CMD_MOD, options, params);
        resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_MOD, employeeList.get(0)),
                resList.get(0));
    }

    @Test
    @DisplayName("전화번호 조건에 의한 Command 실행")
    void testCommandWithPhoneNumberConditionOption() {
        final List<Employee> employeeList = getEmployeesForOptionTest();
        List<Employee> result = new ArrayList<>();

        result.add(employeeList.get(0));
        when(employeeStore.search(new ConditionParam("phoneNum", "1234", "-m"))).thenReturn(result);
        when(employeeStore.search(new ConditionParam("phoneNum", "5678", "-l"))).thenReturn(result);

        List<String> options = Arrays.asList("-p", "-m", " ");
        List<String> params = Arrays.asList("phoneNum", "1234");
        Command command = CommandFactory.buildCommand(CommandFactory.CMD_DEL, options, params);
        List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_DEL, employeeList.get(0)),
                resList.get(0));

        options = Arrays.asList("-p", "-m", " ");
        params = Arrays.asList("phoneNum", "1234");
        command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_SCH, employeeList.get(0)),
                resList.get(0));

        options = Arrays.asList("-p", "-l", " ");
        params = Arrays.asList("phoneNum", "5678", "cl", "CL2");
        command = CommandFactory.buildCommand(CommandFactory.CMD_MOD, options, params);
        resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_MOD, employeeList.get(0)),
                resList.get(0));
    }

    @Test
    @DisplayName("생년월일 조건에 의한 Command 실행")
    void testCommandWithBirthdayConditionOption() {
        final List<Employee> employeeList = getEmployeesForOptionTest();
        List<Employee> result = new ArrayList<>();

        result.add(employeeList.get(0));
        when(employeeStore.search(new ConditionParam("birthday", "2017", "-y"))).thenReturn(result);
        when(employeeStore.search(new ConditionParam("birthday", "01", "-m"))).thenReturn(result);
        when(employeeStore.search(new ConditionParam("birthday", "01", "-d"))).thenReturn(result);

        List<String> options = Arrays.asList("-p", "-y", " ");
        List<String> params = Arrays.asList("birthday", "2017");
        Command command = CommandFactory.buildCommand(CommandFactory.CMD_DEL, options, params);
        List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_DEL, employeeList.get(0)),
                resList.get(0));

        options = Arrays.asList("-p", "-m", " ");
        params = Arrays.asList("birthday", "01");
        command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_SCH, employeeList.get(0)),
                resList.get(0));

        options = Arrays.asList("-p", "-d", " ");
        params = Arrays.asList("birthday", "01", "cl", "CL2");
        command = CommandFactory.buildCommand(CommandFactory.CMD_MOD, options, params);
        resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_MOD, employeeList.get(0)),
                resList.get(0));
    }

    @Test
    @DisplayName("부등호 조건에 의한 Command 실행")
    void testCommandWithCompareOption() {
        final List<Employee> employeeList = getEmployeesForOptionTest();
        List<Employee> result = new ArrayList<>();

        result.add(employeeList.get(2));
        when(employeeStore.search(new ConditionParam("name", "CCB", "-f", ConditionParam.CompareOption.GREATER_EQUAL))).thenReturn(result);
        when(employeeStore.search(new ConditionParam("name", "CCC", " ", ConditionParam.CompareOption.SMALL))).thenReturn(result);
        when(employeeStore.search(new ConditionParam("birthday", "2019", "-y", ConditionParam.CompareOption.GREATER))).thenReturn(result);
        when(employeeStore.search(new ConditionParam("birthday", "20170101", " ", ConditionParam.CompareOption.GREATER))).thenReturn(result);
        when(employeeStore.search(new ConditionParam("phoneNum", "1234", "-m", ConditionParam.CompareOption.SMALL_EQUAL))).thenReturn(result);
        when(employeeStore.search(new ConditionParam("phoneNum", "010-1234-5678", " ", ConditionParam.CompareOption.SMALL_EQUAL))).thenReturn(result);
        when(employeeStore.search(new ConditionParam("cl", "CL2", " ", ConditionParam.CompareOption.SMALL))).thenReturn(result);

        List<String> options = Arrays.asList("-p", "-f", "-ge");
        List<String> params = Arrays.asList("name", "CCB");
        Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_SCH, employeeList.get(2)),
                resList.get(0));

        options = Arrays.asList("-p", " ", "-s");
        params = Arrays.asList("name", "CCC");
        command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_SCH, employeeList.get(2)),
                resList.get(0));

        options = Arrays.asList("-p", "-y", "-g");
        params = Arrays.asList("birthday", "2019");
        command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_SCH, employeeList.get(2)),
                resList.get(0));

        options = Arrays.asList("-p", " ", "-g");
        params = Arrays.asList("birthday", "20170101");
        command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_SCH, employeeList.get(2)),
                resList.get(0));

        options = Arrays.asList("-p", "-m", "-se");
        params = Arrays.asList("phoneNum", "1234");
        command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_SCH, employeeList.get(2)),
                resList.get(0));

        options = Arrays.asList("-p", " ", "-se");
        params = Arrays.asList("phoneNum", "010-1234-5678");
        command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_SCH, employeeList.get(2)),
                resList.get(0));

        options = Arrays.asList("-p", " ", "-s");
        params = Arrays.asList("cl", "CL2");
        command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_SCH, employeeList.get(2)),
                resList.get(0));
    }

    @Test
    @DisplayName("AND/OR 조건에 의한 Command 실행")
    void testCommandWithAndOrOption() {
        final List<Employee> employeeList = getEmployeesForOptionTest();
        List<Employee> result = new ArrayList<>();

        result.add(employeeList.get(2));
        when(employeeStore.searchAnd(new ConditionParam("name", "KIM", "-l"),
                new ConditionParam("cl", "CL1"))).thenReturn(result);
        when(employeeStore.searchOr(new ConditionParam("phoneNum", "1234", "-m"),
                new ConditionParam("birthday", "2019", "-y"))).thenReturn(result);
        when(employeeStore.searchAnd(new ConditionParam("phoneNum", "6789", "-l"),
                new ConditionParam("birthday", "02", "-d"))).thenReturn(result);

        List<String> options = Arrays.asList("-p", "-l", " ", "-a", " ", " ");
        List<String> params = Arrays.asList("name", "KIM", "cl", "CL1");
        Command command = CommandFactory.buildCommand(CommandFactory.CMD_DEL, options, params);
        List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_DEL, employeeList.get(2)),
                resList.get(0));

        options = Arrays.asList("-p", "-m", " ", "-o", "-y", " ");
        params = Arrays.asList("phoneNum", "1234", "birthday", "2019");
        command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_SCH, employeeList.get(2)),
                resList.get(0));

        options = Arrays.asList("-p", "-l", " ", "-a", "-d", " ");
        params = Arrays.asList("phoneNum", "6789", "birthday", "02", "certi", "EX");
        command = CommandFactory.buildCommand(CommandFactory.CMD_MOD, options, params);
        resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_MOD, employeeList.get(2)),
                resList.get(0));
    }

    @Test
    @DisplayName("AND/OR & 부등호 조건에 의한 Command 실행")
    void testCommandWithAndOrCompareOption() {
        final List<Employee> employeeList = getEmployeesForOptionTest();
        List<Employee> result = new ArrayList<>();

        result.add(employeeList.get(0));
        when(employeeStore.searchAnd(new ConditionParam("name", "KIM", "-l", ConditionParam.CompareOption.SMALL_EQUAL),
                new ConditionParam("phoneNum", "1239", "-m", ConditionParam.CompareOption.GREATER))).thenReturn(result);
        when(employeeStore.searchOr(new ConditionParam("phoneNum", "1239", "-m", ConditionParam.CompareOption.SMALL),
                new ConditionParam("birthday", "2019", "-y", ConditionParam.CompareOption.GREATER_EQUAL))).thenReturn(result);

        List<String> options = Arrays.asList("-p", "-m", "-s", "-o", "-y", "-ge");
        List<String> params = Arrays.asList("phoneNum", "1239", "birthday", "2019");
        Command command = CommandFactory.buildCommand(CommandFactory.CMD_DEL, options, params);
        List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(ResultStringFormatter.getEmployeeListToFormattedString(result, CommandFactory.CMD_DEL, 1),
                resList);

        options = Arrays.asList("-p", "-l", "-se", "-a", "-m", "-g");
        params = Arrays.asList("name", "KIM", "phoneNum", "1239");
        command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(ResultStringFormatter.getEmployeeListToFormattedString(result, CommandFactory.CMD_SCH, 1),
                resList);

        options = Arrays.asList("-p", "-m", "-s", "-o", "-y", "-ge");
        params = Arrays.asList("phoneNum", "1239", "birthday", "2019", "certi", "EX");
        command = CommandFactory.buildCommand(CommandFactory.CMD_MOD, options, params);
        resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(ResultStringFormatter.getEmployeeListToFormattedString(result, CommandFactory.CMD_MOD, 1),
                resList);
    }
}