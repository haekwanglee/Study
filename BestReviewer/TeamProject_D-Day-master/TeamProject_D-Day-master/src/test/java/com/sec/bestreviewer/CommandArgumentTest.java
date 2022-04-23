package com.sec.bestreviewer;

import com.sec.bestreviewer.util.OptionParser;
import com.sec.bestreviewer.util.Options;
import com.sec.bestreviewer.util.Pair;
import com.sec.bestreviewer.util.arguments.CommandArguments;
import com.sec.bestreviewer.util.arguments.CommandArgumentsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.sec.bestreviewer.util.arguments.CommandOptionSeparator.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class CommandArgumentTest {
    private static final String SingleOptionADDCommand = "ADD,-p, , ,18050301,KYUMOK KIM,CL2,010-9777-6955,19980906";
    private static final String NOOptionADDCommand = "ADD, , , ,18050301,KYUMOK KIM, CL2,010-9777-6955,19980906";
    private static final String SingleOptionDeleteCommand = "DEL,-p, , ,name,KYUMOK KIM";
    private static final String SingleOptionSearchCommand = "SCH,-p, , ,birthday,19980906";
    private static final String DoubleParameterModCommand = "MOD,-p, , ,name,KYUMOK KIM,name,KYUMOK LEE";
    private static final String DoubleOptionDeleteCommand = "DEL,-p,-m, ,phoneNum,9777";
    private static final String SpecialModeDeleteCommand = "DEL,-p, , ,name,KIM,-a, , ,cl,CL4";
    private static final String SpecialModeDeleteCommandWithFilter = "DEL,-p,-l, ,name,KIM,-a, ,-g,cl,CL4";
    private static final String SpecialModeModCommand = "MOD,-p, , ,cl,CL2,-o, , ,name,KYUMOK KIM,name,KYUMOK LEE";
    private static final String SpecialModeSearchCommandWithFilter = "SCH, , ,-se,cl,CL2,-o,-f,-ge,name,KYUMOK";

    CommandArguments commandArguments;
    CommandParser commandParser;
    CommandFactory commandFactory;


    @BeforeEach
    public void setUpEachTest() {
        commandArguments = new CommandArgumentsImpl();
        commandParser = new CommandParser();
        commandFactory = new CommandFactory();
    }

    @Test
    public void AddCommandWithSingleOptionTest() {
        String line = SingleOptionADDCommand;
        TokenGroup tokens = commandParser.parse(line);
        commandArguments.generateCommandArguments(tokens.getType(), tokens.getOptions(), tokens.getParams());

        assertEquals(1, commandArguments.getOptionList().size());
        assertEquals(5, commandArguments.getRawParams().size());

        OptionParser option = commandArguments.getOptionList().get(0);

        assertTrue(option.isPrintOn());
    }

    @Test
    public void AddCommandWithNOOptionTest() {
        String line = NOOptionADDCommand;
        TokenGroup tokens = commandParser.parse(line);
        commandArguments.generateCommandArguments(tokens.getType(), tokens.getOptions(), tokens.getParams());

        assertEquals(1, commandArguments.getOptionList().size());
        assertEquals(5, commandArguments.getRawParams().size());

        OptionParser option = commandArguments.getOptionList().get(0);

        assertFalse(option.isPrintOn());
    }

    @Test
    public void DeleteCommandWithSingleOptionTest() {
        String line = SingleOptionDeleteCommand;
        TokenGroup tokens = commandParser.parse(line);
        commandArguments.generateCommandArguments(tokens.getType(), tokens.getOptions(), tokens.getParams());

        assertEquals(1, commandArguments.getOptionList().size());
        assertEquals(1, commandArguments.getConditionParamList().size());

        OptionParser option = commandArguments.getOptionList().get(0);
        Pair<String, String> conditionParam = commandArguments.getConditionParamList().get(0);

        assertTrue(option.isPrintOn());
        assertEquals("name", conditionParam.first);
        assertEquals("KYUMOK KIM", conditionParam.second);
    }

    @Test
    public void SearchCommandWithSingleOptionTest() {
        String line = SingleOptionSearchCommand;
        TokenGroup tokens = commandParser.parse(line);
        commandArguments.generateCommandArguments(tokens.getType(), tokens.getOptions(), tokens.getParams());

        assertEquals(1, commandArguments.getOptionList().size());
        assertEquals(1, commandArguments.getConditionParamList().size());

        OptionParser option = commandArguments.getOptionList().get(0);
        Pair<String, String> conditionParam = commandArguments.getConditionParamList().get(0);

        assertTrue(option.isPrintOn());
        assertEquals("birthday", conditionParam.first);
        assertEquals("19980906", conditionParam.second);
    }

    @Test
    public void MODCommandWithDoubleParameterTest() {
        String line = DoubleParameterModCommand;
        TokenGroup tokens = commandParser.parse(line);
        commandArguments.generateCommandArguments(tokens.getType(), tokens.getOptions(), tokens.getParams());

        assertEquals(1, commandArguments.getOptionList().size());
        assertEquals(1, commandArguments.getConditionParamList().size());
        assertNotNull(commandArguments.getTargetParam());

        OptionParser option = commandArguments.getOptionList().get(0);
        Pair<String, String> conditionParam = commandArguments.getConditionParamList().get(0);
        Pair<String, String> targetParam = commandArguments.getTargetParam();

        assertTrue(option.isPrintOn());
        assertEquals("name", conditionParam.first);
        assertEquals("KYUMOK KIM", conditionParam.second);
        assertEquals("name", targetParam.first);
        assertEquals("KYUMOK LEE", targetParam.second);
    }

    @Test
    public void DeleteCommandWithDoubleOptionTest() {
        String line = DoubleOptionDeleteCommand;
        TokenGroup tokens = commandParser.parse(line);
        commandArguments.generateCommandArguments(tokens.getType(), tokens.getOptions(), tokens.getParams());

        assertEquals(1, commandArguments.getOptionList().size());
        assertEquals(1, commandArguments.getConditionParamList().size());

        OptionParser option = commandArguments.getOptionList().get(0);
        Pair<String, String> conditionParam = commandArguments.getConditionParamList().get(0);

        assertTrue(option.isPrintOn());
        assertEquals("phoneNum", conditionParam.first);
        assertEquals("9777", conditionParam.second);
    }

    @Test
    public void DeleteCommandWithSpecialModeTest() {
        String line = SpecialModeDeleteCommand;
        TokenGroup tokens = commandParser.parse(line);
        commandArguments.generateCommandArguments(tokens.getType(), tokens.getOptions(), tokens.getParams());

        assertEquals(2, commandArguments.getOptionList().size());
        assertEquals(2, commandArguments.getConditionParamList().size());

        OptionParser option = commandArguments.getOptionList().get(0);
        Pair<String, String> conditionParam = commandArguments.getConditionParamList().get(0);
        Pair<String, String> secondConditionParam = commandArguments.getConditionParamList().get(1);

        assertTrue(option.isPrintOn());
        assertEquals("name", conditionParam.first);
        assertEquals("KIM", conditionParam.second);
        assertEquals("cl", secondConditionParam.first);
        assertEquals("CL4", secondConditionParam.second);
    }

    @Test
    public void ModCommandWithSpecialModeTest() {
        String line = SpecialModeModCommand;
        TokenGroup tokens = commandParser.parse(line);
        commandArguments.generateCommandArguments(tokens.getType(), tokens.getOptions(), tokens.getParams());

        assertEquals(2, commandArguments.getOptionList().size());
        assertEquals(2, commandArguments.getConditionParamList().size());

        OptionParser option = commandArguments.getOptionList().get(0);
        Pair<String, String> conditionParam = commandArguments.getConditionParamList().get(0);
        Pair<String, String> secondConditionParam = commandArguments.getConditionParamList().get(1);
        Pair<String, String> targetParam = commandArguments.getTargetParam();

        assertTrue(option.isPrintOn());

        assertEquals("cl", conditionParam.first);
        assertEquals("CL2", conditionParam.second);
        assertEquals("name", secondConditionParam.first);
        assertEquals("KYUMOK KIM", secondConditionParam.second);
        assertEquals("name", targetParam.first);
        assertEquals("KYUMOK LEE", targetParam.second);
    }


    @ParameterizedTest
    @MethodSource
    public void ValidOptionTestWithLogicalOperatorTest(String line, boolean expectedPrinterOption, List<String> expectedResults) {
        TokenGroup tokens = commandParser.parse(line);
        commandArguments.generateCommandArguments(tokens.getType(), tokens.getOptions(), tokens.getParams());

        assertEquals(2, commandArguments.getOptionList().size());

        OptionParser firstCommandOptions = commandArguments.getOptionList().get(0);
        OptionParser secondCommandOptions = commandArguments.getOptionList().get(1);

        assertEquals(expectedPrinterOption, firstCommandOptions.isPrintOn());
        assertEquals(expectedResults.get(0), firstCommandOptions.getFilterOption());
        assertEquals(expectedResults.get(1), firstCommandOptions.getCompareOption());

        assertEquals(expectedResults.get(2), secondCommandOptions.getFilterOption());
        assertEquals(expectedResults.get(3), secondCommandOptions.getCompareOption());
    }

    static Stream<Arguments> ValidOptionTestWithLogicalOperatorTest(){
        return Stream.of(
                arguments(SpecialModeModCommand, true, Arrays.asList(Options.EMPTY_OPTION, Options.EMPTY_OPTION, Options.EMPTY_OPTION, Options.EMPTY_OPTION)),
                arguments(SpecialModeDeleteCommand, true, Arrays.asList(Options.EMPTY_OPTION, Options.EMPTY_OPTION, Options.EMPTY_OPTION, Options.EMPTY_OPTION)),
                arguments(SpecialModeDeleteCommandWithFilter, true, Arrays.asList(Options.LAST_NAME_OPTION, Options.EMPTY_OPTION, Options.EMPTY_OPTION, Options.GRATER_OPTION)),
                arguments(SpecialModeSearchCommandWithFilter, false, Arrays.asList(Options.EMPTY_OPTION, Options.SMALLER_EQUAL_OPTION, Options.FIRST_NAME_OPTION, Options.GRATER_EQUAL_OPTION))
                );
    }

    @Test
    void wrongFieldParameterTest(){
        String line = "DEL,-p, , ,,KYUMOK KIM";
        TokenGroup tokens = commandParser.parse(line);

        assertThrows(IllegalArgumentException.class, () -> commandArguments.generateCommandArguments(tokens.getType(), tokens.getOptions(), tokens.getParams()));
    }

    @Test
    void getCommandModeTest(){
        String line = SingleOptionDeleteCommand;
        TokenGroup tokens = commandParser.parse(line);
        commandArguments.generateCommandArguments(tokens.getType(), tokens.getOptions(), tokens.getParams());

        assertEquals(NORMAL_COMMAND, commandArguments.getCommandMode());

        line = SpecialModeDeleteCommand;
        tokens = commandParser.parse(line);
        commandArguments.generateCommandArguments(tokens.getType(), tokens.getOptions(), tokens.getParams());

        assertEquals(AND_COMMAND, commandArguments.getCommandMode());

        line = SpecialModeModCommand;
        tokens = commandParser.parse(line);
        commandArguments.generateCommandArguments(tokens.getType(), tokens.getOptions(), tokens.getParams());

        assertEquals(OR_COMMAND, commandArguments.getCommandMode());
    }

    @Test
    void pairMethodsOfParamTest(){
        String line = SingleOptionDeleteCommand;
        TokenGroup tokens = commandParser.parse(line);
        commandArguments.generateCommandArguments(tokens.getType(), tokens.getOptions(), tokens.getParams());
        Pair<String, String> paramList = commandArguments.getConditionParamList().get(0);

        assertNotNull(paramList.hashCode());
        assertNotNull(paramList.toString());
    }
}
