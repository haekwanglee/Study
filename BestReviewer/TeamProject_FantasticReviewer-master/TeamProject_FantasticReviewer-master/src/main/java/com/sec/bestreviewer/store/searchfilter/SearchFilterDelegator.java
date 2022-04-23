package com.sec.bestreviewer.store.searchfilter;

import com.sec.bestreviewer.base.ConditionParameter;
import com.sec.bestreviewer.store.Employee;

import java.util.List;


public interface SearchFilterDelegator {
    String REGEX_FIRST_LAST_NAME = " ";

    List<Employee> search(List<Employee> employees, ConditionParameter param);
}


