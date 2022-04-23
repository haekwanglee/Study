package com.sec.bestreviewer.store.modifier;

import com.sec.bestreviewer.store.Employee;

public class EmployeeNameModifier implements EmployeeModifier {
    private final String name;

    public EmployeeNameModifier(String name) {
        this.name = name;
    }

    @Override
    public Employee modify(Employee employee) {
        employee.setName(name);
        return employee;
    }

    public String getName() {
        return name;
    }
}
