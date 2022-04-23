package com.sec.bestreviewer.store.modifier;

import com.sec.bestreviewer.store.Employee;

public class EmployeeCertiModifier implements EmployeeModifier {
    private final String certi;

    public EmployeeCertiModifier(String certi) {
        this.certi = certi;
    }

    @Override
    public Employee modify(Employee employee) {
        employee.setCerti(certi);
        return employee;
    }

    public String getCerti() {
        return certi;
    }
}
