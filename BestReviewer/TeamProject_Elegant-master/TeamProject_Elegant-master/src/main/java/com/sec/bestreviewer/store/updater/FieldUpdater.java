package com.sec.bestreviewer.store.updater;

import com.sec.bestreviewer.store.Employee;

import java.util.List;

abstract public class FieldUpdater {
    List<Employee> employees;

    FieldUpdater(List<Employee> employees) {
        this.employees = employees;
    }

    abstract public List<Employee> update(String value);
}
