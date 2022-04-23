package com.sec.bestreviewer.store.updater;

import com.sec.bestreviewer.store.Employee;

import java.util.List;

public class FieldCertiUpdater extends FieldUpdater {

    public FieldCertiUpdater(List<Employee> employees) {
        super(employees);
    }

    @Override
    public List<Employee> update(String value) {
        employees.forEach(employee -> employee.setCerti(value));
        return employees;
    }
}
