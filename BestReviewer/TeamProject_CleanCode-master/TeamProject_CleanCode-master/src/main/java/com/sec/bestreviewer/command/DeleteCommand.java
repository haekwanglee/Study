package com.sec.bestreviewer.command;

import com.sec.bestreviewer.CommandFactory;
import com.sec.bestreviewer.data.CommandData;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.util.ResultStringFormatter;

import java.util.List;

import static com.sec.bestreviewer.CommandExecutor.MAX_RESULT_NUMBER;

public class DeleteCommand extends Command {
    public DeleteCommand(CommandData commandData) {
        super(commandData);
    }

    @Override
    public List<String> execute(EmployeeStore employeeStore) {
        SearchCommand searchCommand = new SearchCommand(commandData);
        List<Employee> employeeList = searchCommand.getSearchedEmployees(employeeStore);
        employeeList = employeeStore.delete(employeeList);

        if (isPrintOn()) {
            return ResultStringFormatter.getEmployeeListToFormattedString(
                    employeeList, CommandFactory.CMD_DEL, MAX_RESULT_NUMBER);
        }
        return ResultStringFormatter.getEmployeeListToFormattedString(employeeList, CommandFactory.CMD_DEL);
    }
}
