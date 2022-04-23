package com.sec.bestreviewer.store.filter;

import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.util.OptionParser;

public class CareerLevelFilter implements BaseFilter {

    @Override
    public boolean filter(Employee employee, String value, OptionParser optionParser) {
        return compare(employee.getCareerLevel(), value, optionParser);
    }

    @Override
    public Employee modify(Employee employee, String value) {
        Employee newEmployee = new Employee(employee);
        newEmployee.setCareerLevel(value);
        return newEmployee;
    }
}
