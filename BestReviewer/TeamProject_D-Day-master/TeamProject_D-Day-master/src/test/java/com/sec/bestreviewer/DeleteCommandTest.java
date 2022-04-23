package com.sec.bestreviewer;

import com.sec.bestreviewer.command.Command;
import com.sec.bestreviewer.command.DeleteCommand;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.util.OptionParser;
import com.sec.bestreviewer.util.Options;
import com.sec.bestreviewer.util.Pair;
import com.sec.bestreviewer.util.ResultStringFormatter;
import com.sec.bestreviewer.util.arguments.CommandArguments;
import com.sec.bestreviewer.util.arguments.CommandOptionSeparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;
import java.util.stream.Collectors;

import static com.sec.bestreviewer.CommandExecutor.MAX_RESULT_NUMBER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class DeleteCommandTest {
    private EmployeeStore employeeStore;
    private CommandArguments commandArguments;

    @BeforeEach
    void createMockEmployeeStore() {
        employeeStore = mock(EmployeeStore.class);
        commandArguments = mock(CommandArguments.class);
    }

    @DisplayName("기본 delete 테스트 (출력옵션 없음)")
    @ParameterizedTest
    @ValueSource(ints={0, 1, 100})
    void deleteWithoutPrintOption(int employeeCount) {
        final List<Employee> employeeList = getEmployees(employeeCount);
        final List<OptionParser> optionList = getOptionList(Options.EMPTY_OPTION, Options.EMPTY_OPTION);
        final List<String> resList = getDeleteResult(employeeList, optionList);
        verify(employeeStore, times(1)).delete(anyList());
        assertEquals(CommandFactory.CMD_DEL + "," + ((employeeCount>0)?employeeCount:"NONE"), resList.get(0));
    }

    private List<String> getDeleteResult(List<Employee> employeeList, List<OptionParser> optionList) {
        List<Pair> conditionParisParamList = getConditionParamList("name", "SEO KFI", "", "");
        when(commandArguments.getOptionList()).thenReturn(optionList);
        when(commandArguments.getConditionParamList()).thenReturn(conditionParisParamList);
        for(Employee employee : employeeList) {
            when(employeeStore.search("name", employee.getName(), Options.EMPTY_OPTION, Options.EMPTY_OPTION)).thenReturn(employeeList);
        }

        DeleteCommand deleteCommand = new DeleteCommand(commandArguments);
        return deleteCommand.execute(employeeStore);
    }

    private  List<Pair> getConditionParamList(String firstColumn, String firstValue, String secondColumn, String secondValue) {
        List<Pair> conditionParisParamList = new ArrayList<>();
        conditionParisParamList.add(Pair.create(firstColumn, firstValue));
        if(!secondColumn.isEmpty()) {
            conditionParisParamList.add(Pair.create(secondColumn, secondValue));
        }
        return conditionParisParamList;
    }

    @DisplayName("기본 delete 테스트 (출력옵션 있음)")
    @ParameterizedTest
    @ValueSource(ints={0, 1, 100})
    void deleteWithPrintOption(int employeeCount) {
        final List<Employee> employeeList = getEmployees(employeeCount);
        final List<OptionParser> optionList = getOptionList("-p", Options.EMPTY_OPTION);
        final List<String> resList = getDeleteResult(employeeList, optionList);

        if(employeeCount == 0) {
            assertEquals(CommandFactory.CMD_DEL + "," + "NONE", resList.get(0));
        }
        for (int i = 0; i < Math.min(employeeCount, MAX_RESULT_NUMBER); i++) {
            assertEquals(ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_DEL, employeeList.get(i)),
                    resList.get(i));
        }
    }

    @DisplayName("option2가 포함된 delete 테스트 (출력옵션 없음)")
    @ParameterizedTest
    @CsvSource({
            "-f, name, SEO",
            "-l, name, KFI",
            "-m, phoneNum, 1234",
            "-l, phoneNum, 5678",
            "-y, birthday, 2019",
            "-m, birthday, 01",
            "-d, birthday, 01"
    })
    void deleteWithoutPrintOptionAndSecondParam(String option, String column, String value) {
        final List<Employee> employeeList = getEmployees(4); // move to init
        when(employeeStore.search(column, value, Options.EMPTY_OPTION, option)).thenReturn(employeeList);

        final List<OptionParser> optionList = getOptionList(Options.EMPTY_OPTION, option);
        final List<Pair> conditionParisParamList = getConditionParamList(column, value, "", "");
        when(commandArguments.getOptionList()).thenReturn(optionList);
        when(commandArguments.getConditionParamList()).thenReturn(conditionParisParamList);

        DeleteCommand deleteCommand = new DeleteCommand(commandArguments);
        final List<String> resList = deleteCommand.execute(employeeStore);

        verify(employeeStore, times(1)).delete(anyList());
        assertEquals(1, resList.size());
        assertEquals(CommandFactory.CMD_DEL + ",4", resList.get(0));
    }

    @DisplayName("option2가 포함된 delete 테스트 (출력옵션 있음)")
    @ParameterizedTest
    @CsvSource({
            "-f, name, SEO",
            "-l, name, KFI",
            "-m, phoneNum, 1234",
            "-l, phoneNum, 5678",
            "-y, birthday, 2019",
            "-m, birthday, 01",
            "-d, birthday, 01"
    })
    void deleteWithPrintOptionAndSecondParam(String option, String column, String value) {
        deleteWithPrintOption(4, option, column, value);
        deleteWithPrintOption(0, option, column, value);
        verify(employeeStore, times(2)).delete(anyList());
    }

    private void deleteWithPrintOption(int count, String secondOption, String column, String value) {
        final List<Employee> employeeList = getEmployees(count); // move to init
        for(Employee employee : employeeList) {
            when(employeeStore.search(column, value, Options.EMPTY_OPTION, secondOption)).thenReturn(employeeList);
        }
        final List<OptionParser> optionList = getOptionList("-p", secondOption);
        final List<Pair> conditionParisParamList = getConditionParamList(column, value, "", "");
        when(commandArguments.getOptionList()).thenReturn(optionList);
        when(commandArguments.getConditionParamList()).thenReturn(conditionParisParamList);

        DeleteCommand deleteCommand = new DeleteCommand(commandArguments);
        final List<String> resList = deleteCommand.execute(employeeStore);

        for (int i = 0; i < Math.min(count, MAX_RESULT_NUMBER); i++) {
            assertEquals(ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_DEL, employeeList.get(i)),
                    resList.get(i));
        }
    }

    private List<Employee> getEmployees(int count) {
        final List<Employee> employeeList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            final String employeeNumber = Integer.toString(90000000 + i);
            employeeList.add(
                    new Employee(employeeNumber, "SEO KFI", "CL1", "010-1234-5678", "20190101", "ADV"));
        }
        return employeeList;
    }

    private List<OptionParser> getOptionList(String first, String second) {
        List<String> options = new ArrayList<>();
        options.add(first);
        options.add(second);
        options.add(Options.EMPTY_OPTION);
        return Collections.singletonList(new OptionParser(options));
    }

    @DisplayName("결과값 정렬을 확인하기위한 delete 테스트 (출력옵션 있음)")
    @ParameterizedTest
    @ValueSource(ints={0, 1, 10})
    void testDeleteCommandReturnsSortedEmployeeList(int employeeCount) {
        final List<Employee> employeeList = getEmployees(employeeCount);
        final List<Employee> reversedEmployeeList = employeeList.stream()
                .sorted(Comparator.comparing(Employee::getEmployeeNumber).reversed())
                .collect(Collectors.toList());
        when(employeeStore.search("name", "KYUMOK KIM", Options.EMPTY_OPTION, Options.EMPTY_OPTION)).thenReturn(reversedEmployeeList);

        final List<String> options = Arrays.asList("-p", " ", " ");
        final List<String> params = Arrays.asList("name", "KYUMOK KIM");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_DEL, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);

        verify(employeeStore, times(1)).delete(anyList());
        for (int i = 0; i < Math.min(employeeCount, MAX_RESULT_NUMBER); i++) {
            assertEquals(
                    ResultStringFormatter.getEmployeeToFormattedString(CommandFactory.CMD_DEL, employeeList.get(i)),
                    resList.get(i));
        }
    }

    @DisplayName("and 연산이 포함된 delete 테스트 (출력옵션 없음)")
    @Test
    void deleteWithAndOperation() {
        final List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("1", "SEO KFI", "CL1", "010-1234-5678", "20190101", "ADV"));
        employeeList.add(new Employee("4", "SEO TAIJI", "CL3", "010-1234-0988", "20190101", "PRO"));

        when(employeeStore.search("name", "SEO KFI", Options.EMPTY_OPTION, Options.EMPTY_OPTION)).thenReturn(Collections.singletonList(employeeList.get(0)));
        when(employeeStore.search("careerLevel", "CL1", Options.EMPTY_OPTION, Options.EMPTY_OPTION)).thenReturn(Collections.singletonList(employeeList.get(0)));

        List<OptionParser> optionList = new ArrayList<>();
        optionList.add(getOptionParser(Options.EMPTY_OPTION, Options.EMPTY_OPTION));
        optionList.add(getOptionParser(Options.EMPTY_OPTION, Options.EMPTY_OPTION));
        final List<Pair> conditionParisParamList = getConditionParamList("name", "SEO KFI", "careerLevel", "CL1");
        when(commandArguments.getOptionList()).thenReturn(optionList);
        when(commandArguments.getConditionParamList()).thenReturn(conditionParisParamList);
        when(commandArguments.getCommandMode()).thenReturn(CommandOptionSeparator.AND_COMMAND);

        DeleteCommand deleteCommand = new DeleteCommand(commandArguments);
        final List<String> resList = deleteCommand.execute(employeeStore);

        verify(employeeStore, times(1)).delete(anyList());
        assertEquals(1, resList.size());
        assertEquals(CommandFactory.CMD_DEL + ",1", resList.get(0));
    }

    OptionParser getOptionParser(String first, String second) {
        List<String> options = new ArrayList<>();
        options.add(first);
        options.add(second);
        options.add(Options.EMPTY_OPTION);
        return new OptionParser(options);
    }

    @DisplayName("or 연산이 포함된 delete 테스트 (출력옵션 없음)")
    @Test
    void deleteWithOrOperation() {
        final List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("1", "SEO KFI", "CL1", "010-1234-5678", "20190101", "ADV"));
        employeeList.add(new Employee("4", "SEO TAIJI", "CL3", "010-1234-0988", "20190101", "PRO"));

        when(employeeStore.search("name", "SEO KFI", Options.EMPTY_OPTION, Options.EMPTY_OPTION)).thenReturn(Collections.singletonList(employeeList.get(0)));
        when(employeeStore.search("careerLevel", "CL3", Options.EMPTY_OPTION, Options.EMPTY_OPTION)).thenReturn(Collections.singletonList(employeeList.get(1)));

        List<OptionParser> optionList = new ArrayList<>();
        optionList.add(getOptionParser(Options.EMPTY_OPTION, Options.EMPTY_OPTION));
        optionList.add(getOptionParser(Options.EMPTY_OPTION, Options.EMPTY_OPTION));
        final List<Pair> conditionParisParamList = getConditionParamList("name", "SEO KFI", "careerLevel", "CL3");
        when(commandArguments.getOptionList()).thenReturn(optionList);
        when(commandArguments.getConditionParamList()).thenReturn(conditionParisParamList);
        when(commandArguments.getCommandMode()).thenReturn(CommandOptionSeparator.OR_COMMAND);

        DeleteCommand deleteCommand = new DeleteCommand(commandArguments);
        final List<String> resList = deleteCommand.execute(employeeStore);

        verify(employeeStore, times(1)).delete(anyList());
        assertEquals(1, resList.size());
        assertEquals(CommandFactory.CMD_DEL + ",2", resList.get(0));
    }
}
