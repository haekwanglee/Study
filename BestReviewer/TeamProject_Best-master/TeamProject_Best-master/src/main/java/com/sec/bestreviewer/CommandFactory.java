package com.sec.bestreviewer;

import com.sec.bestreviewer.command.*;

import java.util.List;

public class CommandFactory {
    public static final String CMD_ADD = "ADD";
    public static final String CMD_DEL = "DEL";
    public static final String CMD_SCH = "SCH";
    public static final String CMD_CNT = "CNT";
    public static final String CMD_MOD = "MOD";

    static Command buildCommand(String cmd, List<String> options, List<String> params)
            throws IllegalArgumentException {
        switch (cmd) {
            case CMD_ADD:
                return new AddCommand(params);
            case CMD_DEL:
                return new DeleteCommand(options, params);
            case CMD_SCH:
                return new SearchCommand(options, params);
            case CMD_CNT:
                return new CountCommand();
            case CMD_MOD:
                return new ModifyCommand(options, params);
        }
        throw new IllegalArgumentException("Wrong command");
    }
}