package com.sec.bestreviewer.store.finder.field;

import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.OptionAttributeType;

public class FieldBirthdayComparator extends FieldComparator {

    protected static FieldBirthdayComparator createFieldBirthdayComparator(OptionAttributeType optionAttributeType) {
        switch (optionAttributeType) {
            case ATTRIBUTE_YEAR:
                return new FieldYearComparator();
            case ATTRIBUTE_MONTH:
                return new FieldMonthComparator();
            case ATTRIBUTE_DAY:
                return new FieldDayComparator();
            default:
                return new FieldBirthdayComparator();
        }
    }

    @Override
    public boolean equals(Employee target, String value) {
        return target.getBirthday().equals(value);
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
        return Integer.parseInt(target.getBirthday());
    }

}
