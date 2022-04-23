package com.sec.bestreviewer.store;

import com.sec.bestreviewer.store.updater.*;

import java.util.List;

public class FieldUpdaterFactory {

    public static FieldUpdater createFieldUpdater(EmployeeFieldType fieldType, List<Employee> employees) {
        switch (fieldType) {
            case EMPLOYEE_NUMBER:
                return new FieldEmployeeNumberUpdater(employees);
            case NAME:
                return new FieldNameUpdater(employees);
            case CAREER_LEVEL:
                return new FieldCareerLevelUpdater(employees);
            case PHONE_NUMBER:
                return new FieldPhoneNumberUpdater(employees);
            case BIRTH_DAY:
                return new FieldBirthDayUpdater(employees);
            case CERTI:
                return new FieldCertiUpdater(employees);
            default:
                throw new RuntimeException("Invalid field type");
        }
    }
}
