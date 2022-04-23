package com.sec.bestreviewer.command;

import com.sec.bestreviewer.CommandFactory;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.store.filter.Filter;
import com.sec.bestreviewer.util.FilterFactory;
import com.sec.bestreviewer.util.OptionParser;
import com.sec.bestreviewer.util.ParamsParser;
import com.sec.bestreviewer.util.ResultStringFormatter;

import java.util.List;

import static com.sec.bestreviewer.CommandExecutor.MAX_RESULT_NUMBER;
import static com.sec.bestreviewer.util.ParamsParser.PairIndex.FIRST_PAIR;

public class SearchCommand implements Command {

    private final Filter filter;
    private final boolean isPrintOn;

    public SearchCommand(List<String> options, List<String> params) {
        OptionParser optionParser = new OptionParser(options, ParamsParser.parser(params, FIRST_PAIR).fieldName);
        isPrintOn = optionParser.getPrintOption().isOptionOn();
        filter = FilterFactory.buildFilter(params, optionParser);
    }

    @Override
    public List<String> execute(EmployeeStore employeeStore) {
        final List<Employee> employeeList = employeeStore.search(filter);
        if (isPrintOn) {
            return ResultStringFormatter.getEmployeeListToFormattedString(
                    employeeList, CommandFactory.CMD_SCH, MAX_RESULT_NUMBER);
        }
        return ResultStringFormatter.getEmployeeListToFormattedString(employeeList, CommandFactory.CMD_SCH);
    }
}
