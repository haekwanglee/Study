package com.sec.bestreviewer;

import com.sec.bestreviewer.command.*;

public class CommandFactory {
    public Command buildCommand(CommandDTO commandDTO) throws IllegalArgumentException {
        switch (commandDTO.getType()) {
            case ADD:
                return new AddCommand(commandDTO);
            case DEL:
                return new DeleteCommand(commandDTO);
            case SCH:
                return new SearchCommand(commandDTO);
            case CNT:
                return new CountCommand(commandDTO);
            case MOD:
                return new ModifyCommand(commandDTO);
        }
        throw new IllegalArgumentException("Wrong command");
    }
}
