package com.sec.bestreviewer.store;

import java.util.Arrays;

public enum EmployeeFieldType {
    EMPLOYEE_NUMBER("employeeNum"),
    NAME("name"),
    CAREER_LEVEL("cl"),
    PHONE_NUMBER("phoneNum"),
    BIRTH_DAY("birthday"),
    CERTI("certi");

    public final String fieldName;

    EmployeeFieldType(String fieldName) {
        this.fieldName = fieldName;
    }

    public static EmployeeFieldType getTypeByFieldName(String fieldName) {
        return Arrays.stream(EmployeeFieldType.values())
                .filter(e -> e.fieldName.equals(fieldName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Wrong field type:"+fieldName));
    }
}
