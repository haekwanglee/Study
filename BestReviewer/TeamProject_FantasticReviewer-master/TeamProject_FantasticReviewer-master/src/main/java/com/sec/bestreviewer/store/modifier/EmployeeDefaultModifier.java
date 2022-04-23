package com.sec.bestreviewer.store.modifier;

import com.sec.bestreviewer.store.Employee;

public class EmployeeDefaultModifier implements EmployeeModifier {
    @Override
    public Employee modify(Employee employee) {
        return employee;
    }
}
