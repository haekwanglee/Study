package com.sec.bestreviewer.store.finder.field;

import com.sec.bestreviewer.store.Employee;

import java.util.HashMap;

public class FieldCertiComparator extends FieldComparator {

    HashMap<String, Integer> compareMap = new HashMap<>();
    public FieldCertiComparator() {
        compareMap.put("ADV", 0);
        compareMap.put("PRO", 1);
        compareMap.put("EX", 2);
    }

    @Override
    public boolean equals(Employee target, String value) {
        String targetString = target.getCerti();
        return targetString.equals(value);
    }

    @Override
    public boolean greaterThan(Employee target, String value) {
        return (compareMap.get(target.getCerti()) > compareMap.get(value));
    }

    @Override
    public boolean greaterThanOrEqualTo(Employee target, String value) {
        return (compareMap.get(target.getCerti()) >= compareMap.get(value));
    }

    @Override
    public boolean smallerThan(Employee target, String value) {
        return (compareMap.get(target.getCerti()) < compareMap.get(value));
    }

    @Override
    public boolean smallerThanOrEqualTo(Employee target, String value) {
        return (compareMap.get(target.getCerti()) <= compareMap.get(value));
    }
}
