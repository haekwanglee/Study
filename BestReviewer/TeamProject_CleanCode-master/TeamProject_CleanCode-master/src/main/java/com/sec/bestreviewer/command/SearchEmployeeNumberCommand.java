package com.sec.bestreviewer.command;

import com.sec.bestreviewer.data.InqualitySignOption;
import com.sec.bestreviewer.data.SearchOption;
import com.sec.bestreviewer.store.Employee;

public class SearchEmployeeNumberCommand implements SearchFilterMaker {
    @Override
    public SearchPredicate createFilter(SearchOption searchOption, InqualitySignOption inqualitySignOption, String value) {
        return new EmployeeNumberSearchPredicate(value, inqualitySignOption);
    }

    private class EmployeeNumberSearchPredicate extends SearchPredicate {

        private InqualitySignOption inqualitySignOption;

        private static final int YEAR_1990_EMPLOYEE_NUMBER = 90_000000;
        private static final int YEAR_PREFIX_19 = 1900_000000;
        private static final int YEAR_PREFIX_20 = 2000_000000;

        EmployeeNumberSearchPredicate(String value, InqualitySignOption inqualitySignOption) {
            super(value);
            this.inqualitySignOption = inqualitySignOption;
        }

        @Override
        public boolean test(Employee employee) {
            int employeeNumber = getEmployeeFullNumber(Integer.parseInt(employee.getEmployeeNumber()));
            int valueInteger = getEmployeeFullNumber(Integer.parseInt(value));

            switch (inqualitySignOption) {
                case NONE:
                    return employeeNumber == valueInteger;
                case GREATER_THAN:
                    return employeeNumber > valueInteger;
                case GREATER_THAN_OR_EQUAL_TO:
                    return employeeNumber >= valueInteger;
                case SMALLER_THAN:
                    return employeeNumber < valueInteger;
                case SMALLER_THAN_OR_EQUAL_TO:
                    return employeeNumber <= valueInteger;
                default:
                    throw new IllegalArgumentException("Illegal inqualitySignOption : " + inqualitySignOption);
            }
        }

        private int getEmployeeFullNumber(int employeeNumber) {
            return employeeNumber >= YEAR_1990_EMPLOYEE_NUMBER ?
                    YEAR_PREFIX_19 + employeeNumber : YEAR_PREFIX_20 + employeeNumber;
        }
    }
}
