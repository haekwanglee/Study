package com.sec.bestreviewer.store.finder.field;

import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.OptionAttributeType;

public class FieldPhoneNumberComparator extends FieldComparator {

    protected static FieldPhoneNumberComparator createFieldPhoneNumberComparator(OptionAttributeType optionAttributeType) {
        switch (optionAttributeType) {
            case ATTRIBUTE_MIDDLE_NUMBER:
                return new FieldMiddlePhoneNumberComparator();
            case ATTRIBUTE_LAST_NUMBER:
                return new FieldLastPhoneNumberComparator();
            default:
                return new FieldPhoneNumberComparator();
        }
    }

    @Override
    public boolean equals(Employee target, String value) {
        return target.getPhoneNumber().equals(value);
    }

    @Override
    public boolean greaterThan(Employee target, String value) {
        return target.getPhoneNumber().compareTo(value) > 0;
    }

    @Override
    public boolean greaterThanOrEqualTo(Employee target, String value) {
        return target.getPhoneNumber().compareTo(value) >= 0;
    }

    @Override
    public boolean smallerThan(Employee target, String value) {
        return target.getPhoneNumber().compareTo(value) < 0;
    }

    @Override
    public boolean smallerThanOrEqualTo(Employee target, String value) {
        return target.getPhoneNumber().compareTo(value) <= 0;
    }
}
