package com.sec.bestreviewer.store;

public class EmployeeNumberField extends Field {
    private static final int YEAR_1990_EMPLOYEE_NUMBER = 90_000000;
    private static final int YEAR_PREFIX_19 = 1900_000000;
    private static final int YEAR_PREFIX_20 = 2000_000000;

    public EmployeeNumberField(String value) {
        super(value);
    }

    @Override
    public int toCompareTo(String value, String filterOption) {
        int myValue = getEmployeeNumber(Integer.parseInt(this.value));
        int valueToCompare = getEmployeeNumber(Integer.parseInt(value));
        return myValue - valueToCompare;
    }

    private static Integer getEmployeeNumber(int employeeNumber) {
        return employeeNumber >= YEAR_1990_EMPLOYEE_NUMBER ?
                YEAR_PREFIX_19 + employeeNumber : YEAR_PREFIX_20 + employeeNumber;
    }
}
