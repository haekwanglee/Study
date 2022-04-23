package com.sec.bestreviewer.command;

import com.sec.bestreviewer.data.CommandData;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;

import java.util.Collections;
import java.util.List;

public class AddCommand extends Command {
    private final Employee employee;

    public AddCommand(CommandData commandData) {
        super(commandData);
        List<String> addDataList = commandData.getAddDataList();
        this.employee = new Employee(addDataList.get(0), addDataList.get(1), addDataList.get(2),
                addDataList.get(3), addDataList.get(4), addDataList.get(5));
    }

    @Override
    public List<String> execute(EmployeeStore employeeStore) {
        employeeStore.add(employee);
        return Collections.emptyList();
    }
}