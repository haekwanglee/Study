package com.sec.bestreviewer;

import com.sec.bestreviewer.util.OptionParser;
import com.sec.bestreviewer.util.Options;
import com.sec.bestreviewer.util.arguments.CommandArguments;
import com.sec.bestreviewer.util.arguments.CommandArgumentsImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class CommandParserTest {
    CommandParser commandParser = new CommandParser();
    CommandArguments commandArguments = new CommandArgumentsImpl();

    @DisplayName("옵션이 없는 단순한 Command를 파싱하는지 확인")
    @Test
    void withoutAnyOptionsWithSimpleCommandTest() {
        String line = "ADD, , , ,08951033,QDJPTOJ KIM,CL3,010-3240-5443,19800308";

        TokenGroup tokens = commandParser.parse(line);

        assertEquals("ADD", tokens.getType());

        String[] options = new String[]{" ", " ", " "};
        assertArrayEquals(options, tokens.getOptions().toArray());

        String[] params = {"08951033", "QDJPTOJ KIM", "CL3", "010-3240-5443", "19800308"};
        assertArrayEquals(params, tokens.getParams().toArray());
    }

    @DisplayName("각각의 옵션이 있는 단순한 검색, 삭제 Command를 파싱하는지 확인")
    @ParameterizedTest
    @CsvSource({
            "'SCH,-p, , ,phoneNum,010-2742-2901', SCH, '-p, , ', phoneNum, 010-2742-2901",
            "'DEL,-p,-f, ,name,KYUMOK', DEL, '-p,-f, ', name, KYUMOK",
            "'DEL, ,-l, ,name,KYUMOK', DEL, ' ,-l, ', name, KYUMOK",
            "'DEL,-p,-y, ,birthday,1990', DEL, '-p,-y, ', birthday, 1990",
            "'SCH, ,-m, ,birthday,09', SCH, ' ,-m, ', birthday, 09",
            "'SCH, ,-d, ,birthday,06', SCH, ' ,-d, ', birthday, 06",
            "'SCH,-p,-f,-ge,name,KYUMOK', SCH, '-p,-f,-ge', name, KYUMOK",
    })
    void withPrintOptionWithSimpleCommandForSearchOrDeleteTest(String line, String resultType, String option, String sourceColumn, String sourceValue) {
        TokenGroup tokens = commandParser.parse(line);

        assertEquals(resultType, tokens.getType());

        String[] options = option.split(",");
        assertArrayEquals(options, tokens.getOptions().toArray());

        String[] params = {sourceColumn, sourceValue};
        assertArrayEquals(params, tokens.getParams().toArray());
    }

    @DisplayName("각각의 옵션이 있는 단순한 변경 Command를 파싱하는지 확인")
    @ParameterizedTest
    @CsvSource({
            "'MOD,-p, , ,phoneNum,010-2742-2901,certi,EX', MOD, '-p, , ', phoneNum, 010-2742-2901,certi,EX",
            "'MOD,-p,-f, ,name,KYUMOK,phoneNum,010-2742-2901', MOD, '-p,-f, ', name, KYUMOK, phoneNum, 010-2742-2901",
            "'MOD, ,-l, ,phoneNum,2901,certi,PRO', MOD, ' ,-l, ', phoneNum, 2901, certi, PRO",
            "'MOD,-p,-y, ,birthday,1990,name,KYUMOK LEE', MOD, '-p,-y, ', birthday, 1990,name,KYUMOK LEE",
    })
    void withPrintOptionWithSimpleCommandForModifyTest(String line, String resultType, String option, String sourceColumn, String sourceValue,
                                                       String targetColumn, String targetValue) {
        TokenGroup tokens = commandParser.parse(line);

        assertEquals(resultType, tokens.getType());

        String[] options = option.split(",");
        assertArrayEquals(options, tokens.getOptions().toArray());

        String[] params = {sourceColumn, sourceValue, targetColumn, targetValue};
        assertArrayEquals(params, tokens.getParams().toArray());
    }

    @DisplayName("성명의 성 옵션 체크 확인")
    @Test
    void withFirstNameOptionWithSimpleCommandTest() {
        String line = "DEL,-p,-f, ,name,KYUMOK";
        List<OptionParser> optionParserList = getOptionList(line);

        assertEquals(Options.FIRST_NAME_OPTION, optionParserList.get(0).getFilterOption());

        line = "DEL,-p, , ,name,KYUMOK";
        optionParserList = getOptionList(line);

        assertNotEquals(Options.FIRST_NAME_OPTION, optionParserList.get(0).getFilterOption());
    }

    @DisplayName("성명의 이름 옵션 체크 확인")
    @Test
    void withLastNameOptionWithSimpleCommandTest() {
        String line = "DEL, ,-l, ,name,KYUMOK";
        List<OptionParser> optionParserList = getOptionList(line);

        assertEquals(Options.LAST_NAME_OPTION, optionParserList.get(0).getFilterOption());

        line = "DEL,-p, , ,name,KYUMOK";
        optionParserList = getOptionList(line);

        assertNotEquals(Options.LAST_NAME_OPTION, optionParserList.get(0).getFilterOption());
    }

    @DisplayName("전화번호의 중간 자리 옵션 체크 확인")
    @Test
    void withMiddleNumberOptionWithSimpleCommandTest() {
        String line = "DEL,-p,-m, ,phoneNum,9777";
        List<OptionParser> optionParserList = getOptionList(line);

        assertEquals(Options.MIDDLE_NUMBER_OPTION, optionParserList.get(0).getFilterOption());

        line = "DEL,-p, , ,phoneNum,9777";
        optionParserList = getOptionList(line);

        assertNotEquals(Options.MIDDLE_NUMBER_OPTION, optionParserList.get(0).getFilterOption());
    }

    @DisplayName("전화번호의 뒷 자리 옵션 체크 확인")
    @Test
    void withLastNumberOptionWithSimpleCommandTest() {
        String line = "MOD, ,-l, ,phoneNum,6055,certi,EX";
        List<OptionParser> optionParserList = getOptionList(line);

        assertEquals(Options.LAST_NUMBER_OPTION, optionParserList.get(0).getFilterOption());

        line = "MOD,-p, , ,phoneNum,6055,certi,EX";
        optionParserList = getOptionList(line);

        assertNotEquals(Options.LAST_NUMBER_OPTION, optionParserList.get(0).getFilterOption());
    }

    @DisplayName("생년월일의 연도 옵션 체크 확인")
    @Test
    void withYearOptionWithSimpleCommandTest() {
        String line = "MOD,-p,-y, ,birthday,1990,name,KYUMOKLEE";
        List<OptionParser> optionParserList = getOptionList(line);

        assertEquals(Options.YEAR_OPTION, optionParserList.get(0).getFilterOption());

        line = "MOD,-p, , ,birthday,1990,name,KYUMOKLEE";
        optionParserList = getOptionList(line);

        assertNotEquals(Options.YEAR_OPTION, optionParserList.get(0).getFilterOption());
    }

    @DisplayName("생년월일의 월 옵션 체크 확인")
    @Test
    void withMonthOptionWithSimpleCommandTest() {
        String line = "MOD,-p,-m, ,birthday,09,cl,CL2";
        List<OptionParser> optionParserList = getOptionList(line);

        assertEquals(Options.MONTH_OPTION, optionParserList.get(0).getFilterOption());

        line = "MOD,-p, , ,birthday,09,cl,CL2";
        optionParserList = getOptionList(line);

        assertNotEquals(Options.MONTH_OPTION, optionParserList.get(0).getFilterOption());
    }

    @DisplayName("생년월일의 일 옵션 체크 확인")
    @Test
    void withDayOptionWithSimpleCommandTest() {
        String line = "MOD, ,-d, ,birthday,06,certi,EX";
        List<OptionParser> optionParserList = getOptionList(line);

        assertEquals(Options.DAY_OPTION, optionParserList.get(0).getFilterOption());

        line = "MOD, , , ,birthday,06,certi,EX";
        optionParserList = getOptionList(line);

        assertNotEquals(Options.DAY_OPTION, optionParserList.get(0).getFilterOption());
    }

    @DisplayName("부등호 옵션 체크 확인")
    @Test
    void withInqualityOptionWithSimpleCommandTest() {
        String line = "SCH,-p,-f,-ge,name,KYUMOK";
        List<OptionParser> optionParserList = getOptionList(line);

        assertEquals(Options.FIRST_NAME_OPTION, optionParserList.get(0).getFilterOption());
        assertEquals(Options.GRATER_EQUAL_OPTION, optionParserList.get(0).getCompareOption());

        line = "SCH,-p, ,-s,cl,CL3";
        optionParserList = getOptionList(line);

        assertEquals(Options.SMALLER_OPTION, optionParserList.get(0).getCompareOption());

        line = "SCH,-p,-m,-se,phoneNum,9777";
        optionParserList = getOptionList(line);

        assertEquals(Options.MIDDLE_NUMBER_OPTION, optionParserList.get(0).getFilterOption());
        assertEquals(Options.SMALLER_EQUAL_OPTION, optionParserList.get(0).getCompareOption());

        line = "SCH,-p,-y,-g,birthday,1990";
        optionParserList = getOptionList(line);

        assertEquals(Options.YEAR_OPTION, optionParserList.get(0).getFilterOption());
        assertEquals(Options.GRATER_OPTION, optionParserList.get(0).getCompareOption());

        line = "SCH,-p, ,-s,certi,PRO";
        optionParserList = getOptionList(line);

        assertEquals(Options.SMALLER_OPTION, optionParserList.get(0).getCompareOption());
    }

    @DisplayName("삭제 커맨드의 And 연산 확인")
    @Test
    void AndOptionTestWithDeleteCommand() {
        String line = "DEL,-p,-l, ,name,KIM,-a, , ,cl,CL4";
        List<OptionParser> optionParserList = getOptionList(line);

        assertTrue(optionParserList.get(0).isPrintOn());
        assertEquals(Options.LAST_NAME_OPTION, optionParserList.get(0).getFilterOption());

        assertNotEquals(Options.LAST_NAME_OPTION, optionParserList.get(1).getFilterOption());
    }


    @DisplayName("삭제 커맨드의 Or 연산 확인")
    @Test
    void OrOptionTestWithDeleteCommand() {
        String line = "DEL, ,-m, ,phoneNum,9777,-o,-y, ,birthday,1990";
        List<OptionParser> optionParserList = getOptionList(line);

        assertFalse(optionParserList.get(0).isPrintOn());
        assertEquals(Options.MIDDLE_NUMBER_OPTION, optionParserList.get(0).getFilterOption());

        assertEquals(Options.YEAR_OPTION, optionParserList.get(1).getFilterOption());
    }

    @DisplayName("변경 커맨드의 And 연산 확인")
    @Test
    void AndOptionTestWithModifyCommand() {
        String line = "MOD,-p, , ,cl,CL2,-a,-m, ,birthday,01,name,KYUMOKLEE";
        List<OptionParser> optionParserList = getOptionList(line);

        assertTrue(optionParserList.get(0).isPrintOn());

        assertEquals(Options.MONTH_OPTION, optionParserList.get(1).getFilterOption());
    }

    @DisplayName("변경 커맨드의 Or 연산 확인")
    @Test
    void OrOptionTestWithModifyCommand() {
        String line = "MOD, ,-d, ,birthday,06,-o, , ,certi,PRO,birthday,19900906";
        List<OptionParser> optionParserList = getOptionList(line);

        assertFalse(optionParserList.get(0).isPrintOn());
        assertEquals(Options.DAY_OPTION, optionParserList.get(0).getFilterOption());

        assertNotEquals(Options.DAY_OPTION, optionParserList.get(1).getFilterOption());
    }

    @DisplayName("검색 커맨드의 And 연산 확인")
    @Test
    void AndOptionTestWithSearchCommand() {
        String line = "SCH,-p,-l, ,name,KIM,-a, , ,cl,CL4";
        List<OptionParser> optionParserList = getOptionList(line);

        assertTrue(optionParserList.get(0).isPrintOn());
        assertEquals(Options.LAST_NAME_OPTION, optionParserList.get(0).getFilterOption());

        assertNotEquals(Options.DAY_OPTION, optionParserList.get(1).getFilterOption());
    }

    @DisplayName("검색 커맨드의 Or 연산 확인")
    @Test
    void OrOptionTestWithSearchCommand() {
        String line = "SCH, ,-m, ,phoneNum,9777,-o,-y, ,birthday,1990";
        List<OptionParser> optionParserList = getOptionList(line);

        assertFalse(optionParserList.get(0).isPrintOn());
        assertEquals(Options.MIDDLE_NUMBER_OPTION, optionParserList.get(0).getFilterOption());

        assertEquals(Options.YEAR_OPTION, optionParserList.get(1).getFilterOption());
    }

    @DisplayName("정확한 Option Return 확인 ")
    @ParameterizedTest
    @MethodSource
    void OptionAccessorsValidationTest(String line, boolean printOptionExpect, String firstOptionExpect, String secondOptionExpect) {
        List<OptionParser> optionParserList = getOptionList(line);

        assertEquals(printOptionExpect, optionParserList.get(0).isPrintOn());
        assertEquals(firstOptionExpect, optionParserList.get(0).getFilterOption());
        assertEquals(secondOptionExpect, optionParserList.get(0).getCompareOption());

    }

    static Stream<Arguments> OptionAccessorsValidationTest() {
        return Stream.of(
                arguments("SCH,-p,-m,-se,phoneNum,9777", true, Options.MIDDLE_NUMBER_OPTION, Options.SMALLER_EQUAL_OPTION),
                arguments("SCH, ,-l, ,phoneNum,9777", false, Options.LAST_NUMBER_OPTION, Options.EMPTY_OPTION),
                arguments("SCH, ,-l,-ge,name,LEE", false, Options.LAST_NAME_OPTION, Options.GRATER_EQUAL_OPTION),
                arguments("SCH, ,-m, ,birthday,06", false, Options.MONTH_OPTION, Options.EMPTY_OPTION)
        );
    }

    @DisplayName("CommonParser에 잘못된 형식의 query가 요청이 오는 경우 확인")
    @Test
    void minimumTokenTest(){
        String line = "SCH,-m,phoneNum,9777";

        assertThrows(IllegalArgumentException.class, () -> getOptionList(line));
    }

    @DisplayName("OptionParser에 잘못된 형식의 Optinon 요청이 오는 경우 확인")
    @Test
    void wrongOptionTest(){
        String line = "SCH, ,-l, ,phoneNum,9777";
        TokenGroup tokens = commandParser.parse(line);

        assertThrows(IllegalArgumentException.class, () -> commandArguments.generateCommandArguments(tokens.getType(), Arrays.asList(" "), tokens.getParams()));
    }

    private List<OptionParser> getOptionList(String line) {
        TokenGroup tokens = commandParser.parse(line);
        commandArguments.generateCommandArguments(tokens.getType(), tokens.getOptions(), tokens.getParams());

        return commandArguments.getOptionList();
    }
}
