package com.sec.bestreviewer.command;

import com.sec.bestreviewer.CommandFactory;
import com.sec.bestreviewer.data.CommandData;
import com.sec.bestreviewer.store.EmployeeStore;

import java.util.Collections;
import java.util.List;

public class CountCommand extends Command {
    public CountCommand() {
        super(null);
    }

    public CountCommand(CommandData commandData) {
        super(commandData);
    }

    @Override
    public List<String> execute(EmployeeStore employeeStore) {
        return Collections.singletonList(CommandFactory.CMD_CNT + "," + String.valueOf(employeeStore.count()));
    }
}
