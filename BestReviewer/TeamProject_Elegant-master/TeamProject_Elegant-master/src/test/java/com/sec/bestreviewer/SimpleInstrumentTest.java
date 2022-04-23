package com.sec.bestreviewer;

import com.sec.bestreviewer.command.Command;
import com.sec.bestreviewer.command.CommandDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleInstrumentTest {

    private static final String INPUT_FILE_NAME = "./src/test/java/com/sec/bestreviewer/simple_instrument_test_input_50.txt";

    private CommandParser commandParser;
    private CommandFactory commandFactory;
    private CommandExecutor commandExecutor;

    @BeforeEach
    void beforeEach() {
        commandParser = new CommandParser();
        commandFactory = new CommandFactory();
        commandExecutor = new CommandExecutor();

        addEmployeeData();
    }

    private void addEmployeeData() {
        CommandReader commandReader = new CommandReader(INPUT_FILE_NAME);
        List<String> input = commandReader.readFile();
        for (String line : input) {
            try {
                final List<String> result = executeCommand(line);
                //FIXME: ADD 에 대한 결과 체크를 어떻게 할 지 논의가 필요합니다.
                assertTrue(result.isEmpty());
            } catch (IllegalArgumentException exception) {
                exception.printStackTrace();
            }
        }
    }

    private List<String> executeCommand(String line) {
        CommandDTO commandDTO = commandParser.parseCommandDTO(line);
        Command command = commandFactory.buildCommand(commandDTO);
        return commandExecutor.execute(command);
    }

    @ParameterizedTest
    @CsvSource({
            "'DEL,,,,name,REVIEW BEST', 'DEL,3'",
            "'DEL,,,,cl,CL3', 'DEL,6'",
            "'DEL,,,,phoneNum,010-2642-1645', 'DEL,1'",
            "'DEL,,,,birthday,19910914', 'DEL,1'",
            "'DEL,,,,certi,ADV', 'DEL,15'",
            "'DEL,,,,name,JASON LIM', 'DEL,NONE'",
    })
    void DEL_Test_without_PrintOption(String line, String expected) {

        final List<String> actual = executeCommand(line);

        assertEquals(expected, actual.get(0));
    }

    @ParameterizedTest
    @CsvSource({
            "'DEL,-p,,,name,REVIEW BEST', 3",
            "'DEL,-p,,,cl,CL3', 5",
            "'DEL,-p,,,phoneNum,010-7135-6953', 1",
            "'DEL,-p,,,birthday,19910914', 1",
            "'DEL,-p,,,certi,ADV', 5",
            "'DEL,-p,,,name,JASON LIM', 1",
    })
    void DEL_Test_with_PrintOption(String line, int expected) {

        final List<String> actual = executeCommand(line);

        assertEquals(expected, actual.size());
    }

    @ParameterizedTest
    @CsvSource({
            "'SCH,,,,employeeNum,92830595', 'SCH,1'",
            "'SCH,,,,name,REVIEW BEST', 'SCH,3'",
            "'SCH,,,,cl,CL2', 'SCH,13'",
            "'SCH,,,,phoneNum,010-0926-4974', 'SCH,1'",
            "'SCH,,,,birthday,19970119', 'SCH,7'",
            "'SCH,,,,certi,ADV', 'SCH,15'",
    })
    void SCH_Test_without_PrintOption(String line, String expected) {

        final List<String> actual = executeCommand(line);

        assertEquals(expected, actual.get(0));
    }

    @ParameterizedTest
    @CsvSource({
            "'SCH,-p,,,employeeNum,92830595', 1",
            "'SCH,-p,,,name,REVIEW BEST', 3",
            "'SCH,-p,,,cl,CL2', 5",
            "'SCH,-p,,,phoneNum,010-0926-4974', 1",
            "'SCH,-p,,,birthday,19970119', 5",
            "'SCH,-p,,,certi,ADV', 5",
    })
    void SCH_Test_with_PrintOption(String line, int expected) {
        final List<String> actual = executeCommand(line);
        assertEquals(expected, actual.size());
    }

    @ParameterizedTest
    @CsvSource({
            "'MOD,,,,name,REVIEW BEST,name,REVIEW WORST', 'MOD,3'",
            "'MOD,,,,cl,CL3,phoneNum,010-9777-5455', 'MOD,6'",
            "'MOD,,,,phoneNum,010-6301-9019,birthday,20000906', 'MOD,1'",
            "'MOD,,,,employeeNum,95325296,phoneNum,010-9777-6055', 'MOD,1'",
            "'MOD,,,,birthday,19970119,cl,CL4', 'MOD,7'",
            "'MOD,,,,employeeNum,91284917,employeeNum,91284918', 'MOD,1'",
    })
    void MOD_Test_without_PrintOption(String line, String expected) {
        final List<String> actual = executeCommand(line);
        assertEquals(expected, actual.get(0));
    }

    @ParameterizedTest
    @CsvSource({
            "'MOD,-p,,,name,KYUMOKKIM,name,KYUMOKLEE', 1",
            "'MOD,-p,,,cl,CL3,phoneNum,010-9777-5455', 5",
            "'MOD,-p,,,phoneNum,010-9777-6055,birthday,20000906', 1",
            "'MOD,-p,,,employeeNum,91351446,phoneNum,010-9777-6055', 1",
            "'MOD,-p,,,birthday,19970119,cl,CL4', 5",
            "'MOD,-p,,,employeeNum,91284917,employeeNum,91284918', 1",
    })
    void MOD_Test_with_PrintOption(String line, int expected) {
        final List<String> actual = executeCommand(line);
        actual.forEach(item -> System.out.println(item));
        assertEquals(expected, actual.size());
    }

    @ParameterizedTest
    @CsvSource({
            "'DEL,,-f,,name,REVIEW', 'DEL,3'",
            "'MOD,,-l,,name,LIM,phoneNum,010-9777-5455', 'MOD,5'",
            "'DEL,,-m,,phoneNum,8133', 'DEL,4'",
            "'MOD,,-l,,phoneNum,6055,certi,EX', 'MOD,6'",
            "'MOD,,-y,,birthday,1990,name,Jason Mraz', 'MOD,2'",
            "'MOD,,-m,,birthday,09,cl,CL2', 'MOD,3'",
            "'MOD,,-d,,birthday,06,certi,EX', 'MOD,3'",
    })
    void Option1_Test_without_PrintOption(String line, String expected) {
        final List<String> actual = executeCommand(line);
        assertEquals(expected, actual.get(0));
    }

    @ParameterizedTest
    @CsvSource({
            "'DEL,-p,-f,,name,REVIEW', 3",
            "'MOD,-p,-l,,name,LIM,phoneNum,010-9777-5455', 5",
            "'DEL,-p,-m,,phoneNum,8133', 4",
            "'MOD,-p,-l,,phoneNum,6055,certi,EX', 5",
            "'MOD,-p,-y,,birthday,1990,name,Jason Mraz', 2",
            "'MOD,-p,-m,,birthday,09,cl,CL2', 3",
            "'MOD,-p,-d,,birthday,06,certi,EX', 3",
    })
    void Option1_Test_with_PrintOption(String line, int expected) {
        final List<String> actual = executeCommand(line);
        assertEquals(expected, actual.size());
    }

    @ParameterizedTest
    @CsvSource({
            "'SCH,,-f,-ge,name,KYUMOK', 'SCH,30'",
            "'SCH,,,-s,cl,CL3', 'SCH,30'",
            "'SCH,,-m,-se,phoneNum,9777', 'SCH,48'",
            "'SCH,,-y,-ge,birthday,1990', 'SCH,50'",
            "'SCH,,,-ge,certi,PRO', 'SCH,35'",
    })
    void Option2_Test_without_PrintOption(String line, String expected) {
        final List<String> actual = executeCommand(line);
        assertEquals(expected, actual.get(0));
    }

    @ParameterizedTest
    @CsvSource({
            "'SCH,-p,-f,-ge,name,KYUMOK', 5",
            "'SCH,-p,,-s,cl,CL3', 5",
            "'SCH,-p,-m,-se,phoneNum,9777', 5",
            "'SCH,-p,-y,-ge,birthday,1990', 5",
            "'SCH,-p,,-ge,certi,PRO', 5",
    })
    void Option2_Test_with_PrintOption(String line, int expected) {
        final List<String> actual = executeCommand(line);
        assertEquals(expected, actual.size());
    }

    @ParameterizedTest
    @CsvSource({
            "'DEL,,-l,,name,KIM,-a,,,cl,CL4', 'DEL,NONE'",
            "'DEL,,-m,,phoneNum,9777,-o,-y,,birthday,1990', 'DEL,2'",
            "'MOD,,,,cl,CL2,-a,-m,,birthday,01,name,KYUMOK LEE', 'MOD,6'",
            "'MOD,,-d,,birthday,06,-o,,,certi,PRO,birthday,19900906', 'MOD,21'",
            "'SCH,,-l,,name,KIM,-a,,,cl,CL4', 'SCH,NONE'",
            "'SCH,,-m,,phoneNum,9777,-o,-y,,birthday,1990', 'SCH,2'",
    })
    void ANDOR_Test_without_PrintOption(String line, String expected) {
        final List<String> actual = executeCommand(line);
        assertEquals(expected, actual.get(0));
    }

    @ParameterizedTest
    @CsvSource({
            "'DEL,-p,-l,,name,KIM,-a,,,cl,CL4', 1",
            "'DEL,-p,-m,,phoneNum,9777,-o,-y,,birthday,1990', 2",
            "'MOD,-p,,,cl,CL2,-a,-m,,birthday,01,name,KYUMOK LEE', 5",
            "'MOD,-p,-d,,birthday,06,-o,,,certi,PRO,birthday,19900906', 5",
            "'SCH,-p,-l,,name,KIM,-a,,,cl,CL4', 1",
            "'SCH,-p,-m,,phoneNum,9777,-o,-y,,birthday,1990', 2",
    })
    void ANDOR_Test_without_PrintOption(String line, int expected) {
        final List<String> actual = executeCommand(line);
        assertEquals(expected, actual.size());
    }
}
