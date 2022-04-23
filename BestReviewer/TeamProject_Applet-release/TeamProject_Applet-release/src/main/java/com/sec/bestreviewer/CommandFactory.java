package com.sec.bestreviewer;

import com.sec.bestreviewer.command.*;

import java.util.ArrayList;
import java.util.List;

public class CommandFactory {

    public static final String CMD_ADD = "ADD";
    public static final String CMD_DEL = "DEL";
    public static final String CMD_SCH = "SCH";
    public static final String CMD_MOD = "MOD";

    static Command buildCommand(TokenGroup tokenGroup)
            throws IllegalArgumentException {

        switch (tokenGroup.getType()) {
            case CMD_ADD:
                return new AddCommand(tokenGroup);
            case CMD_DEL:
                return new DeleteCommand(tokenGroup);
            case CMD_SCH:
                return new SearchCommand(tokenGroup);
            case CMD_MOD:
                return new ModCommand(tokenGroup);
        }
        throw new IllegalArgumentException("Wrong command");
    }
}