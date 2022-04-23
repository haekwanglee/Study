package com.sec.bestreviewer;

import com.sec.bestreviewer.command.Command;
import com.sec.bestreviewer.database.Table;
import com.sec.bestreviewer.store.Injection;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CommandExecutorTest {

    private Table employeeDatabase;

    @BeforeEach
    void createMockEmployeeStore() {
        employeeDatabase = mock(Table.class);
    }

    @Test
    void queryExecutorReturnsResultString() {
        String commandline = "ADD,-p, , ,18050301,KYUMOK KIM,CL2,010-9777-6055,19980906";
        TokenGroup tokens = new CommandParser().parse(commandline);
        final Command addCommand = CommandFactory.buildCommand(tokens);
        final List<String> res = (new CommandExecutor()).execute(addCommand);
        assertNotNull(res);
    }

    @Test
    void testAddCommandReturnsEmptyList() {
        String commandline = "ADD, , , ,18050301,KYUMOK KIM,CL2,010-9777-6055,19980906";
        TokenGroup tokens = new CommandParser().parse(commandline);
        final Command addCommand = CommandFactory.buildCommand(tokens);
        final List<String> res = (new CommandExecutor()).execute(addCommand);
        assertNotNull(res);
        assertEquals(0, res.size());
    }

    @Nested
    public class DeleteTest {
        Table realEmployeeDatabase = Injection.provideEmployeeDatabase();
        public CommandExecutor commandExecutor;
        public CommandParser commandParser;
        public Printer printer;

        @BeforeEach
        void setupDatabase() {
            String[] addEmployeeCommands = new String[]{
                    "ADD, , , ,18050301,KYUMOK KIM,CL2,010-9777-6055,19980906,ADV",
                    "ADD, , , ,91385769,KEUNYOUNG PARK,CL2,010-9777-6055,19980906,ADV",
                    "ADD, , , ,91385768,KEUNYOUNG PARK,CL3,010-9777-6055,19980906,PRO",
                    "ADD, , , ,91385767,KEUNYOUNG PARK,CL4,010-9777-6055,19980906,PRO",
                    "ADD, , , ,19837450,YEJI YOON,CL1,010-1234-5678,19970415,EX",
                    "ADD, , , ,06837495,HONGBIN MIN,CL3,010-4567-1111,20050101,EX",
                    "ADD, , , ,06837566,HAEKWANG LEE,CL3,010-9777-6055,20121212,ADV",
                    "ADD, , , ,19384050,MINHYUK YOON,CL3,010-9777-6055,19820917,ADV"
            };

            commandParser = new CommandParser();
            commandExecutor = new CommandExecutor(realEmployeeDatabase);

            for (String line : addEmployeeCommands) {
                TokenGroup tokens = commandParser.parse(line);
                Command command = CommandFactory.buildCommand(tokens);
                commandExecutor.execute(command);
            }

            printer = new Printer();
        }

        @ParameterizedTest
        @CsvSource(
                value = {
                        "DEL,-p, , ,employeeNum,06837566|1|1",
                        "DEL,-p, , ,name,KYUMOK KIM|1|1",
                        "DEL,-p, , ,name,KEUNYOUNG PARK|3|3",
                        "DEL,-p, , ,phoneNum,010-9777-6055|6|5",
                        "DEL,-p, , ,cl,CL2|2|2",
                        "DEL,-p, , ,birthday,20121212|1|1",
                },
                delimiter = '|'
        )
        void deleteCommandWithPrintOption(String line, int expected, int printLineNum) {
            testDeleteCommandLogic(line, expected);
            assertEquals(printLineNum, printer.peekStringOutput().split("\n").length);
        }

        @ParameterizedTest
        @CsvSource(
                value = {
                        "DEL, , , ,employeeNum,06837566|1",
                        "DEL, , , ,name,KYUMOK KIM|1",
                        "DEL, , , ,name,KEUNYOUNG PARK|3",
                        "DEL, , , ,phoneNum,010-9777-6055|6",
                        "DEL, , , ,cl,CL2|2",
                        "DEL, , , ,birthday,20121212|1",
                },
                delimiter = '|'
        )
        void testDeleteCommandWithOutPrintOption(String line, int expected) {
            testDeleteCommandLogic(line, expected);
            assertEquals("DEL," + expected + "\n", printer.peekStringOutput());
        }

        @ParameterizedTest
        @CsvSource(
                value = {
                        "DEL,-p, , ,birthday,20121213|0",
                        "DEL, , , ,birthday,20121213|0"
                },
                delimiter = '|'
        )
        void testDeleteCommandWithNoneResult(String line, int expected) {
            testDeleteCommandLogic(line, expected);
            assertEquals("DEL,NONE\n", printer.peekStringOutput());
        }

        void testDeleteCommandLogic(String line, int expected) {
            int originalNumDbEntry = realEmployeeDatabase.getRowList().size();

            TokenGroup tokens = commandParser.parse(line);
            Command command = CommandFactory.buildCommand(tokens);
            List<String> rowResult = commandExecutor.execute(command);
            printer.addCommandResult(tokens, rowResult);

            assertEquals(expected, rowResult.size());
            assertEquals(realEmployeeDatabase.getRowList().size(), originalNumDbEntry - rowResult.size());
        }
    }

    @Nested
    @DisplayName("Search command test")
    public class SearchCommandTest {
        Table realEmployeeDatabase = Injection.provideEmployeeDatabase();
        private final String[] employees;

        public SearchCommandTest() {
            employees = new String[]{
                    "18050301,KYUMOK KIM,CL2,010-9777-6055,19980906,ADV",
                    "91385769,KEUNYOUNG PARK,CL2,010-9777-6055,19980906,PRO",
                    "19837450,YEJI YOON,CL1,010-1234-5678,19970415,PRO",
                    "06837495,HONGBIN MIN,CL3,010-4567-1111,20050101,PRO",
                    "06837566,HAEKWANG LEE,CL3,010-9777-6055,20121212,PRO",
                    "19384050,MINHYUK YOON,CL3,010-9777-6055,19820917,PRO"
            };

            for (String employee : employees) {
                String commandline = "ADD, , , ," + employee;
                TokenGroup tokens = new CommandParser().parse(commandline);
                final Command addCommand = CommandFactory.buildCommand(tokens);
                List<String> res = (new CommandExecutor(realEmployeeDatabase)).execute(addCommand);
            }
        }

        @ParameterizedTest
        @CsvSource({
                "SCH|-p| | |employeeNum|06837566,4",
                "SCH|-p| | |name|KYUMOK KIM,0",
                "SCH|-p| | |cl|CL1,2",
                "SCH|-p| | |phoneNum|010-4567-1111,3",
                "SCH|-p| | |birthday|20121212,4"
        })
        @DisplayName("field 로 검색 및 결과1개 - with print option")
        void testSearchCommandWithPrintOption(String subQuery, int index) {
            TokenGroup tokens = new CommandParser().parse(subQuery.replace("|", ","));
            final Command searchCommand = CommandFactory.buildCommand(tokens);

            List<String> res = (new CommandExecutor(realEmployeeDatabase)).execute(searchCommand);
            assertEquals(employees[index], res.get(0));
        }

        @ParameterizedTest
        @CsvSource({
                "SCH| | | |employeeNum|06837566,4",
                "SCH| | | |name|KYUMOK KIM,0",
                "SCH| | | |cl|CL1,2",
                "SCH| | | |phoneNum|010-4567-1111,3",
                "SCH| | | |birthday|20121212,4"
        })
        @DisplayName("field 로 검색 및 결과1개 - without print option")
        void testSearchCommandWithOutPrintOption(String subQuery, int index) {
            TokenGroup tokens = new CommandParser().parse(subQuery.replace("|", ","));
            final Command searchCommand = CommandFactory.buildCommand(tokens);
            List<String> res = (new CommandExecutor(realEmployeeDatabase)).execute(searchCommand);
            assertEquals(1, res.size());
        }

        @Test
        @DisplayName("birthday field 로 검색 - 소팅까지 확인됨")
        void searchCommandWithPrintOptionBybirthday() {
            TokenGroup tokens = new CommandParser().parse("SCH,-p, , ,birthday,19980906");
            final Command searchCommand = CommandFactory.buildCommand(tokens);
            List<String> res = (new CommandExecutor(realEmployeeDatabase)).execute(searchCommand);
            assertEquals(
                    "91385769,KEUNYOUNG PARK,CL2,010-9777-6055,19980906,PRO",
                    res.get(0));
            assertEquals(
                    "18050301,KYUMOK KIM,CL2,010-9777-6055,19980906,ADV",
                    res.get(1));
        }

        @ParameterizedTest
        @CsvSource({
                "SCH| | | |employeeNum|11111222",
                "SCH| | | |name|KYUMOK LEE",
                "SCH| | | |cl|CL0",
                "SCH| | | |phoneNum|010-4567-3333",
                "SCH| | | |birthday|20121225"
        })
        @DisplayName("NoneResult")
        void testSearchCommandWithPrintOption_NoneResult(String subQuery) {
            TokenGroup tokens = new CommandParser().parse(subQuery.replace("|", ","));
            final Command searchCommand = CommandFactory.buildCommand(tokens);

            List<String> res = (new CommandExecutor(realEmployeeDatabase)).execute(searchCommand);
            assertTrue(res.isEmpty());
        }

        @Test
        void testSearchCommandWithUnknownField() {
            String commandline = "SCH, , , ,unknownField,11111222";
            TokenGroup tokens = new CommandParser().parse(commandline);
            final Command searchCommand = CommandFactory.buildCommand(tokens);

            List<String> res = (new CommandExecutor(realEmployeeDatabase)).execute(searchCommand);
            assertTrue(res.isEmpty());
        }
    }

    @Test
    void testInvalidCommand() {
        TokenGroup tokens = new TokenGroup("INV", "", "", Collections.emptyList(), Collections.emptyList());
        Assertions.assertThrows(IllegalArgumentException.class, () -> CommandFactory.buildCommand(tokens));
    }
}