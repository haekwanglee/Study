package com.sec.bestreviewer.command;

import com.sec.bestreviewer.CommandFactory;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.store.filter.Filter;
import com.sec.bestreviewer.util.*;

import java.util.List;

import static com.sec.bestreviewer.CommandExecutor.MAX_RESULT_NUMBER;
import static com.sec.bestreviewer.util.ParamsParser.PairIndex.*;

public class ModifyCommand implements Command {
    private final Filter filter;
    private final boolean isPrintOn;
    private final Pair<String, String> modifyPair;

    public ModifyCommand(List<String> options, List<String> params) {
        OptionParser optionParser = new OptionParser(options, ParamsParser.parser(params, FIRST_PAIR).fieldName);
        isPrintOn = optionParser.getPrintOption().isOptionOn();
        filter = FilterFactory.buildFilter(params, optionParser);

        if (optionParser.getOrOption().isOrOn() || optionParser.getAndOption().isAndOn()) {
            modifyPair = ParamsParser.parser(params, THIRD_PAIR);
        } else {
            modifyPair = ParamsParser.parser(params, SECOND_PAIR);
        }
    }

    @Override
    public List<String> execute(EmployeeStore employeeStore) {
        final List<Employee> employeeList = employeeStore.modify(filter, modifyPair);
        if (isPrintOn) {
            return ResultStringFormatter.getEmployeeListToFormattedString(
                    employeeList, CommandFactory.CMD_MOD, MAX_RESULT_NUMBER);
        }
        return ResultStringFormatter.getEmployeeListToFormattedString(employeeList, CommandFactory.CMD_MOD);
    }
}
