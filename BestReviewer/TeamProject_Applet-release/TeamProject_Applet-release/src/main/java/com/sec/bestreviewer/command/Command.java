package com.sec.bestreviewer.command;

import com.sec.bestreviewer.database.Table;

import java.util.List;

public interface Command {
    List<String> execute(Table employeeDatabase);
}
