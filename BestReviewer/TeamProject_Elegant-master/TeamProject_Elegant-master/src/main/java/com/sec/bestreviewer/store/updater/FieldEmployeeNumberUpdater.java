package com.sec.bestreviewer.store.updater;

import com.sec.bestreviewer.store.Employee;

import java.util.List;

public class FieldEmployeeNumberUpdater extends FieldUpdater {

    public FieldEmployeeNumberUpdater(List<Employee> employees) {
        super(employees);
    }

    @Override
    public List<Employee> update(String value) {
        employees.forEach(employee -> employee.setEmployeeNumber(value));
        return employees;
    }
}
