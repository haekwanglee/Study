package com.sec.bestreviewer.command;

import com.sec.bestreviewer.CommandFactory;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.util.OptionParser;
import com.sec.bestreviewer.util.Pair;
import com.sec.bestreviewer.util.ResultStringFormatter;
import com.sec.bestreviewer.util.arguments.CommandArguments;

import java.util.*;
import java.util.stream.Collectors;

import static com.sec.bestreviewer.CommandExecutor.MAX_RESULT_NUMBER;

public class ModifyCommand implements Command {
    CommandArguments commandArguments;

    public ModifyCommand(CommandArguments commandArguments) {
        this.commandArguments = commandArguments;
    }

    @Override
    public List<String> execute(EmployeeStore employeeStore) {
        SearchHelper searchHelper = new SearchHelper(commandArguments, employeeStore);
        List<Employee> listModifyNeeded = searchHelper.search();
        List listBeforeModify = cloneList(listModifyNeeded);
        Pair<String, String> changeAsThis = commandArguments.getTargetParam();
        employeeStore.modify(changeAsThis.first, changeAsThis.second, listModifyNeeded);
        if (isPrintOn()) {
            return ResultStringFormatter.getEmployeeListToFormattedString(
                    listBeforeModify, CommandFactory.CMD_MOD, MAX_RESULT_NUMBER);
        }
        return ResultStringFormatter.getEmployeeListToFormattedString(listModifyNeeded, CommandFactory.CMD_MOD);
    }

    private List<Employee> cloneList(List<Employee> listModifyNeeded) {
        return listModifyNeeded.stream()
                .map(n -> new Employee(n))
                .collect(Collectors.toList());
    }

    private boolean isPrintOn() {
        List<OptionParser> optionParserList = commandArguments.getOptionList();
        OptionParser firstOptionParser = optionParserList.get(0);
        return firstOptionParser.isPrintOn();
    }
}