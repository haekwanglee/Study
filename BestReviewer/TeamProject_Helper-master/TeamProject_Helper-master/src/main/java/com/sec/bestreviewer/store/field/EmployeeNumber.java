package com.sec.bestreviewer.store.field;

public class EmployeeNumber implements Field, Comparable<EmployeeNumber> {
    public static final String PREFIX_NINETY = "9";
    public static final String HIDDEN_PREFIX = "1";

    private String value;
    private int intEmployeeNumber;

    public EmployeeNumber(String employeeNumber) {
        setValue(employeeNumber);
    }

    @Override
    public String toString() {
        return value;
    }

    public void setValue(String employeeNumber) {
        this.value = employeeNumber;
        this.intEmployeeNumber = getIntegerID(employeeNumber);
    }

    private int getIntegerID(String employeeNumber) {
        if (isNinety(employeeNumber)) {
            return Integer.parseInt(employeeNumber);
        }
        return Integer.parseInt(HIDDEN_PREFIX + employeeNumber);
    }

    private boolean isNinety(String employeeNumber) {
        return employeeNumber.startsWith(PREFIX_NINETY);
    }

    @Override
    public boolean equals(String value) {
        return this.value.equals(value);
    }

    @Override
    public boolean equals(String value, String option) {
        return equals(value);
    }

    @Override
    public int compareTo(String value) {
        return intEmployeeNumber - getIntegerID(value);
    }

    @Override
    public int compareTo(String value, String option) {
        return compareTo(value);
    }

    @Override
    public int compareTo(EmployeeNumber o) {
        return this.intEmployeeNumber - o.intEmployeeNumber;
    }
}
