package com.sec.bestreviewer.command;

import com.sec.bestreviewer.CommandFactory;
import com.sec.bestreviewer.store.ConditionParam;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.util.OptionParser;
import com.sec.bestreviewer.util.Pair;
import com.sec.bestreviewer.util.ResultStringFormatter;

import java.util.ArrayList;
import java.util.List;

import static com.sec.bestreviewer.CommandExecutor.MAX_RESULT_NUMBER;

public class ModifyCommand extends Command {
    private final Pair<String, String> newValuePair;

    public ModifyCommand(OptionParser optionParser, List<Pair<String, String>> conditionPairs, Pair<String, String> newValuePair) {
        this.conditionPairs = conditionPairs;
        this.optionParser = optionParser;
        this.newValuePair = newValuePair;
    }

    @Override
    public List<String> execute(EmployeeStore employeeStore) {
        ConditionParam conditionParam = getConditionParam();
        final List<Employee> employeeList;
        List<String> result;

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
            result = ResultStringFormatter.getEmployeeListToFormattedString(
                    employeeList, CommandFactory.CMD_MOD, MAX_RESULT_NUMBER);
        } else {
            result = ResultStringFormatter.getEmployeeListToFormattedString(employeeList, CommandFactory.CMD_MOD);
        }

        employeeStore.modify(employeeList, newValuePair.first, newValuePair.second);

        return result;
    }
}