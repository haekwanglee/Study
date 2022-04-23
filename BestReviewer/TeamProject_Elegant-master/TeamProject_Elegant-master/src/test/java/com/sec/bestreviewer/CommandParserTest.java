package com.sec.bestreviewer;

import com.sec.bestreviewer.command.CommandDTO;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandParserTest {

    @ParameterizedTest
    @CsvSource({
            "'ADD, , , ,18050301,KYUMOK KIM,CL2,010-9777-6055,19980906'",
            "'DEL,-p, , ,name,KYUMOKKIM',",
            "'DEL, , , ,cl,CL3',",
            "'SCH,-p, , ,phoneNum,010-2742-2901',",
            "'SCH, , , ,birthday,19810630',",
            "'MOD,-p, , ,name,KYUMOKKIM,name,KYUMOKLEE',",
            "'MOD, , , ,employeeNum,91351446,phoneNum,010-9777-6055',"

    })
    void testParsing(String input) {
        CommandParser commandParser = new CommandParser();
        CommandDTO commandDTO = commandParser.parseCommandDTO(input);

        List<String> tokens = Arrays.asList(input.split(","));
        assertControllerOptions(commandDTO, tokens);

        List<String> employeeDataList = commandDTO.getEmployeeDataList();
        assertEquals(getExpectedEmployeeDataList(tokens), employeeDataList);
    }

    private List<String> getExpectedEmployeeDataList(List<String> tokens) {
        return tokens.subList(2, tokens.size());
    }

    private void assertControllerOptions(CommandDTO commandDTO, List<String> tokens) {
        final int TYPE = 0;
        final int PRINT_OPT = 1;

        assertAll(() -> assertEquals(tokens.get(TYPE), commandDTO.getType().label),
                () -> assertEquals(tokens.get(PRINT_OPT), commandDTO.getPrintOption()));
    }
}
