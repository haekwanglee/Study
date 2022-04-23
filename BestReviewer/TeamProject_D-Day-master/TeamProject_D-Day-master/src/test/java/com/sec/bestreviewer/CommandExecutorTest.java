package com.sec.bestreviewer;

import com.sec.bestreviewer.command.Command;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.util.ResultStringFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;

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
        final List<String> options = Arrays.asList("-p", " ", " ");
        final List<String> params = Arrays.asList("18050301", "KYUMOK KIM", "CL2", "010-9777-6055", "19980906", "ADV");
        final Command command = CommandFactory.buildCommand("ADD", options, params);
        final List<String> res = (new CommandExecutor()).execute(command);
        assertNotNull(res);
    }

    @Test
    void testAddCommandReturnsEmptyList() {
        final List<String> options = Collections.emptyList();
        final List<String> params = Arrays.asList("18050301", "KYUMOK KIM", "CL2", "010-9777-6055", "19980906", "ADV");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_ADD, options, params);
        final List<String> res = (new CommandExecutor()).execute(command);
        assertNotNull(res);
        assertEquals(0, res.size());
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
    void testSearchCommandWithPrintOption() {
        searchCommandWithPrintOption(1);
        searchCommandWithPrintOption(6);
    }

    void searchCommandWithPrintOption(int count) {
        final List<Employee> employeeList = getEmployees(count);
        when(employeeStore.search("name", "KYUMOK KIM", " ", " ")).thenReturn(employeeList);

        final List<String> options = Arrays.asList("-p", " ", " ");
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
    void testSearchCommandWithPrintOption_NoneResult() {
        final List<Employee> employeeList = getEmployees(0);
        when(employeeStore.search("name", "KYUMOK KIM")).thenReturn(employeeList);

        final List<String> options = Arrays.asList("-p", " ", " ");
        final List<String> params = Arrays.asList("name", "KYUMOK KIM");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(CommandFactory.CMD_SCH + ",NONE", resList.get(0));
    }

    @Test
    void testSearchCommandWithOutPrintOption() {
        final int deletedCount = 10;
        final List<Employee> employeeList = getEmployees(deletedCount);
        when(employeeStore.search("name", "KYUMOK KIM", " ", " ")).thenReturn(employeeList);

        final List<String> options = Collections.emptyList();
        final List<String> params = Arrays.asList("name", "KYUMOK KIM");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(CommandFactory.CMD_SCH + "," + deletedCount, resList.get(0));
    }

    @Test
    void testSearchCommandWithOutPrintOption_NoneResult() {
        final List<Employee> employeeList = getEmployees(0);
        when(employeeStore.search("name", "KYUMOK KIM")).thenReturn(employeeList);

        final List<String> options = Collections.emptyList();
        final List<String> params = Arrays.asList("name", "KYUMOK KIM");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(CommandFactory.CMD_SCH + ",NONE", resList.get(0));
    }

    @ParameterizedTest
    @CsvSource({"7, 'KYUMOK', 7"})
    void testSearchCommandWithFirstNameOption(int totalEmployees, String firstName, int expectedNumOfFirstName) {
        final List<Employee> employeeList = getEmployeesWithFirstName(totalEmployees, firstName, expectedNumOfFirstName);
        //when(employeeStore.search("name", "KYUMOK", "-eq", "-f")).thenReturn(employeeList);
        when(employeeStore.search("name", "KYUMOK", " ", "-f")).thenReturn(employeeList);
        final List options = Arrays.asList(" ","-f"," ");

        final List<String> params = Arrays.asList("name", "KYUMOK");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(CommandFactory.CMD_SCH + "," + expectedNumOfFirstName , resList.get(0));
    }

    @Test
    void testSearchCommandWithFirstNameOption_NoneResult() {
        final List<Employee> employeeList = getEmployees(0);
        when(employeeStore.search("name", "KYUMOK", " ", "-f")).thenReturn(employeeList);

        final List options = Arrays.asList(" ", "-f", " ");

        final List<String> params = Arrays.asList("name", "KYUMOK");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(CommandFactory.CMD_SCH + ",NONE", resList.get(0));
    }

    @ParameterizedTest
    @CsvSource({"7, 'KIM', 7",
                "5, 'PARK', 5",
                "3, 'YUN', 3",
                "1, 'LEE', 1"})
    void testSearchCommandWithLastNameOption(int totalEmployees, String lastName, int expectedNumOfFirstName) {
        final List<Employee> employeeList = getEmployeesWithLastName(totalEmployees, lastName, expectedNumOfFirstName);
        when(employeeStore.search("name", "KIM", " ", "-l")).thenReturn(employeeList);
        final List options = Arrays.asList(" ", "-l", " ");

        final List<String> params = Arrays.asList("name", "KIM");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(CommandFactory.CMD_SCH + "," + expectedNumOfFirstName, resList.get(0));
    }

    @Test
    void testSearchCommandWithLastNameOption_NoneResult() {
        final List<Employee> employeeList = getEmployees(0);
        when(employeeStore.search("name", "KIM", " ", "-l")).thenReturn(employeeList);

        final List options = Arrays.asList(" ", "-l", " ");
        final List<String> params = Arrays.asList("name", "KIM");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(CommandFactory.CMD_SCH + ",NONE", resList.get(0));
    }


    private List<Employee> getEmployeesWithFirstName(int totalEmployees, String firstName, int numOfFirstName) {
        final List<Employee> employeeList = new ArrayList<>();
        if (totalEmployees < 1)
            return employeeList;

        boolean[] totalEmployeesIndex = getRandomIndex(totalEmployees, numOfFirstName);
        for (int i = 0; i < totalEmployees; i++) {
            final String employeeNumber = Integer.toString(90_000000 + (i * 1_000000));
            String fullName = totalEmployeesIndex[i] ? firstName + " KIM" : "NO" + firstName + " KIM";
            employeeList.add(
                    new Employee(employeeNumber, fullName, "CL1", "010-1234-5678", "20190101", "ADV"));
        }
        return employeeList;
    }

    private List<Employee> getEmployeesWithLastName(int totalEmployees, String lastName, int numOfLastName) {
        final List<Employee> employeeList = new ArrayList<>();
        if (totalEmployees < 1)
            return employeeList;

        boolean[] totalEmployeesIndex = getRandomIndex(totalEmployees, numOfLastName);
        for (int i = 0; i < totalEmployees; i++) {
            final String employeeNumber = Integer.toString(90_000000 + (i * 1_000000));
            String fullName = totalEmployeesIndex[i] ? "YUNSUN " + lastName : "YUNSUN " + "NO" + lastName;
            employeeList.add(
                    new Employee(employeeNumber, fullName, "CL1", "010-1234-5678", "20190101", "ADV"));
        }
        return employeeList;
    }

    private boolean[] getRandomIndex(int totalEmployees, int numOfFirstName) {
        int[] randomFirstNameIndex = new int[numOfFirstName];
        Random r = new Random();
        for (int i = 0; i <= numOfFirstName - 1; i++) {
            randomFirstNameIndex[i] = r.nextInt(totalEmployees) + 1;
            for (int j = 0; j < i; j++) {
                if (randomFirstNameIndex[i] == randomFirstNameIndex[j]) {
                    i--;
                }
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int value : randomFirstNameIndex) {
            list.add(value);
        }
        Collections.sort(list);

        boolean[] totalEmployeesIndex = new boolean[totalEmployees];
        for(int selectedIndex : list) {
            totalEmployeesIndex[selectedIndex-1] = true;
        }
        return totalEmployeesIndex;
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 20})
    void testSearchCommandWithMiddleNumberOption(int expectedNumOfEmployee) {
        final List<Employee> employeeList = getEmployees(expectedNumOfEmployee);
        when(employeeStore.search("phoneNum", "1234", " ", "-m")).thenReturn(employeeList);
        final List options = Arrays.asList(" ", "-m", " ");
        final List<String> params = Arrays.asList("phoneNum", "1234");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(CommandFactory.CMD_SCH + "," + expectedNumOfEmployee, resList.get(0));
    }

    @Test
    void testSearchCommandWithMiddleNumberOption_NoneResult() {
        final List<Employee> employeeList = getEmployees(0);
        when(employeeStore.search("phoneNum", "1234", " ", "-l")).thenReturn(employeeList);
        final List options = Arrays.asList(" ", "-l", " ");
        final List<String> params = Arrays.asList("phoneNum", "1234");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(CommandFactory.CMD_SCH + ",NONE", resList.get(0));
    }


    @DisplayName("birthday 추가 옵션 검색을 위한 테스트")
    @ParameterizedTest
    @CsvSource({
            "-y, birthday, 2019",
            "-m, birthday, 01",
            "-d, birthday, 01"
    })
    void testSearchCommandWithoutPrintOption_birthdayOption(String secondOption, String column, String value) {
        int count = 3;
        final List<Employee> employeeList = getEmployees(count);
        when(employeeStore.search(column, value, " ", secondOption)).thenReturn(employeeList);

        final List<String> options = getOptionList(" ", secondOption);
        final List<String> params = Arrays.asList(column, value);
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);
        assertEquals(CommandFactory.CMD_SCH + "," + count, resList.get(0));
    }

    private List<String> getOptionList(String first, String second) {
        List<String> options = new ArrayList<>();
        options.add(first);
        options.add(second);
        options.add(" ");
        return options;
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
    void testModifyCommandReturnsEmptyList() {
        final List<Employee> employeeList = Collections.emptyList();
        final List<String> options = Collections.emptyList();
        final List<String> params = Arrays.asList("name", "KYUMOK KIM", "name", "Best Kim");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_MOD, options, params);
        when(employeeStore.search(any(), any(), any(), any())).thenReturn(Collections.emptyList());
        when(employeeStore.modify("name", "Best Kim", employeeList)).thenReturn(Collections.emptyList());
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);

        assertEquals(CommandFactory.CMD_MOD + ",NONE", resList.get(0));
    }

    @Test
    void testModifyCommandWithAndReturnsOneEmployeeItemList() {
        Employee employee_CL1_PRO = new Employee("90001234", "SEO KFI", "CL1", "010-1234-5678", "20190101", "PRO");
        Employee employee_CL2_ADV = new Employee("90001235", "SEO KFI", "CL2", "010-1234-5678", "20190101", "ADV");
        Employee employee_CL2_PRO = new Employee("90001236", "SEO KFI", "CL2", "010-1234-5678", "20190101", "PRO");

        String input = "MOD,-p, , ,cl,cl2,-a, , ,certi,PRO,certi,EX";
        Command command = prepereCommandWithModify(input);

        when(employeeStore.search("cl", "cl2", " ", " ")).thenReturn(Arrays.asList(employee_CL2_ADV, employee_CL2_PRO));
        when(employeeStore.search("certi", "PRO", " ", " ")).thenReturn(Arrays.asList(employee_CL1_PRO, employee_CL2_PRO));

        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);

        assertEquals(1, resList.size());
    }

    @Test
    void testModifyCommandWithOrReturnsThreeEmployeeItemList() {
        Employee employee_CL1_PRO = new Employee("90001234", "SEO KFI", "CL1", "010-1234-5678", "20190101", "PRO");
        Employee employee_CL2_PRO = new Employee("90001235", "SEO KFI", "CL2", "010-1234-5678", "20190101", "PRO");
        Employee employee_CL3_PRO = new Employee("90001236", "SEO KFI", "CL3", "010-1234-5678", "20190101", "PRO");

        String input = "MOD,-p, , ,cl,cl2,-o, , ,certi,PRO,certi,EX";
        Command command = prepereCommandWithModify(input);

        when(employeeStore.search("cl", "cl2", " ", " ")).thenReturn(Arrays.asList(employee_CL1_PRO));
        when(employeeStore.search("certi", "PRO", " ", " ")).thenReturn(Arrays.asList(employee_CL1_PRO, employee_CL2_PRO, employee_CL3_PRO));

        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);

        assertEquals(3, resList.size());
    }

    private Command prepereCommandWithModify(String input) {
        CommandParser commandParser = new CommandParser();
        TokenGroup token = commandParser.parse(input);
        return CommandFactory.buildCommand(CommandFactory.CMD_MOD, token.options, token.params);
    }
}