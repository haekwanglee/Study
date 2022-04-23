package com.sec.bestreviewer.store.finder.field;

import com.sec.bestreviewer.store.Employee;

public class FieldMonthComparator extends FieldBirthdayComparator {

    @Override
    public boolean equals(Employee target, String value) {
        return target.getMonthOfBirthday() == Integer.parseInt(value);
    }

    @Override
    protected int getTargetNumber(Employee target) {
        return target.getMonthOfBirthday();
    }
}
