package com.sec.bestreviewer.command;

import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.util.OptionParser;
import com.sec.bestreviewer.util.ResultStringFormatter;

import java.util.List;

import static com.sec.bestreviewer.CommandExecutor.MAX_RESULT_NUMBER;

public class CommandPrinter {
    public static List<String> printFormattedString(OptionParser optionParser, String command, List<Employee> employeeList) {
        if (optionParser.isPrintOn()) {
            return ResultStringFormatter.getEmployeeListToFormattedString(
                    employeeList, command, MAX_RESULT_NUMBER);
        }
        return ResultStringFormatter.getEmployeeListToFormattedString(employeeList, command);
    }
}
