package com.sec.bestreviewer.command;


import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.util.OptionParser;

import java.util.Collections;
import java.util.List;

public class AddCommand implements Command {
    private final Employee employee;

    public AddCommand(List<String> params) {
        this.employee = new Employee(params.get(0), params.get(1), params.get(2), params.get(3), params.get(4), params.get(5));
    }

    @Override
    public List<String> execute(EmployeeStore employeeStore) {
        employeeStore.add(employee);
        return Collections.emptyList();
    }
}