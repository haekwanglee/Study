package com.sec.bestreviewer;

import com.sec.bestreviewer.command.Command;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.store.Injection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SecondOptionTest {
    private final EmployeeStore employeeStore = Injection.provideEmployeeStore();
    private CommandExecutor executor;

    @BeforeEach
    public void setupEmployeeStore() {
        CommandExecutorTestUtil.addEmployees(employeeStore);
        executor = new CommandExecutor(employeeStore);
    }

    @ParameterizedTest
    @CsvSource({"-f, name, UAFBOWU", "-l, name, HONG",
            "-m, phoneNum, 9054", "-l, phoneNum, 6560",
            "-y, birthday, 1978", "-m, birthday, 08", "-d, birthday, 25"
    })
    void testSearchCommandWithSecondOption(String option, String field, String value) {
        String expected = CommandFactory.CMD_SCH + "," + CommandExecutorTestUtil.employees[0];

        List<String> options = Arrays.asList("-p", option);
        List<String> params = Arrays.asList(field, value);
        Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        List<String> resList = executor.execute(command);
        assertEquals(1, resList.size());
        assertEquals(expected, resList.get(0));
    }

    @ParameterizedTest
    @CsvSource({"-f, name, UAFBOWU", "-l, name, HONG",
            "-m, phoneNum, 9054", "-l, phoneNum, 6560",
            "-y, birthday, 1978", "-m, birthday, 08", "-d, birthday, 25"
    })
    void testDeleteCommandWithSecondOption(String option, String field, String value) {
        String expected = CommandFactory.CMD_DEL + "," + CommandExecutorTestUtil.employees[0];

        List<String> options = Arrays.asList("-p", option);
        List<String> params = Arrays.asList(field, value);
        Command command = CommandFactory.buildCommand(CommandFactory.CMD_DEL, options, params);
        List<String> resList = executor.execute(command);
        assertEquals(1, resList.size());
        assertEquals(expected, resList.get(0));
    }

    @ParameterizedTest
    @CsvSource({"-f, name, UAFBOWU", "-l, name, HONG",
            "-m, phoneNum, 9054", "-l, phoneNum, 6560",
            "-y, birthday, 1978", "-m, birthday, 08", "-d, birthday, 25"
    })
    void testModifyCommandWithSecondOption(String option, String field, String value) {
        String expected = "," + CommandExecutorTestUtil.employees[0];

        List<String> options = Arrays.asList("-p", option);
        List<String> params = Arrays.asList(field, value, EmployeeStore.FIELD_NAME, "KILDONG HONG");
        Command command = CommandFactory.buildCommand(CommandFactory.CMD_MOD, options, params);
        List<String> resList = executor.execute(command);
        assertEquals(1, resList.size());
        assertEquals(CommandFactory.CMD_MOD + expected, resList.get(0));

        resList = CommandExecutorTestUtil.getResultData(executor, Arrays.asList(EmployeeStore.FIELD_NAME, "KILDONG HONG"));
        assertEquals(CommandFactory.CMD_SCH +
                expected.replace("UAFBOWU HONG", "KILDONG HONG"), resList.get(0));
    }
}
