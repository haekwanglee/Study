package com.sec.bestreviewer.command;

import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.util.OptionParser;
import com.sec.bestreviewer.util.Pair;
import com.sec.bestreviewer.util.arguments.CommandArguments;
import com.sec.bestreviewer.util.arguments.CommandOptionSeparator;

import java.util.List;

public class SearchHelper {
    CommandArguments commandArguments;
    EmployeeStore employeeStore;

    public SearchHelper(CommandArguments commandArguments, EmployeeStore employeeStore) {
        this.commandArguments = commandArguments;
        this.employeeStore = employeeStore;
    }

    public List<Employee> search() {
        List<Employee> firstResultList = searchWithFirst(employeeStore);

        if (!hasMoreCommand()) {
            return firstResultList;
        }
        Set resultSet = SetFactory.createSet(commandArguments.getCommandMode());
        return resultSet.execute(firstResultList, searchWithSecond(employeeStore));
    }

    private boolean hasMoreCommand() {
        return commandArguments.getCommandMode() == CommandOptionSeparator.AND_COMMAND ||
                commandArguments.getCommandMode() == CommandOptionSeparator.OR_COMMAND;
    }

    private List<Employee> searchWithFirst(EmployeeStore employeeStore) {
        return searchWithConditionIndex(employeeStore, 0);
    }

    private List<Employee> searchWithSecond(EmployeeStore employeeStore) {
        return searchWithConditionIndex(employeeStore, 1);
    }

    private List<Employee> searchWithConditionIndex(EmployeeStore employeeStore, int index) {
        List<Pair> conditionPairs = commandArguments.getConditionParamList();
        Pair<String, String> conditionPair = conditionPairs.get(index);
        OptionParser optionParser = commandArguments.getOptionList().get(index);
        final List<Employee> employeeList = employeeStore.search(conditionPair.first, conditionPair.second,
                optionParser.getCompareOption(), optionParser.getFilterOption());

        return employeeList;
    }
}