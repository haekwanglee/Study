package com.sec.bestreviewer.store.finder.field;

import com.sec.bestreviewer.store.Employee;

public class FieldLastPhoneNumberComparator extends FieldPhoneNumberComparator {

    @Override
    public boolean equals(Employee target, String value) {
        return getTargetNumber(target) == Integer.parseInt(value);
    }

    @Override
    public boolean greaterThan(Employee target, String value) {
        return getTargetNumber(target) > Integer.parseInt(value);
    }

    @Override
    public boolean greaterThanOrEqualTo(Employee target, String value) {
        return getTargetNumber(target) >= Integer.parseInt(value);
    }

    @Override
    public boolean smallerThan(Employee target, String value) {
        return getTargetNumber(target) < Integer.parseInt(value);
    }

    @Override
    public boolean smallerThanOrEqualTo(Employee target, String value) {
        return getTargetNumber(target) <= Integer.parseInt(value);
    }

    protected int getTargetNumber(Employee target) {
        return target.getLastPhoneNumber();
    }
}
