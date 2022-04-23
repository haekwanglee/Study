package com.sec.bestreviewer.store.filter;

import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.util.OptionParser;

public class EmployeeNumberFilter implements BaseFilter {

    @Override
    public boolean filter(Employee employee, String value, OptionParser optionParser) {
        return compare(employee.getEmployeeNumber(), value, optionParser);
    }

    @Override
    public int compare(String a, String b) {
        int yearA = Integer.parseInt(a.substring(0, 2));
        int yearB = Integer.parseInt(b.substring(0, 2));
        if (yearA < 20) yearA += 100;
        if (yearB < 20) yearB += 100;

        int restA = Integer.parseInt(a.substring(2));
        int restB = Integer.parseInt(b.substring(2));
        if (yearA != yearB) {
            return yearA - yearB;
        }
        return restA - restB;
    }
    
    @Override
    public Employee modify(Employee employee, String value) {
        throw new IllegalArgumentException("Illegal field: " + EmployeeStore.FIELD_EMPLOYEE_NUMBER);
    }
}
