package com.sec.bestreviewer.command;

import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;

import java.util.Collections;
import java.util.List;

public class AddCommand implements Command {
    private final Employee employee;

    public AddCommand(Employee employee) {
        this.employee = employee;
    }

    @Override
    public List<String> execute(EmployeeStore employeeStore) {
        employeeStore.add(employee);
        return Collections.emptyList();
    }
}