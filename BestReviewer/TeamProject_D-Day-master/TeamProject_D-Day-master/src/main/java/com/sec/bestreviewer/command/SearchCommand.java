package com.sec.bestreviewer.command;

import com.sec.bestreviewer.CommandFactory;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.util.arguments.CommandArguments;
import com.sec.bestreviewer.util.OptionParser;
import com.sec.bestreviewer.util.ResultStringFormatter;

import java.util.List;

import static com.sec.bestreviewer.CommandExecutor.MAX_RESULT_NUMBER;

public class SearchCommand implements Command {
    CommandArguments commandArguments;

    public SearchCommand(CommandArguments commandArguments) {
        initCommand(commandArguments);
    }

    private void initCommand(CommandArguments commandArguments) {
        this.commandArguments = commandArguments;
    }

    @Override
    public List<String> execute(EmployeeStore employeeStore) {
        SearchHelper helper = new SearchHelper(commandArguments, employeeStore);
        final List<Employee> employeeList = helper.search();

        if (isPrintOn()) {
            return ResultStringFormatter.getEmployeeListToFormattedString(
                    employeeList, CommandFactory.CMD_SCH, MAX_RESULT_NUMBER);
        }

        return ResultStringFormatter.getEmployeeListToFormattedString(employeeList, CommandFactory.CMD_SCH);
    }

    private boolean isPrintOn() {
        List<OptionParser> optionParserList = commandArguments.getOptionList();
        OptionParser firstOptionParser = optionParserList.get(0);
        return firstOptionParser.isPrintOn();
    }
}