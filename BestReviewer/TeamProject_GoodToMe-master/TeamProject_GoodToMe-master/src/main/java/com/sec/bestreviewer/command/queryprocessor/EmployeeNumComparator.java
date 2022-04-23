package com.sec.bestreviewer.command.queryprocessor;

public class EmployeeNumComparator implements QueryComparator {

    private static final int YEAR_1990_EMPLOYEE_NUMBER = 90_000000;
    private static final int YEAR_PREFIX_19 = 1900_000000;
    private static final int YEAR_PREFIX_20 = 2000_000000;

    public boolean equals(String a, String b) {
        return getConvertedEmployeeNumber(Integer.parseInt(a)) == getConvertedEmployeeNumber(Integer.parseInt(b));
    }

    public boolean greaterThan(String a, String b) {
        return getConvertedEmployeeNumber(Integer.parseInt(a)) > getConvertedEmployeeNumber(Integer.parseInt(b));
    }

    public boolean greaterThanEquals(String a, String b) {
        return getConvertedEmployeeNumber(Integer.parseInt(a)) >= getConvertedEmployeeNumber(Integer.parseInt(b));
    }

    public boolean smallerThan(String a, String b) {
        return getConvertedEmployeeNumber(Integer.parseInt(a)) < getConvertedEmployeeNumber(Integer.parseInt(b));
    }

    public boolean smallerThanEquals(String a, String b) {
        return getConvertedEmployeeNumber(Integer.parseInt(a)) <= getConvertedEmployeeNumber(Integer.parseInt(b));
    }

    private int getConvertedEmployeeNumber(int queryNumber) {
        return queryNumber >= YEAR_1990_EMPLOYEE_NUMBER ?
                YEAR_PREFIX_19 + queryNumber : YEAR_PREFIX_20 + queryNumber;
    }

}