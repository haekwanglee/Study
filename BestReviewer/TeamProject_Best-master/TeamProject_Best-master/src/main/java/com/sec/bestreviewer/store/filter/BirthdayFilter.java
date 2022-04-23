package com.sec.bestreviewer.store.filter;

import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.util.OptionParser;

public class BirthdayFilter implements BaseFilter {

    @Override
    public boolean filter(Employee employee, String value, OptionParser optionParser) {
        if (optionParser.getBirthDayOption().isYearOn()) {
            return compare(employee.getProfileBirthday().getYear(), value, optionParser);
        } else if (optionParser.getBirthDayOption().isMonthOn()) {
            return compare(employee.getProfileBirthday().getMonth(), value, optionParser);
        } else if (optionParser.getBirthDayOption().isDayOn()) {
            return compare(employee.getProfileBirthday().getDay(), value, optionParser);
        } else {
            // TODO 추가 필요
            return compare(employee.getProfileBirthday().getBirthday(), value, optionParser);
        }
    }

    @Override
    public Employee modify(Employee employee, String value) {
        Employee newEmployee = new Employee(employee);
        newEmployee.setBirthday(value);
        return newEmployee;
    }
}
