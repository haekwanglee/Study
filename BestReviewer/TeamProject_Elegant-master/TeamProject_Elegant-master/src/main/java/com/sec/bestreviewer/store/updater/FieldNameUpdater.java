package com.sec.bestreviewer.store.updater;

import com.sec.bestreviewer.store.Employee;

import java.util.List;

public class FieldNameUpdater extends FieldUpdater {

    public FieldNameUpdater(List<Employee> employees) {
        super(employees);
    }

    @Override
    public List<Employee> update(String value) {
        employees.forEach(employee -> employee.setName(value));
        return employees;
    }
}
