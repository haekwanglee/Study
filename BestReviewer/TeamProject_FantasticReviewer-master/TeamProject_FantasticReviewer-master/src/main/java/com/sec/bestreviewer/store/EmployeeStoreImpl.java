package com.sec.bestreviewer.store;

import com.sec.bestreviewer.base.ConditionParameter;
import com.sec.bestreviewer.store.modifier.EmployeeModifier;
import com.sec.bestreviewer.store.modifier.EmployeeModifierFactory;
import com.sec.bestreviewer.store.searchfilter.SearchFilterFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class EmployeeStoreImpl implements EmployeeStore {
    private final List<Employee> employees = new ArrayList<>();

    @Override
    public void add(Employee employee) {
        employees.add(employee);
    }

    @Override
    public int count() {
        return employees.size();
    }

    @Override
    public List<Employee> modify(ConditionParameter param) {
        List<Employee> searched = search(param);
        List<Employee> clone = searched.stream()
                .map(Employee::new)
                .collect(Collectors.toList());

        EmployeeModifier modifier = EmployeeModifierFactory
                .create(Objects.requireNonNull(param.getModifyValue()));
        searched.forEach(modifier::modify);
        return clone;
    }

    @Override
    public List<Employee> search(ConditionParameter param) {
        return SearchFilterFactory.create(param)
                .search(Collections.unmodifiableList(employees), param).stream()
                .sorted(Comparator.comparing(Employee::getEmployeeNumberDateFormatYyyy))
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> delete(ConditionParameter param) {
        List<Employee> searchedEmployees = search(param);
        employees.removeAll(searchedEmployees);
        return searchedEmployees;
    }


}
