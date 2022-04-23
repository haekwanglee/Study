package com.sec.bestreviewer.command;

import com.sec.bestreviewer.store.Employee;

import java.util.List;

public interface Set {
    List<Employee> execute(List<Employee> list1, List<Employee> list2);
}
