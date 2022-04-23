package com.sec.bestreviewer;

import com.sec.bestreviewer.command.*;

public class CommandFactory {
    static final String CMD_ADD = "ADD";
    public static final String CMD_DEL = "DEL";
    public static final String CMD_SCH = "SCH";
    public static final String CMD_CNT = "CNT";
    public static final String CMD_MOD = "MOD";

    static Command buildCommand(TokenGroup tokens) throws IllegalArgumentException {
        String cmd = tokens.getType();

        switch (cmd) {
            case CMD_ADD:
                return new AddCommand(tokens.getEmployee());
            case CMD_DEL:
                return new DeleteCommand(tokens.getConditionParameter());
            case CMD_SCH:
                return new SearchCommand(tokens.getConditionParameter());
            case CMD_CNT:
                return new CountCommand();
            case CMD_MOD:
                return new ModifyCommand(tokens.getConditionParameter());

        }
        throw new IllegalArgumentException("Wrong command");
    }
}