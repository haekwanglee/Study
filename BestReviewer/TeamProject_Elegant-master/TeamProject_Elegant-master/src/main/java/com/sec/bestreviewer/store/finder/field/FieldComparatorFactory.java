package com.sec.bestreviewer.store.finder.field;

import com.sec.bestreviewer.store.EmployeeFieldType;
import com.sec.bestreviewer.store.OptionAttributeType;

public class FieldComparatorFactory {

    public static FieldComparator createFieldComparator(EmployeeFieldType employeeFieldType, OptionAttributeType optionAttributeType) {
        switch (employeeFieldType) {
            case EMPLOYEE_NUMBER:
                return new FieldEmployeeComparator();
            case CAREER_LEVEL:
                return new FieldCareerLevelComparator();
            case NAME:
                return FieldNameComparator.createFieldNameComparator(optionAttributeType);
            case PHONE_NUMBER:
                return FieldPhoneNumberComparator.createFieldPhoneNumberComparator(optionAttributeType);
            case BIRTH_DAY:
                return FieldBirthdayComparator.createFieldBirthdayComparator(optionAttributeType);
            case CERTI:
                return new FieldCertiComparator();
            default:
                throw new IllegalArgumentException("Wrong employee field type");
        }
    }
}
