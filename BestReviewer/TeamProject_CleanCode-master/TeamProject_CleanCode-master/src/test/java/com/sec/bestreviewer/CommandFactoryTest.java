package com.sec.bestreviewer;

import com.sec.bestreviewer.command.*;
import com.sec.bestreviewer.data.*;
import com.sec.bestreviewer.parser.CommandParser;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandFactoryTest {
    @Test
    void testBuildCommand_ADD() {
        String[] params = new String[]{"param0", "Param 1", "param2", "010-1234-5678", "20211208", "EX"};
        CommandData commandData = new CommandData();
        commandData.setType(CommandType.ADD);
        commandData.setAddDataList(Arrays.asList(params));

        Command command = CommandFactory.buildCommand(commandData);
        assertTrue(command instanceof AddCommand);
    }

    @Test
    void testBuildCommand_SCH_For_EmployeeNumber() {
        List<SearchData> searchDataList = new ArrayList<>();
        searchDataList.add(new SearchData(SearchOption.EMPLOYEE_NUMBER, InqualitySignOption.NONE, "employeeNum", "00000000"));

        CommandData commandData = new CommandData();
        commandData.setType(CommandType.SCH);
        commandData.setSearchDataList(searchDataList);

        Command command = CommandFactory.buildCommand(commandData);
        assertTrue(command instanceof SearchCommand);
    }

    @Test
    void testBuildCommand_SCH_For_Name() {
        List<SearchData> searchDataList = new ArrayList<>();
        searchDataList.add(new SearchData(SearchOption.FULL_NAME, InqualitySignOption.NONE, "name", "test name"));

        CommandData commandData = new CommandData();
        commandData.setType(CommandType.SCH);
        commandData.setSearchDataList(searchDataList);

        Command command = CommandFactory.buildCommand(commandData);
        assertTrue(command instanceof SearchCommand);
    }

    @Test
    void testBuildCommand_SCH_For_CL() {
        List<SearchData> searchDataList = new ArrayList<>();
        searchDataList.add(new SearchData(SearchOption.CL, InqualitySignOption.NONE, "cl", "CL1"));

        CommandData commandData = new CommandData();
        commandData.setType(CommandType.SCH);
        commandData.setSearchDataList(searchDataList);

        Command command = CommandFactory.buildCommand(commandData);
        assertTrue(command instanceof SearchCommand);
    }

    @Test
    void testBuildCommand_SCH_For_PhoneNumber() {
        List<SearchData> searchDataList = new ArrayList<>();
        searchDataList.add(new SearchData(SearchOption.FULL_NUMBER, InqualitySignOption.NONE, "phoneNum", "010-1234-5678"));

        CommandData commandData = new CommandData();
        commandData.setType(CommandType.SCH);
        commandData.setSearchDataList(searchDataList);

        Command command = CommandFactory.buildCommand(commandData);
        assertTrue(command instanceof SearchCommand);
    }

    @Test
    void testBuildCommand_SCH_For_Birthday() {
        List<SearchData> searchDataList = new ArrayList<>();
        searchDataList.add(new SearchData(SearchOption.BIRTHDAY_DAY, InqualitySignOption.NONE, "birthday", "20211207"));

        CommandData commandData = new CommandData();
        commandData.setType(CommandType.SCH);
        commandData.setSearchDataList(searchDataList);

        Command command = CommandFactory.buildCommand(commandData);
        assertTrue(command instanceof SearchCommand);
    }

    @Test
    void testBuildCommand_SCH_For_Certi() {
        List<SearchData> searchDataList = new ArrayList<>();
        searchDataList.add(new SearchData(SearchOption.CERTI, InqualitySignOption.NONE, "certi", "PRO"));

        CommandData commandData = new CommandData();
        commandData.setType(CommandType.SCH);
        commandData.setSearchDataList(searchDataList);

        Command command = CommandFactory.buildCommand(commandData);
        assertTrue(command instanceof SearchCommand);
    }

    @Test
    void testBuildCommand_DEL() {
        List<SearchData> searchDataList = new ArrayList<>();
        searchDataList.add(new SearchData(SearchOption.CERTI, InqualitySignOption.NONE, "name", "test name"));

        CommandData commandData = new CommandData();
        commandData.setType(CommandType.DEL);
        commandData.setSearchDataList(searchDataList);

        Command command = CommandFactory.buildCommand(commandData);
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    void testBuildCommand_CNT() {
        CommandData commandData = new CommandData();
        commandData.setType(CommandType.CNT);

        Command command = CommandFactory.buildCommand(commandData);
        assertTrue(command instanceof CountCommand);
    }

    @Test
    void testBuildCommand_NotSupportedArgument() {
        assertThrows(IllegalArgumentException.class, () -> {
            CommandData commandData = new CommandData();
            commandData.setType(CommandType.NONE);
            CommandFactory.buildCommand(commandData);
        });
    }

    @Test
    void testBuildCommand_ADD_WithCommandData() {
        String line = "ADD, , ,,08951033,QDJPTOJ KIM,CL3,010-3240-5443,19800308,PRO";
        CommandParser commandParser = new CommandParser();
        CommandData commandData = commandParser.parse(line);
        Command command = CommandFactory.buildCommand(commandData);
        assertTrue(command instanceof AddCommand);
    }
}