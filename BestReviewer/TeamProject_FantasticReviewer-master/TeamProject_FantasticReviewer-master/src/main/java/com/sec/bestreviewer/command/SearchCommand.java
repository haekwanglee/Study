package com.sec.bestreviewer.command;

import com.sec.bestreviewer.CommandFactory;
import com.sec.bestreviewer.base.ConditionParameter;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.util.OptionParser;

import java.util.List;

public class SearchCommand implements Command {
    private final ConditionParameter conditionParameter;


    public SearchCommand(ConditionParameter conditionParameter) {
        this.conditionParameter = conditionParameter;
    }

    @Override
    public List<String> execute(EmployeeStore employeeStore) {
        final List<Employee> employeeList = employeeStore.search(conditionParameter);
        OptionParser optionParser = new OptionParser(List.of(conditionParameter.getFirst().getOption1()));
        return CommandPrinter.printFormattedString(optionParser, CommandFactory.CMD_SCH, employeeList);
    }
}
