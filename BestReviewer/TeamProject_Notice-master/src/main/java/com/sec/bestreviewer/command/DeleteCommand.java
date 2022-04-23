package com.sec.bestreviewer.command;

import com.sec.bestreviewer.CommandFactory;

import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.util.OptionParser;
import com.sec.bestreviewer.util.Pair;
import com.sec.bestreviewer.util.ResultStringFormatter;

import java.util.List;

import static com.sec.bestreviewer.CommandExecutor.MAX_RESULT_NUMBER;

public class DeleteCommand implements Command {
    private final Pair<String, String> conditionPair;
    private final OptionParser optionParser;

    public DeleteCommand(OptionParser optionParser, Pair<String, String> conditionPair) {
        this.conditionPair = conditionPair;
        this.optionParser = optionParser;
    }

    @Override
    public List<String> execute(EmployeeStore employeeStore) {
        final List<Employee> employeeList = employeeStore.delete(conditionPair.first, conditionPair.second);
        if (optionParser.isPrintOn()) {
            return ResultStringFormatter.getEmployeeListToFormattedString(
                    employeeList, CommandFactory.CMD_DEL, MAX_RESULT_NUMBER);
        }
        return ResultStringFormatter.getEmployeeListToFormattedString(employeeList, CommandFactory.CMD_DEL);
    }
}
