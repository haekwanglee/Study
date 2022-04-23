package com.sec.bestreviewer.command;

import com.sec.bestreviewer.CommandFactory;
import com.sec.bestreviewer.command.querycondition.QueryCondition;
import com.sec.bestreviewer.command.queryprocessor.QueryProcessor;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.util.OptionParser;
import com.sec.bestreviewer.util.Pair;
import com.sec.bestreviewer.util.ResultStringFormatter;

import java.util.List;

import static com.sec.bestreviewer.CommandExecutor.MAX_RESULT_NUMBER;

public class ModifyCommand implements Command {
    private final OptionParser optionParser;
    private List<QueryCondition> queryConditions;
    private String joinOperator;
    private Pair<String, String> newFieldValue;

    public ModifyCommand(OptionParser optionParser, List<QueryCondition> queryConditions, String joinOperator, Pair<String, String> newFieldValue) {
        this.optionParser = optionParser;
        this.queryConditions = queryConditions;
        this.joinOperator = joinOperator;
        this.newFieldValue = newFieldValue;
    }

    @Override
    public List<String> execute(EmployeeStore employeeStore) {
        QueryProcessor queryProcessor = new QueryProcessor(queryConditions, joinOperator);
        final List<Employee> employeeList = employeeStore.modify(queryProcessor, newFieldValue);

        if (optionParser.isPrintOn()) {
            return ResultStringFormatter.getEmployeeListToFormattedString(
                    employeeList, CommandFactory.CMD_MOD, MAX_RESULT_NUMBER);
        }

        return ResultStringFormatter.getEmployeeListToFormattedString(employeeList, CommandFactory.CMD_MOD);
    }
}
