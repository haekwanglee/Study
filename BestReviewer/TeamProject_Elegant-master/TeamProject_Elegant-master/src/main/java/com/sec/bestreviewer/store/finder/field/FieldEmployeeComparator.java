package com.sec.bestreviewer.store.finder.field;

import com.sec.bestreviewer.store.Employee;

public class FieldEmployeeComparator extends FieldComparator {

    @Override
    public boolean equals(Employee target, String value) {
        return (target.compareEmployeeNumberValue(value) == 0);
    }

    @Override
    public boolean greaterThan(Employee target, String value) {
        return (target.compareEmployeeNumberValue(value) > 0);
    }

    @Override
    public boolean greaterThanOrEqualTo(Employee target, String value) {
        return (target.compareEmployeeNumberValue(value) >= 0);
    }

    @Override
    public boolean smallerThan(Employee target, String value) {
        return (target.compareEmployeeNumberValue(value) < 0);
    }

    @Override
    public boolean smallerThanOrEqualTo(Employee target, String value) {
        return (target.compareEmployeeNumberValue(value) <= 0);
    }
}
