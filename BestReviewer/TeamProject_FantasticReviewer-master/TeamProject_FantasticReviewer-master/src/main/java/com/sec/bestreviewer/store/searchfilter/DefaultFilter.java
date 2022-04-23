package com.sec.bestreviewer.store.searchfilter;

import com.sec.bestreviewer.base.ConditionParameter;
import com.sec.bestreviewer.store.Employee;

import java.util.Collections;
import java.util.List;

public class DefaultFilter implements SearchFilterDelegator {

    @Override
    public List<Employee> search(List<Employee> employees, ConditionParameter param) {
        return Collections.emptyList();
    }
}
