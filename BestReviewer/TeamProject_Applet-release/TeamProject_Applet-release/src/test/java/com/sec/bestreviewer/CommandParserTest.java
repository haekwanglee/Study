package com.sec.bestreviewer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class CommandParserTest {

    static Stream<Arguments> parseStringWithOption() {

        return Stream.of(
                arguments("ADD, , ,,08951033,QDJPTOJ KIM,CL3,010-3240-5443,19800308",
                        "ADD", "", "",
                        List.of(),
                        List.of(new String[]{"08951033", "QDJPTOJ KIM", "CL3", "010-3240-5443", "19800308"})

                ),
                arguments("SCH, , , ,phoneNum,2901",
                        "SCH", "", "",
                        List.of(new SearchConditionPair(
                                List.of(new String[]{}),
                                List.of(new String[]{"phoneNum", "2901"}))),
                        List.of(new String[]{})

                ),
                arguments("SCH,-p, , ,phoneNum,2901",
                        "SCH", "-p", "",
                        List.of(new SearchConditionPair(
                                List.of(new String[]{}),
                                List.of(new String[]{"phoneNum", "2901"}))),
                        List.of(new String[]{})

                ),
                arguments("SCH,-p,-l,-s,phoneNum,2901",
                        "SCH", "-p", "",
                        List.of(new SearchConditionPair(
                                List.of(new String[]{"-l", "-s"}),
                                List.of(new String[]{"phoneNum", "2901"}))),
                        List.of(new String[]{})

                ),
                arguments("MOD,-p, , ,cl,CL2,-a,-m, ,birthday,01,name,KYUMOK LEE",
                        "MOD", "-p", "-a",
                        List.of(new SearchConditionPair(
                                        List.of(new String[]{}),
                                        List.of(new String[]{"cl", "CL2"})),
                                new SearchConditionPair(
                                        List.of(new String[]{"-m"}),
                                        List.of(new String[]{"birthday", "01"}))),
                        List.of(new String[]{"name", "KYUMOK LEE"})

                ),
                arguments("MOD, ,-l, ,name,A,phoneNum,010-0000-0000",
                        "MOD", "", "",
                        List.of(new SearchConditionPair(
                                List.of(new String[]{"-l"}),
                                List.of(new String[]{"name", "A"}))),
                        List.of(new String[]{"phoneNum", "010-0000-0000"})

                )
        );
    }

    static Stream<Arguments> checkParserInternalMethod() {

        return Stream.of(
                arguments("MOD,-p, , ,cl,CL2,-a,-m, ,birthday,01,name,KYUMOK LEE")
        );
    }

    static Stream<Arguments> checkParserSpecificOption() {

        return Stream.of(
                arguments("SCH,-p,-f,-ge,name,KYUMOK,-o,-y,-s,birthday,1990")
        );
    }

    @ParameterizedTest
    @MethodSource
    void parseStringWithOption(String line, String command, String optionMain, String optionOperator,
                               List<SearchConditionPair> optionParams, List<String> targetParams) {
        CommandParser commandParser = new CommandParser();
        TokenGroup tokens = commandParser.parse(line);

        assertEquals(command, tokens.getType());
        assertEquals(optionMain, tokens.getOptionMain());
        assertEquals(optionOperator, tokens.getOptionOperator());

        for (SearchConditionPair param : optionParams) {
            int index = optionParams.indexOf(param);
            assertArrayEquals(optionParams.get(index).getOptions().toArray(),
                    tokens.getSearchConditionPair().get(index).getOptions().toArray());
            assertArrayEquals(optionParams.get(index).getParams().toArray(),
                    tokens.getSearchConditionPair().get(index).getParams().toArray());
        }

        if (command.equals(CommandFactory.CMD_ADD)) {
            assertArrayEquals(targetParams.toArray(), tokens.getTargetValuesForAdd().toArray());
        } else {
            if (targetParams.size() > 2) {
                assertEquals(targetParams.get(0), tokens.getTargetFieldName());
                assertEquals(targetParams.get(1), tokens.getTargetFieldValue());
            }
        }
    }

    @ParameterizedTest
    @MethodSource
    void checkParserInternalMethod(String line) {
        CommandParser commandParser = new CommandParser();
        TokenGroup tokens = commandParser.parse(line);
        assertEquals(tokens.hasOption(TokenGroup.Option.PRINT), true);
        assertEquals(tokens.hasOption(TokenGroup.Option.AND), true);
        assertEquals(tokens.hasOption(TokenGroup.Option.OR), false);
    }

    @ParameterizedTest
    @MethodSource
    void checkParserSpecificOption(String line) {
        CommandParser commandParser = new CommandParser();
        TokenGroup tokens = commandParser.parse(line);
        assertEquals(tokens.hasOption(TokenGroup.Option.PRINT), true);
        assertEquals(tokens.hasOption(TokenGroup.Option.OR), true);

        assertEquals(tokens.getSearchConditionPair().get(0).hasOptionSpecific(SearchConditionPair.OptionSpecific.FIRST_OF_NAME), true);
        assertEquals(tokens.getSearchConditionPair().get(0).hasOptionSpecific(SearchConditionPair.OptionSpecific.LAST_OF_NAME), false);
        assertEquals(tokens.getSearchConditionPair().get(0).hasOptionSpecific(SearchConditionPair.OptionSpecific.GREATER_EQUAL), true);

        assertEquals(tokens.getSearchConditionPair().get(1).hasOptionSpecific(SearchConditionPair.OptionSpecific.YEAR_OF_BIRTH), true);
        assertEquals(tokens.getSearchConditionPair().get(1).hasOptionSpecific(SearchConditionPair.OptionSpecific.SMALLER), true);
        assertEquals(tokens.getSearchConditionPair().get(1).hasOptionSpecific(SearchConditionPair.OptionSpecific.SMALLER_EQUAL), false);
    }

    @Test
    void testTargetValuesForAdd_withNonAddCommand_throwsException() {
        TokenGroup tokenGroup = new TokenGroup("MOD", "", "", Collections.emptyList(), Collections.emptyList());
        assertThrows(IllegalArgumentException.class, tokenGroup::getTargetValuesForAdd);
    }

    @Test
    void testTargetFieldNameAndFieldValuesForMod_withNonModCommand_throwsException() {
        TokenGroup tokenGroup = new TokenGroup("ADD", "", "", Collections.emptyList(), Collections.emptyList());
        assertThrows(IllegalArgumentException.class, tokenGroup::getTargetFieldName);
        assertThrows(IllegalArgumentException.class, tokenGroup::getTargetFieldValue);
    }
        
    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "   ",
            " , ",
            ", asdf"
    })
    public void testInvalidToken(String line) {
        CommandParser commandParser = new CommandParser();
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> commandParser.parse(line)
        );
    }

    @Test
    public void getTypeTest() {
        CommandParser commandParser = new CommandParser();
        final TokenGroup tokenGroup = commandParser.parse(",,,,,");
        Assertions.assertEquals("", tokenGroup.getType());

    }
}
