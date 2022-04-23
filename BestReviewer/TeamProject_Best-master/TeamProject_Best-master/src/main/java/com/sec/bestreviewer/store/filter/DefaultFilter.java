package com.sec.bestreviewer.store.filter;

import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.util.OptionParser;

public class DefaultFilter implements BaseFilter {

    @Override
    public boolean filter(Employee employee, String value, OptionParser optionParser) {
        return false;
    }

    @Override
    public Employee modify(Employee employee, String value) {
        throw new IllegalArgumentException("Unknown field");
    }
}
