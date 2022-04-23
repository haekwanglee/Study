package com.sec.bestreviewer.store.searchfilter;

import com.sec.bestreviewer.store.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class MiddlePhoneNumberFilter extends SearchFilterSearcher {

    private static final int MIDDLE_PHONE_NUMBER_INDEX_START = 4;
    private static final int MIDDLE_PHONE_NUMBER_INDEX_END = 8;

    protected List<Employee> searchEquals(List<Employee> employees, String value) {
        return employees.stream()
                .filter(it -> getMiddlePhoneNumber(it.getPhoneNumber()).equals(value))
                .collect(Collectors.toList());
    }

    private String getMiddlePhoneNumber(String phoneNumber) {
        return phoneNumber.substring(MIDDLE_PHONE_NUMBER_INDEX_START, MIDDLE_PHONE_NUMBER_INDEX_END);
    }

    @Override
    public String getIndexKey(Employee employee) {
        return getMiddlePhoneNumber(employee.getPhoneNumber());
    }
}
