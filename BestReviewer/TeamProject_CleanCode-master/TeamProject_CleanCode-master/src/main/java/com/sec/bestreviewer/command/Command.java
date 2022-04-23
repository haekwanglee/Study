package com.sec.bestreviewer.command;

import com.sec.bestreviewer.data.CommandData;
import com.sec.bestreviewer.store.EmployeeStore;

import java.util.List;

public abstract class Command {
    public Command(CommandData commandData) {
        this.commandData = commandData;
    }

    protected CommandData commandData;
    public abstract List<String> execute(EmployeeStore employeeStore);

    boolean isPrintOn() {
        return (commandData != null && commandData.isPrintOn());
    }
}
