package com.sec.bestreviewer;

import com.sec.bestreviewer.command.*;
import com.sec.bestreviewer.util.arguments.CommandArguments;
import com.sec.bestreviewer.util.arguments.CommandArgumentsImpl;

import java.util.List;

public class CommandFactory {
    public static final String CMD_ADD = "ADD";
    public static final String CMD_DEL = "DEL";
    public static final String CMD_SCH = "SCH";
    public static final String CMD_CNT = "CNT";
    public static final String CMD_MOD = "MOD";

    static Command buildCommand(String cmd, List<String> options, List<String> params)
            throws IllegalArgumentException {

        CommandArguments commandArguments = new CommandArgumentsImpl();
        commandArguments.generateCommandArguments(cmd, options, params);

        switch (cmd) {
            case CMD_ADD:
                return new AddCommand(commandArguments);
            case CMD_DEL:
                return new DeleteCommand(commandArguments);
            case CMD_SCH:
                return new SearchCommand(commandArguments);
            case CMD_CNT:
                return new CountCommand();
            case CMD_MOD:
                return new ModifyCommand(commandArguments);
        }
        throw new IllegalArgumentException("Wrong command");
    }

}