package com.sec.bestreviewer.command.queryprocessor;

import com.sec.bestreviewer.store.Employee;

public interface FieldOptionHandler {
    String process(String option, Employee employee);
}
