package com.sec.bestreviewer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandParserTest {
    private final static String SPACE = " ";

    @Test
    @DisplayName("옵션이_없는_CMD를_잘파싱하는지_확인")
    public void testParserWithOutOptions() {
        String line = "ADD, , ,,08951033,QDJPTOJ KIM,CL3,010-3240-5443,19800308,ADV";

        CommandParser commandParser = new CommandParser();
        TokenGroup tokens = commandParser.parse(line);

        assertEquals("ADD", tokens.getType());

        String[] options = {SPACE, SPACE, SPACE};
        assertArrayEquals(options, tokens.getOptions().toArray());

        String[] params = {"08951033", "QDJPTOJ KIM", "CL3", "010-3240-5443", "19800308", "ADV"};
        assertArrayEquals(params, tokens.getParams().toArray());
    }

    @Test
    @DisplayName("옵션이_있는_CMD를_잘파싱하는지_확인")
    public void testParserWithOneOptions() {
        String line = "SCH,-p, , ,phoneNum,010-2742-2901";

        CommandParser commandParser = new CommandParser();
        TokenGroup tokens = commandParser.parse(line);


        assertEquals("SCH", tokens.getType());

        String[] options = {"-p", SPACE, SPACE};
        assertArrayEquals(options, tokens.getOptions().toArray());

        String[] params = {"phoneNum", "010-2742-2901"};
        assertArrayEquals(params, tokens.getParams().toArray());
    }

    @Test
    @DisplayName("옵션이_3개인_CMD를_잘파싱하는지_확인")
    public void testParserWithThreeOptions() {
        String line = "SCH,-p,-y,-ge,birthday,1990";

        CommandParser commandParser = new CommandParser();
        TokenGroup tokens = commandParser.parse(line);


        assertEquals("SCH", tokens.getType());

        String[] options = {"-p", "-y", "-ge"};
        assertArrayEquals(options, tokens.getOptions().toArray());

        String[] params = {"birthday", "1990"};
        assertArrayEquals(params, tokens.getParams().toArray());
    }

    @Test
    @DisplayName("-a 옵션이_있는_CMD를_잘파싱하는지_확인")
    public void testParserWithAndOptions() {
        String line = "DEL,-p,-l, ,name,KIM,-a, , ,cl,CL4";

        CommandParser commandParser = new CommandParser();
        TokenGroup tokens = commandParser.parse(line);


        assertEquals("DEL", tokens.getType());

        String[] options = {"-p", "-l", SPACE, "-a", SPACE, SPACE};
        assertArrayEquals(options, tokens.getOptions().toArray());

        String[] params = {"name", "KIM", "cl", "CL4"};
        assertArrayEquals(params, tokens.getParams().toArray());
    }

    @Test
    @DisplayName("-o 옵션이_있는_CMD를_잘파싱하는지_확인")
    public void testParserWithOrOptions() {
        String line = "DEL, ,-m, ,phoneNum,9777,-o,-y, ,birthday,1990";

        CommandParser commandParser = new CommandParser();
        TokenGroup tokens = commandParser.parse(line);


        assertEquals("DEL", tokens.getType());

        String[] options = {SPACE, "-m", SPACE, "-o", "-y", SPACE};
        assertArrayEquals(options, tokens.getOptions().toArray());

        String[] params = {"phoneNum", "9777", "birthday", "1990"};
        assertArrayEquals(params, tokens.getParams().toArray());
    }

    @Test
    @DisplayName("옵션이_2개인_MOD_CMD를_잘파싱하는지_확인")
    public void testParserMODWithThreeOptions() {
        String line = "MOD,-p,-y, ,birthday,1990,birthday,1991";

        CommandParser commandParser = new CommandParser();
        TokenGroup tokens = commandParser.parse(line);


        assertEquals("MOD", tokens.getType());

        String[] options = {"-p", "-y", SPACE};
        assertArrayEquals(options, tokens.getOptions().toArray());

        String[] params = {"birthday", "1990", "birthday", "1991"};
        assertArrayEquals(params, tokens.getParams().toArray());
    }

    @Test
    @DisplayName("-a 옵션이_있는_MOD_CMD를_잘파싱하는지_확인")
    public void testParserMODWithAndOptions() {
        String line = "MOD,-p,-l, ,name,KIM,-a, , ,cl,CL4,birthday,1991";

        CommandParser commandParser = new CommandParser();
        TokenGroup tokens = commandParser.parse(line);


        assertEquals("MOD", tokens.getType());

        String[] options = {"-p", "-l", SPACE, "-a", SPACE, SPACE};
        assertArrayEquals(options, tokens.getOptions().toArray());

        String[] params = {"name", "KIM", "cl", "CL4", "birthday", "1991"};
        assertArrayEquals(params, tokens.getParams().toArray());
    }

    @Test
    @DisplayName("-o 옵션이_있는_MOD_CMD를_잘파싱하는지_확인")
    public void testParserMODWithOrOptions() {
        String line = "MOD, ,-m, ,phoneNum,9777,-o,-y, ,birthday,1990,birthday,1991";

        CommandParser commandParser = new CommandParser();
        TokenGroup tokens = commandParser.parse(line);


        assertEquals("MOD", tokens.getType());

        String[] options = {SPACE, "-m", SPACE, "-o", "-y", SPACE};
        assertArrayEquals(options, tokens.getOptions().toArray());

        String[] params = {"phoneNum", "9777", "birthday", "1990", "birthday", "1991"};
        assertArrayEquals(params, tokens.getParams().toArray());
    }
}
