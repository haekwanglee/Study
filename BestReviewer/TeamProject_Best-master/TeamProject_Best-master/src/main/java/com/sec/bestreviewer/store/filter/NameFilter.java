package com.sec.bestreviewer.store.filter;

import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.util.OptionParser;

public class NameFilter implements BaseFilter {

    @Override
    public boolean filter(Employee employee, String value, OptionParser optionParser) {
        if (optionParser.getNameOption().isFirstNameOn()) {
            return compare(employee.getProfileName().getFirstName(), value, optionParser);
        } else if (optionParser.getNameOption().isLastNameOn()) {
            return compare(employee.getProfileName().getLastName(), value, optionParser);
        } else {
            return compare(employee.getProfileName().getFullName(), value, optionParser);
        }
    }

    @Override
    public Employee modify(Employee employee, String value) {
        Employee newEmployee = new Employee(employee);
        newEmployee.setName(value);
        return newEmployee;
    }
}
