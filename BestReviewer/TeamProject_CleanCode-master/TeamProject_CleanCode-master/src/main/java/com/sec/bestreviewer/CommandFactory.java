package com.sec.bestreviewer;

import com.sec.bestreviewer.command.*;
import com.sec.bestreviewer.data.CommandData;

public class CommandFactory {
    public static final String CMD_DEL = "DEL";
    public static final String CMD_SCH = "SCH";
    public static final String CMD_CNT = "CNT";
    public static final String CMD_MOD = "MOD";

    static Command buildCommand(CommandData commandData) {
        switch (commandData.getType()) {
            case ADD:
                return new AddCommand(commandData);
            case DEL:
                return new DeleteCommand(commandData);
            case MOD:
                return new ModifyCommand(commandData);
            case CNT:
                return new CountCommand(commandData);
            case SCH:
                return new SearchCommand(commandData);
        }
        throw new IllegalArgumentException("Wrong command");
    }
}