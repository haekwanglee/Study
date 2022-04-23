package com.sec.bestreviewer;

import com.sec.bestreviewer.command.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandFactoryTest {

    @Test
    void addBuildCommandTest(){
        String line = "ADD, , , ,18050301,KYUMOK KIM,CL2,010-9777-6055,19980906,ADV";
        Command retCommand = getCommand(line);

        assertTrue(retCommand instanceof AddCommand);
    }


    @Test
    void deleteBuildCommandTest(){
        String line = "DEL, , , ,name,KYUMOK KIM";
        Command retCommand = getCommand(line);

        assertTrue(retCommand instanceof DeleteCommand);
    }

    @Test
    void searchBuildCommandTest(){
        String line = "SCH, , , ,name,KYUMOK KIM";
        Command retCommand = getCommand(line);

        assertTrue(retCommand instanceof SearchCommand);
    }

    @Test
    void countBuildCommandTest(){
        String line = "CNT, , , ,name,KYUMOK KIM";
        Command retCommand = getCommand(line);

        assertTrue(retCommand instanceof CountCommand);
    }

    @Test
    void modifyBuildCommandTest(){
        String line = "MOD, , , ,name,KYUMOK KIM,name,KYUMOK LEE";
        Command retCommand = getCommand(line);

        assertTrue(retCommand instanceof ModifyCommand);
    }

    @Test
    void wrongCommandTest(){
        String line = "ETC, , , ,name,KYUMOK KIM";
        CommandParser commandParser = new CommandParser();
        TokenGroup tokens = commandParser.parse(line);

        assertThrows(IllegalArgumentException.class, () -> getCommand(line));
    }

    private Command getCommand(String line) {
        CommandParser commandParser = new CommandParser();
        TokenGroup tokens = commandParser.parse(line);

        return CommandFactory.buildCommand(tokens.getType(), tokens.getOptions(), tokens.getParams());
    }
}
