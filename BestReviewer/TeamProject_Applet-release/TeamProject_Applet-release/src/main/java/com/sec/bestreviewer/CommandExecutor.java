package com.sec.bestreviewer;

import com.sec.bestreviewer.command.Command;
import com.sec.bestreviewer.database.Table;
import com.sec.bestreviewer.store.Injection;

import java.util.List;

public class CommandExecutor {

    private final Table employeeDatabase;

    CommandExecutor() {
        this.employeeDatabase = Injection.provideEmployeeDatabase();
    }

    CommandExecutor(Table employeeDatabase) {
        this.employeeDatabase = employeeDatabase;
    }


    List<String> execute(Command command) {
        return command.execute(employeeDatabase);
    }
}