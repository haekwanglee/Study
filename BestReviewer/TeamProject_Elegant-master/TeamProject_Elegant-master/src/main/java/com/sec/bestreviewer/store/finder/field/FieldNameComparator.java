package com.sec.bestreviewer.store.finder.field;

import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.OptionAttributeType;

public class FieldNameComparator extends FieldComparator {

    protected static FieldNameComparator createFieldNameComparator(OptionAttributeType optionAttributeType) {
        switch (optionAttributeType) {
            case ATTRIBUTE_FIRST_NAME:
                return new FieldFirstNameComparator();
            case ATTRIBUTE_LAST_NAME:
                return new FieldLastNameComparator();
            default:
                return new FieldNameComparator();
        }
    }


    protected String getTargetName(Employee target) {
        return target.getName();
    }

    @Override
    public boolean equals(Employee target, String value) {
        return getTargetName(target).equals(value);
    }

    @Override
    public boolean greaterThan(Employee target, String value) {
        return (getTargetName(target).compareTo(value) > 0);
    }

    @Override
    public boolean greaterThanOrEqualTo(Employee target, String value) {
        return (getTargetName(target).compareTo(value) >= 0);
    }

    @Override
    public boolean smallerThan(Employee target, String value) {
        return (getTargetName(target).compareTo(value) < 0);
    }

    @Override
    public boolean smallerThanOrEqualTo(Employee target, String value) {
        return (getTargetName(target).compareTo(value) <= 0);
    }
}
