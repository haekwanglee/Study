package com.sec.bestreviewer.command;

import com.sec.bestreviewer.CommandFactory;
import com.sec.bestreviewer.store.ConditionParam;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.util.OptionParser;
import com.sec.bestreviewer.util.Pair;
import com.sec.bestreviewer.util.ResultStringFormatter;

import java.util.List;

import static com.sec.bestreviewer.CommandExecutor.MAX_RESULT_NUMBER;

public class SearchCommand extends Command {
    public SearchCommand(OptionParser optionParser, List<Pair<String, String>> conditionPairs) {
        this.conditionPairs = conditionPairs;
        this.optionParser = optionParser;
    }

    @Override
    public List<String> execute(EmployeeStore employeeStore) {
        ConditionParam conditionParam = getConditionParam();
        final List<Employee> employeeList;
        if (isAndOrOperation()) {
            ConditionParam secondConditionParam = getSecondConditionParam();

            if (optionParser.isAndOperation()) {
                employeeList = employeeStore.searchAnd(conditionParam, secondConditionParam);
            } else {
                employeeList = employeeStore.searchOr(conditionParam, secondConditionParam);
            }
        } else {
            employeeList = employeeStore.search(conditionParam);
        }

        if (optionParser.isPrintOn()) {
            return ResultStringFormatter.getEmployeeListToFormattedString(
                    employeeList, CommandFactory.CMD_SCH, MAX_RESULT_NUMBER);
        }
        return ResultStringFormatter.getEmployeeListToFormattedString(employeeList, CommandFactory.CMD_SCH);
    }
}
