package com.sec.bestreviewer;

import com.sec.bestreviewer.command.Command;
import com.sec.bestreviewer.command.CommandDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.sec.bestreviewer.CommandExecutor.MAX_RESULT_NUMBER;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandExecutorTest {

    private CommandParser commandParser;
    private CommandExecutor commandExecutor;
    private CommandFactory commandFactory;

    final List<String> employees = Arrays.asList(
            "ADD, , , ,90000000,AAA AA,CL1,010-1234-1111,19980101,ADV",
            "ADD, , , ,90000001,AAA BB,CL2,010-1234-1112,19980101,ADV",
            "ADD, , , ,90000002,AAA CC,CL2,010-1234-1113,19980103,PRO",
            "ADD, , , ,90000003,AAA DD,CL2,010-1234-1114,19980104,PRO",
            "ADD, , , ,90000004,AAA EE,CL2,010-1234-1115,19980105,EX",
            "ADD, , , ,90000005,AAA FF,CL3,010-1234-1116,19980106,EX",
            "ADD, , , ,90000006,AAA PP,CL3,010-1234-1117,19981007,EX"
    );

    @BeforeEach
    void beforeEach() {
        commandParser = new CommandParser();
        commandExecutor = new CommandExecutor();
        commandFactory = new CommandFactory();

        addEmployeesList();
    }

    void addEmployeesList() {
        for (String line : employees) {
            CommandDTO commandDTO = commandParser.parseCommandDTO(line);
            final Command command = commandFactory.buildCommand(commandDTO);
            commandExecutor.execute(command);
        }
    }

    final List<String> processCommandsAndGetResult(String query) {
        CommandDTO commandDTO = commandParser.parseCommandDTO(query);
        final Command command = commandFactory.buildCommand(commandDTO);
        return commandExecutor.execute(command);
    }

    @ParameterizedTest
    @CsvSource({
            "1,'SCH,-p, , ,phoneNum,010-1234-1111'," +
                    "'SCH,90000000,AAA AA,CL1,010-1234-1111,19980101,ADV'"
    })
    void searchCommandWithPrintOption(int resCount, String query, String estimated) {
        List<String> res = processCommandsAndGetResult(query);

        for (int i = 0; i < Math.min(resCount, MAX_RESULT_NUMBER); i++) {
            assertEquals(estimated, res.get(i));
        }
    }

    @ParameterizedTest
    @CsvSource({
            "1,'SCH, , , ,phoneNum,010-1234-1111','SCH,1'"
    })
    void testSearchCommandWithOutPrintOption(int resCount, String query, String estimated) {
        List<String> res = processCommandsAndGetResult(query);

        for (int i = 0; i < Math.min(resCount, MAX_RESULT_NUMBER); i++) {
            assertEquals(estimated, res.get(i));
        }
    }

    @ParameterizedTest
    @CsvSource({
            "1,'SCH,-p, , ,phoneNum,010-1234-9999','SCH,NONE'",
            "1,'SCH, , , ,phoneNum,010-1234-9999','SCH,NONE'"
    })
    void testSearchCommand_NoneResult(int resCount, String query, String estimated) {
        List<String> res = processCommandsAndGetResult(query);

        for (int i = 0; i < Math.min(resCount, MAX_RESULT_NUMBER); i++) {
            assertEquals(estimated, res.get(i));
        }
    }

    @ParameterizedTest
    @CsvSource({
            "1,'DEL,-p, , ,phoneNum,010-1234-1111'," +
                    "'DEL,90000000,AAA AA,CL1,010-1234-1111,19980101,ADV'"
    })
    void TestDeleteCommandWithPrintOption(int resCount, String query, String estimated) {
        List<String> res = processCommandsAndGetResult(query);

        for (int i = 0; i < Math.min(resCount, MAX_RESULT_NUMBER); i++) {
            assertEquals(estimated, res.get(i));
        }
    }

    @ParameterizedTest
    @CsvSource({
            "1,'DEL, , , ,phoneNum,010-1234-1111','DEL,1'"
    })
    void testDeleteCommandWithOutPrintOption(int resCount, String query, String estimated) {
        List<String> res = processCommandsAndGetResult(query);

        for (int i = 0; i < Math.min(resCount, MAX_RESULT_NUMBER); i++) {
            assertEquals(estimated, res.get(i));
        }
    }

    @ParameterizedTest
    @CsvSource({
            "1,'DEL,-p, , ,phoneNum,010-1234-9999','DEL,NONE'",
            "1,'DEL, , , ,phoneNum,010-1234-9999','DEL,NONE'"
    })
    void testDeleteCommand_NoneResult(int resCount, String query, String estimated) {
        List<String> res = processCommandsAndGetResult(query);

        for (int i = 0; i < Math.min(resCount, MAX_RESULT_NUMBER); i++) {
            assertEquals(estimated, res.get(i));
        }
    }

    @ParameterizedTest
    @CsvSource({
            "1,'MOD,-p, , ,phoneNum,010-1234-1111,phoneNum,010-1234-9999'," +
                    "'MOD,90000000,AAA AA,CL1,010-1234-1111,19980101,ADV'"
    })
    void TestModifyCommandWithPrintOption(int resCount, String query, String estimated) {
        List<String> res = processCommandsAndGetResult(query);

        for (int i = 0; i < Math.min(resCount, MAX_RESULT_NUMBER); i++) {
            assertEquals(estimated, res.get(i));
        }
    }

    @ParameterizedTest
    @CsvSource({
            "1,'MOD, , , ,phoneNum,010-1234-1111,phoneNum,010-1234-9999','MOD,1'"
    })
    void testModifyCommandWithOutPrintOption(int resCount, String query, String estimated) {
        List<String> res = processCommandsAndGetResult(query);

        for (int i = 0; i < Math.min(resCount, MAX_RESULT_NUMBER); i++) {
            assertEquals(estimated, res.get(i));
        }
    }

    @ParameterizedTest
    @CsvSource({
            "1,'MOD,-p, , ,phoneNum,010-1234-0000,phoneNum,010-1234-9999','MOD,NONE'",
            "1,'MOD, , , ,phoneNum,010-1234-0000,phoneNum,010-1234-9999','MOD,NONE'"
    })
    void testModifyCommand_NoneResult(int resCount, String query, String estimated) {
        List<String> res = processCommandsAndGetResult(query);

        for (int i = 0; i < Math.min(resCount, MAX_RESULT_NUMBER); i++) {
            assertEquals(estimated, res.get(i));
        }
    }

    @ParameterizedTest
    @CsvSource({
            "1,'MOD,-p, , ,cl,CL3,-o,-m, ,certi,EX,name,MMM MM', " +
                    "'MOD,90000004,AAA EE,CL2,010-1234-1115,19980105,EX'",
            "1,'MOD,-p, , ,cl,CL3,-a,-m, ,certi,EX,name,MMM MM', " +
                    "'MOD,90000005,AAA FF,CL3,010-1234-1116,19980106,EX'",
    })
    void testModifyAndOrCommandWithPrintOption(int resCount, String query, String estimated) {
        List<String> res = processCommandsAndGetResult(query);

        for (int i = 0; i < Math.min(resCount, MAX_RESULT_NUMBER); i++) {
            assertEquals(estimated, res.get(i));
        }
    }

    @ParameterizedTest
    @CsvSource({
            "'CNT, , , , , ','CNT,7'"
    })
    void testCountCommand_NoneResult(String query, String estimated) {
        List<String> expected = Collections.singletonList(estimated);
        List<String> res = processCommandsAndGetResult(query);
        assertEquals(expected, res);
    }
}
