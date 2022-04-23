package com.sec.bestreviewer;

import com.sec.bestreviewer.data.*;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.parser.CommandParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CommandParserTest {

    @Test
    @DisplayName("ADD CommandData 파싱 테스트")
    void testParseADDCommandType() {
        String line = "ADD, , ,,08951033,QDJPTOJ KIM,CL3,010-3240-5443,19800308,PRO";

        CommandParser commandParser = new CommandParser();
        CommandData commandData = commandParser.parse(line);
        assertEquals(CommandType.ADD, commandData.getType());
        assertEquals(PrintOption.NONE, commandData.getPrintOption());
        assertEquals(AndOrOption.NONE, commandData.getAndOrOption());
        assertNull(commandData.getModifyData());
        assertEquals(0, commandData.getSearchDataList().size());
        assertEquals(commandData.getAddDataList(), Arrays.asList("08951033", "QDJPTOJ KIM", "CL3", "010-3240-5443", "19800308", "PRO"));
    }

    @Test
    @DisplayName("ADD CommandData Certi 포함 파싱 테스트")
    void testParseADDCommandTypeWithCerti() {
        String line = "ADD, , ,,08951033,QDJPTOJ KIM,CL3,010-3240-5443,19800308,certi,PRO";

        CommandParser commandParser = new CommandParser();
        CommandData commandData = commandParser.parse(line);
        assertEquals(CommandType.ADD, commandData.getType());
        assertEquals(PrintOption.NONE, commandData.getPrintOption());
        assertEquals(AndOrOption.NONE, commandData.getAndOrOption());
        assertNull(commandData.getModifyData());
        assertEquals(0, commandData.getSearchDataList().size());
        assertEquals(commandData.getAddDataList(), Arrays.asList("08951033", "QDJPTOJ KIM", "CL3", "010-3240-5443", "19800308", "certi", "PRO"));
    }

    @Test
    @DisplayName("DEL CommandData 파싱 테스트")
    void testParseDELCommandData() {
        String line = "DEL,-p,,,name,KYUMOKKIM";

        CommandParser commandParser = new CommandParser();
        CommandData commandData = commandParser.parse(line);
        assertEquals(CommandType.DEL, commandData.getType());
        assertTrue(commandData.isPrintOn());
        assertEquals(AndOrOption.NONE, commandData.getAndOrOption());
        assertNull(commandData.getModifyData());

        assertEquals("name", commandData.getSearchDataList().get(0).getKeyColumnName());
        assertEquals("KYUMOKKIM", commandData.getSearchDataList().get(0).getKeyValue());
    }

    @Test
    @DisplayName("SCH CommandData 파싱 테스트")
    void testParseSCHCommandData() {
        String line = "SCH,,,,name,KYUMOKKIM";

        CommandParser commandParser = new CommandParser();
        CommandData commandData = commandParser.parse(line);
        assertEquals(CommandType.SCH, commandData.getType());
        assertEquals(PrintOption.NONE, commandData.getPrintOption());
        assertEquals(AndOrOption.NONE, commandData.getAndOrOption());
        assertNull(commandData.getModifyData());

        assertEquals("name", commandData.getSearchDataList().get(0).getKeyColumnName());
        assertEquals("KYUMOKKIM", commandData.getSearchDataList().get(0).getKeyValue());
    }

    @Test
    @DisplayName("MOD CommandData 파싱 테스트")
    void testParseMODCommandData() {
        String line = "MOD,-p,-l, ,name,TEST KIM,name,KIHYUN CHUNG";

        CommandParser commandParser = new CommandParser();
        CommandData commandData = commandParser.parse(line);
        assertEquals(CommandType.MOD, commandData.getType());
        assertEquals(PrintOption.PRINT, commandData.getPrintOption());
        assertEquals(AndOrOption.NONE, commandData.getAndOrOption());
        assertEquals(InqualitySignOption.NONE, commandData.getSearchDataList().get(0).getInqualitySignOption());
        assertEquals("name", commandData.getSearchKeyName(0));
        assertEquals("TEST KIM", commandData.getSearchValue(0));
        assertEquals("name", commandData.getModifyKeyName());
        assertEquals("KIHYUN CHUNG", commandData.getModifyValue());
    }

    @Test
    @DisplayName("SCH CommandData 파싱 테스트 - 부등호 ")
    void testParseMODCommandDataWith() {
        String line = "SCH,-p,-f,-ge,name,KYUMOK";

        CommandParser commandParser = new CommandParser();
        CommandData commandData = commandParser.parse(line);
        assertEquals(CommandType.SCH, commandData.getType());
        assertEquals(PrintOption.PRINT, commandData.getPrintOption());
        assertEquals(AndOrOption.NONE, commandData.getAndOrOption());
        assertEquals(InqualitySignOption.GREATER_THAN_OR_EQUAL_TO, commandData.getSearchDataList().get(0).getInqualitySignOption());
        assertEquals(EmployeeStore.FIELD_NAME, commandData.getSearchDataList().get(0).getKeyColumnName());
        assertEquals("KYUMOK", commandData.getSearchDataList().get(0).getKeyValue());
    }

    @Test
    @DisplayName("Delete AND 조건 Command 파싱 테스트")
    void testParseDeleteAndCommandData() {
        String line = "DEL,-p,-l, ,name,KIM,-a, , ,cl,CL4";

        CommandParser commandParser = new CommandParser();
        CommandData commandData = commandParser.parse(line);
        assertEquals(CommandType.DEL, commandData.getType());
        assertEquals(PrintOption.PRINT, commandData.getPrintOption());
        assertEquals(AndOrOption.AND, commandData.getAndOrOption());
        assertEquals(SearchOption.LAST_NAME, commandData.getSearchDataList().get(0).getSearchOption());
        assertEquals(InqualitySignOption.NONE, commandData.getSearchDataList().get(0).getInqualitySignOption());
        assertEquals(EmployeeStore.FIELD_NAME, commandData.getSearchDataList().get(0).getKeyColumnName());
        assertEquals("KIM", commandData.getSearchDataList().get(0).getKeyValue());
        assertEquals(SearchOption.CL, commandData.getSearchDataList().get(1).getSearchOption());
        assertEquals(InqualitySignOption.NONE, commandData.getSearchDataList().get(1).getInqualitySignOption());
        assertEquals(EmployeeStore.FIELD_CAREER_LEVEL, commandData.getSearchDataList().get(1).getKeyColumnName());
        assertEquals("CL4", commandData.getSearchDataList().get(1).getKeyValue());
    }

    @Test
    @DisplayName("Delete OR 조건 Command 파싱 테스트")
    void testParseDeleteOrCommandData() {
        String line = "DEL, ,-m, ,phoneNum,9777, -o,-y, ,birthday,1990";

        CommandParser commandParser = new CommandParser();
        CommandData commandData = commandParser.parse(line);
        assertEquals(CommandType.DEL, commandData.getType());
        assertEquals(PrintOption.NONE, commandData.getPrintOption());
        assertEquals(AndOrOption.OR, commandData.getAndOrOption());
        assertEquals(SearchOption.MIDDLE_NUMBER, commandData.getSearchDataList().get(0).getSearchOption());
        assertEquals(InqualitySignOption.NONE, commandData.getSearchDataList().get(0).getInqualitySignOption());
        assertEquals(EmployeeStore.FIELD_PHONE_NUMBER, commandData.getSearchDataList().get(0).getKeyColumnName());
        assertEquals("9777", commandData.getSearchDataList().get(0).getKeyValue());
        assertEquals(SearchOption.BIRTHDAY_YEAR, commandData.getSearchDataList().get(1).getSearchOption());
        assertEquals(InqualitySignOption.NONE, commandData.getSearchDataList().get(1).getInqualitySignOption());
        assertEquals(EmployeeStore.FIELD_BIRTH_DAY, commandData.getSearchDataList().get(1).getKeyColumnName());
        assertEquals("1990", commandData.getSearchDataList().get(1).getKeyValue());
    }

    @Test
    @DisplayName("Modify OR 조건 Command 파싱 테스트")
    void testParseModifyOrCommandData() {
        String line = "MOD,-p,-d, ,birthday,06,-o, , ,certi,PRO,birthday,19900906";

        CommandParser commandParser = new CommandParser();
        CommandData commandData = commandParser.parse(line);
        assertEquals(CommandType.MOD, commandData.getType());
        assertEquals(PrintOption.PRINT, commandData.getPrintOption());
        assertEquals(AndOrOption.OR, commandData.getAndOrOption());

        assertEquals(SearchOption.BIRTHDAY_DAY, commandData.getSearchDataList().get(0).getSearchOption());
        assertEquals(InqualitySignOption.NONE, commandData.getSearchDataList().get(0).getInqualitySignOption());
        assertEquals(EmployeeStore.FIELD_BIRTH_DAY, commandData.getSearchDataList().get(0).getKeyColumnName());
        assertEquals("06", commandData.getSearchDataList().get(0).getKeyValue());

        assertEquals(SearchOption.CERTI, commandData.getSearchDataList().get(1).getSearchOption());
        assertEquals(InqualitySignOption.NONE, commandData.getSearchDataList().get(1).getInqualitySignOption());
        assertEquals(EmployeeStore.FIELD_CERTI, commandData.getSearchDataList().get(1).getKeyColumnName());
        assertEquals("PRO", commandData.getSearchDataList().get(1).getKeyValue());

        assertEquals("birthday", commandData.getModifyData().getColumnName());
        assertEquals("19900906", commandData.getModifyData().getValue());
    }

    @Test
    @DisplayName("Search And 조건 Command 파싱 테스트")
    void testParseSearchAndCommandData() {
        String line = "SCH,-p,-l, ,name,KIM,-a, , ,cl,CL4";

        CommandParser commandParser = new CommandParser();
        CommandData commandData = commandParser.parse(line);
        assertEquals(CommandType.SCH, commandData.getType());
        assertEquals(PrintOption.PRINT, commandData.getPrintOption());
        assertEquals(AndOrOption.AND, commandData.getAndOrOption());
        assertEquals(SearchOption.LAST_NAME, commandData.getSearchDataList().get(0).getSearchOption());
        assertEquals(InqualitySignOption.NONE, commandData.getSearchDataList().get(0).getInqualitySignOption());
        assertEquals(EmployeeStore.FIELD_NAME, commandData.getSearchDataList().get(0).getKeyColumnName());
        assertEquals("KIM", commandData.getSearchDataList().get(0).getKeyValue());
        assertEquals(SearchOption.CL, commandData.getSearchDataList().get(1).getSearchOption());
        assertEquals(InqualitySignOption.NONE, commandData.getSearchDataList().get(1).getInqualitySignOption());
        assertEquals(EmployeeStore.FIELD_CAREER_LEVEL, commandData.getSearchDataList().get(1).getKeyColumnName());
        assertEquals("CL4", commandData.getSearchDataList().get(1).getKeyValue());
    }


}
