package com.sec.bestreviewer.store.searchfilter;

import com.sec.bestreviewer.store.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class LastPhoneNumberFilter extends SearchFilterSearcher {

    private static final int LAST_PHONE_NUMBER_INDEX_START = 9;
    private static final int LAST_PHONE_NUMBER_INDEX_END = 13;

    @Override
    protected List<Employee> searchEquals(List<Employee> employees, String value) {
        return employees.stream()
                .filter(it -> getLastPhoneNumber(it.getPhoneNumber()).equals(value))
                .collect(Collectors.toList());
    }

    private String getLastPhoneNumber(String phoneNumber) {
        return phoneNumber.substring(LAST_PHONE_NUMBER_INDEX_START, LAST_PHONE_NUMBER_INDEX_END);
    }

    @Override
    public String getIndexKey(Employee employee) {
        return getLastPhoneNumber(employee.getPhoneNumber());
    }
}
