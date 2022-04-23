package com.sec.bestreviewer.store.filter;

import com.sec.bestreviewer.store.Employee;

public class AndFilter implements Filter {

    private final Filter singleFilter1;
    private final Filter singleFilter2;

    public AndFilter(Filter singleFilter1, Filter singleFilter2) {
        this.singleFilter1 = singleFilter1;
        this.singleFilter2 = singleFilter2;
    }

    @Override
    public boolean predicate(Employee employee) {
        return singleFilter1.predicate(employee) && singleFilter2.predicate(employee);
    }
}
