package com.sec.bestreviewer;

import com.sec.bestreviewer.command.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandFactoryTest {
    ArrayList<String> options = new ArrayList<>();
    @Test
    void testAddCommand() {
        final List<String> options = Collections.singletonList("-p");
        final List<String> params = Arrays.asList(" ", " ", "18050301", "KYUMOK KIM", "CL2", "010-9777-6055", "19980906", "PRO");
        assertTrue(CommandFactory.buildCommand(CommandFactory.CMD_ADD, options, params) instanceof AddCommand);
    }
    @Test
    void testDeleteCommand() {
        final List<String> options = Collections.singletonList("-p");
        final List<String> params = Arrays.asList(" ", " " , "name", "KYUMOK KIM");
        assertTrue(CommandFactory.buildCommand(CommandFactory.CMD_DEL, options, params) instanceof DeleteCommand);
    }
    @Test
    void testSearchCommand() {
        final List<String> options = Collections.singletonList("-p");
        final List<String> params = Arrays.asList(" ", " ", "name", "KYUMOK KIM");
        assertTrue(CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params) instanceof SearchCommand);
    }
    @Test
    void testModifyCommand() {
        final List<String> options = Collections.singletonList("-p");
        final List<String> params = Arrays.asList(" ", " ", "name", "KYUMOK KIM", "name", "KYUMOK LEE");
        assertTrue(CommandFactory.buildCommand(CommandFactory.CMD_MOD, options, params) instanceof ModifyCommand);
    }
    @Test
    void testUndefinedCommand() {
        final List<String> options = Collections.singletonList("-p");
        final List<String> params = Arrays.asList(" ", " ", "name", "KYUMOK KIM", "name", "KYUMOK LEE");
        assertThrows(IllegalArgumentException.class, () -> CommandFactory.buildCommand("CMD", options, params));
    }
    @Test
    void testWrongField() {
        final List<String> options = Collections.singletonList("-p");
        final List<String> params = Arrays.asList(" ", " ", "wrong", "KYUMOK KIM", "name", "KYUMOK LEE");
        assertThrows(IllegalArgumentException.class, () -> CommandFactory.buildCommand(CommandFactory.CMD_MOD, options, params));
    }

}
