package com.sec.bestreviewer.store.finder.field;

import com.sec.bestreviewer.store.Employee;

public class FieldDayComparator extends FieldBirthdayComparator {

    @Override
    public boolean equals(Employee target, String value) {
        return target.getDayOfBirthday() == Integer.parseInt(value);
    }

    @Override
    protected int getTargetNumber(Employee target) {
        return target.getDayOfBirthday();
    }
}
