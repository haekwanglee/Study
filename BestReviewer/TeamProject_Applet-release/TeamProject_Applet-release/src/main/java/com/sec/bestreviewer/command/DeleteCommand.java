package com.sec.bestreviewer.command;

import com.sec.bestreviewer.TokenGroup;
import com.sec.bestreviewer.database.Row;
import com.sec.bestreviewer.database.Table;

import java.util.List;

public class DeleteCommand implements Command {

    private final SearchCommand searchCommand;

    public DeleteCommand(TokenGroup tokenGroup) {
        searchCommand = new SearchCommand(tokenGroup);
    }

    @Override
    public List<String> execute(Table employeeDatabase) {
        List<String> toBeDeleted = searchCommand.execute(employeeDatabase);
        toBeDeleted.stream()
                .map(str-> str.split(","))
                .map(Row::new)
                .forEach(employeeDatabase::remove);
        return toBeDeleted;
    }
}
