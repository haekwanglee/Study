package com.sec.bestreviewer.store.filter;

import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.util.OptionParser;

public class CertiFilter implements BaseFilter {

    enum EnumCerti {
        ADV("ADV", 0), PRO("PRO", 1), EX("EX", 2);
        final String certi;
        final int value;

        EnumCerti(String certi, int value) {
            this.certi = certi;
            this.value = value;
        }
    }

    @Override
    public boolean filter(Employee employee, String value, OptionParser optionParser) {
        return compare(employee.getCerti(), value, optionParser);
    }

    @Override
    public int compare(String a, String b) {
        return EnumCerti.valueOf(a).value - EnumCerti.valueOf(b).value;
    }

    @Override
    public Employee modify(Employee employee, String value) {
        Employee newEmployee = new Employee(employee);
        newEmployee.setCerti(value);
        return newEmployee;
    }
}
