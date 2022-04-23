package com.sec.bestreviewer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandParserTest {
    @Test
    public void 옵션이_없는_CMD를_잘파싱하는지_확인() {
        String line = "ADD, , ,,08951033,QDJPTOJ KIM,CL3,010-3240-5443,19800308";

        CommandParser commandParser = new CommandParser();
        TokenGroup tokens = commandParser.parse(line);

        assertEquals("ADD", tokens.getType());

        String[] options = {};
        assertArrayEquals(options, tokens.getOptions().toArray());

        String[] params = {"08951033", "QDJPTOJ KIM", "CL3", "010-3240-5443", "19800308"};
        assertArrayEquals(params, tokens.getParams().toArray());
    }

    @Test
    public void 옵션이_있는_CMD를_잘파싱하는지_확인() {
        String line = "SCH,-p, , ,phoneNum,010-2742-2901";

        CommandParser commandParser = new CommandParser();
        TokenGroup tokens = commandParser.parse(line);

        assertEquals("SCH", tokens.getType());

        String[] options = {"-p"};
        assertArrayEquals(options, tokens.getOptions().toArray());

        String[] params = {"phoneNum", "010-2742-2901"};
        assertArrayEquals(params, tokens.getParams().toArray());
    }

    @Test
    public void AND_MOD_옵션이_있는_CMD를_잘파싱하는지_확인() {
        String line = "MOD,-p, , ,phoneNum,010-9777-6055,birthday,20000906,-a,-m, ,birthday,01,name,KYUMOK LEE";

        CommandParser commandParser = new CommandParser();
        TokenGroup tokens = commandParser.parse(line);

        assertEquals("MOD", tokens.getType());
        String[] options = {"-p", "-a", "-m"};
        assertArrayEquals(options, tokens.getOptions().toArray());

        String[] params = {"phoneNum", "010-9777-6055","birthday","20000906", "birthday", "01", "name", "KYUMOK LEE"};
        assertArrayEquals(params, tokens.getParams().toArray());
    }

    @Test
    public void AND_DEL_옵션이_있는_CMD를_잘파싱하는지_확인() {
        String line = "DEL,-p,-l,,name,KIM,-a,-y,,birthday,1990";

        CommandParser commandParser = new CommandParser();
        TokenGroup tokens = commandParser.parse(line);

        assertEquals("DEL", tokens.getType());
        String[] options = {"-p", "-l", "-a", "-y"};
        assertArrayEquals(options, tokens.getOptions().toArray());

        String[] params = {"name", "KIM","birthday","1990"};
        assertArrayEquals(params, tokens.getParams().toArray());
    }

    @Test
    public void AND_SCH_옵션이_있는_CMD를_잘파싱하는지_확인() {
        String line = "SCH,-p,-l,,name,KIM,-a,,,cl,CL4";

        CommandParser commandParser = new CommandParser();
        TokenGroup tokens = commandParser.parse(line);

        assertEquals("SCH", tokens.getType());
        String[] options = {"-p", "-l", "-a"};
        assertArrayEquals(options, tokens.getOptions().toArray());

        String[] params = {"name", "KIM", "cl", "CL4"};
        assertArrayEquals(params, tokens.getParams().toArray());
    }
}
