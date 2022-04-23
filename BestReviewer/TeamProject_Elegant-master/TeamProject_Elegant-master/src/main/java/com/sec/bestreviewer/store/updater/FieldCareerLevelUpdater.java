package com.sec.bestreviewer.store.updater;

import com.sec.bestreviewer.store.Employee;

import java.util.List;

public class FieldCareerLevelUpdater extends FieldUpdater {

    public FieldCareerLevelUpdater(List<Employee> employees) {
        super(employees);
    }

    @Override
    public List<Employee> update(String value) {
        employees.forEach(employee -> employee.setCareerLevel(value));
        return employees;
    }
}
