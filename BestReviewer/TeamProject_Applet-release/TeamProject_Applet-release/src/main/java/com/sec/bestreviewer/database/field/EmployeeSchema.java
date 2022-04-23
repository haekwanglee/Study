package com.sec.bestreviewer.database.field;

import java.util.Arrays;

public enum EmployeeSchema {
    EMPLOYEE_NUMBER("employeeNum", FieldType.STRING),
    NAME("name", FieldType.STRING),
    CAREER_LEVEL("cl", FieldType.CAREER_LEVEL),
    PHONE_NUMBER("phoneNum", FieldType.STRING),
    BIRTHDAY("birthday", FieldType.STRING),
    CERTI("certi", FieldType.CERTI),
    NAME_FIRST("name_first", FieldType.STRING),
    NAME_LAST("name_last", FieldType.STRING),
    PHONE_NUMBER_MIDDLE("phoneNum_middle", FieldType.STRING),
    PHONE_NUMBER_LAST("phoneNum_last", FieldType.STRING),
    BIRTH_DAY_YEAR("birthday_year", FieldType.STRING),
    BIRTH_DAY_MONTH("birthday_month", FieldType.STRING),
    BIRTH_DAY_DAY("birthday_day", FieldType.STRING);

    private final String fieldName;
    private final FieldType fieldType;

    EmployeeSchema(String fieldName, FieldType fieldType) {
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }

    public String getName() {
        return fieldName;
    }

    public int getIndex() {
        return ordinal();
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public static int getPrimaryKeyIndex() {
        return EMPLOYEE_NUMBER.ordinal();
    }

    public static int getIndexByName(String fieldName) {
        return Arrays.stream(EmployeeSchema.values()).filter(index -> index.fieldName.equals(fieldName))
                .map(EmployeeSchema::getIndex)
                .findFirst().orElse(-1);
    }
}
