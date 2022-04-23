package com.sec.bestreviewer.store.finder.field;

import com.sec.bestreviewer.store.Employee;

public class FieldCareerLevelComparator extends FieldComparator {

    @Override
    public boolean equals(Employee target, String value) {
        String targetString = target.getCareerLevel();
        return targetString.equals(value);
    }

    @Override
    public boolean greaterThan(Employee target, String value) {
        return (target.getCareerLevel().compareTo(value) > 0);
    }

    @Override
    public boolean greaterThanOrEqualTo(Employee target, String value) {
        return (target.getCareerLevel().compareTo(value) >= 0);
    }

    @Override
    public boolean smallerThan(Employee target, String value) {
        return (target.getCareerLevel().compareTo(value) < 0);
    }

    @Override
    public boolean smallerThanOrEqualTo(Employee target, String value) {
        return (target.getCareerLevel().compareTo(value) <= 0);
    }
}
