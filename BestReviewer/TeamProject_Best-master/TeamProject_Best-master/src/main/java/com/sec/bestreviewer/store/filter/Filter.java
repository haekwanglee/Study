package com.sec.bestreviewer.store.filter;

import com.sec.bestreviewer.store.Employee;

public interface Filter {
    boolean predicate(Employee employee);
}
