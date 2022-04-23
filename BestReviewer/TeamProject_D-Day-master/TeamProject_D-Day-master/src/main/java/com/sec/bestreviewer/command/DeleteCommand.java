package com.sec.bestreviewer.command;

import com.sec.bestreviewer.CommandFactory;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.util.ResultStringFormatter;
import com.sec.bestreviewer.util.arguments.CommandArguments;

import java.util.List;

import static com.sec.bestreviewer.CommandExecutor.MAX_RESULT_NUMBER;

public class DeleteCommand implements Command {
    private CommandArguments commandArguments;
    private boolean isPrintOn;

    public DeleteCommand(CommandArguments commandArguments) {
        this.commandArguments = commandArguments;
        this.isPrintOn = commandArguments.getOptionList().get(0).isPrintOn();
    }

    @Override
    public List<String> execute(EmployeeStore employeeStore) {
        SearchHelper searchHelper = new SearchHelper(commandArguments, employeeStore);
        final List<Employee> searchResult = searchHelper.search();
        employeeStore.delete(searchResult);
        if (isPrintOn) {
            return ResultStringFormatter.getEmployeeListToFormattedString(searchResult, CommandFactory.CMD_DEL, MAX_RESULT_NUMBER);
        }
        return ResultStringFormatter.getEmployeeListToFormattedString(searchResult, CommandFactory.CMD_DEL);
    }
}
