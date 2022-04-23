package com.sec.bestreviewer.command;

import com.sec.bestreviewer.CommandFactory;
import com.sec.bestreviewer.data.CommandData;
import com.sec.bestreviewer.data.ModifyData;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.util.ResultStringFormatter;

import java.util.Collections;
import java.util.List;

import static com.sec.bestreviewer.CommandExecutor.MAX_RESULT_NUMBER;

public class ModifyCommand extends Command {
    public ModifyCommand(CommandData commandData) {
        super(commandData);
    }

    @Override
    public List<String> execute(EmployeeStore employeeStore) {
        SearchCommand searchCommand = new SearchCommand(commandData);
        List<Employee> employeeList = searchCommand.getSearchedEmployees(employeeStore);
        ModifyData modifyData = commandData.getModifyData();;
        employeeList = employeeStore.modify(employeeList, modifyData.getColumnName(), modifyData.getValue());

        if (isPrintOn()) {
            return ResultStringFormatter.getEmployeeListToFormattedString(
                    employeeList, CommandFactory.CMD_MOD, MAX_RESULT_NUMBER);
        }
        return Collections.singletonList(CommandFactory.CMD_MOD + "," + getEmployeeSizeString(employeeList));
    }

    private Object getEmployeeSizeString(List<Employee> employeeList) {
        return employeeList.isEmpty() ? "NONE" : employeeList.size();
    }
}
