package com.sec.bestreviewer.command;

import com.sec.bestreviewer.data.InqualitySignOption;
import com.sec.bestreviewer.data.SearchOption;
import com.sec.bestreviewer.store.Employee;

public class SearchBirthdayCommand implements SearchFilterMaker {
    @Override
    public SearchPredicate createFilter(SearchOption searchOption, InqualitySignOption inqualitySignOption, String value) {
        switch (searchOption) {
            case FULL_BIRTHDAY:
                return new BirthdaySearchPredicate(value, inqualitySignOption);
            case BIRTHDAY_YEAR:
                return new BirthdayYearSearchPredicate(value, inqualitySignOption);
            case BIRTHDAY_MONTH:
                return new BirthdayMonthSearchPredicate(value, inqualitySignOption);
            case BIRTHDAY_DAY:
                return new BirthdayDaySearchPredicate(value, inqualitySignOption);
            default:
                throw new IllegalArgumentException("Illegal options : " + searchOption);
        }
    }

    private static class BirthdaySearchPredicate extends SearchPredicate {

        private final InqualitySignOption inqualitySignOption;

        BirthdaySearchPredicate(String value, InqualitySignOption inqualitySignOption) {
            super(value);
            this.inqualitySignOption = inqualitySignOption;
        }

        @Override
        public boolean test(Employee employee) {
            final int valueDigit = Integer.parseInt(value);
            switch (inqualitySignOption) {
                case GREATER_THAN:
                    return employee.getBirthdayDigit() > valueDigit;
                case GREATER_THAN_OR_EQUAL_TO:
                    return employee.getBirthdayDigit() >= valueDigit;
                case SMALLER_THAN:
                    return employee.getBirthdayDigit() < valueDigit;
                case SMALLER_THAN_OR_EQUAL_TO:
                    return employee.getBirthdayDigit() <= valueDigit;
                default:
                    return employee.getBirthdayDigit() == valueDigit;
            }
        }
    }

    private static class BirthdayYearSearchPredicate extends SearchPredicate {

        private InqualitySignOption inqualitySignOption;

        BirthdayYearSearchPredicate(String value, InqualitySignOption inqualitySignOption) {
            super(value);
            this.inqualitySignOption = inqualitySignOption;
        }

        @Override
        public boolean test(Employee employee) {
            final int valueDigit = Integer.parseInt(value);
            switch (inqualitySignOption) {
                case GREATER_THAN:
                    return employee.getBirthdayYearDigit() > valueDigit;
                case GREATER_THAN_OR_EQUAL_TO:
                    return employee.getBirthdayYearDigit() >= valueDigit;
                case SMALLER_THAN:
                    return employee.getBirthdayYearDigit() < valueDigit;
                case SMALLER_THAN_OR_EQUAL_TO:
                    return employee.getBirthdayYearDigit() <= valueDigit;
                default:
                    return employee.getBirthdayYearDigit() == valueDigit;
            }
        }
    }

    private static class BirthdayMonthSearchPredicate extends SearchPredicate {

        private InqualitySignOption inqualitySignOption;

        BirthdayMonthSearchPredicate(String value, InqualitySignOption inqualitySignOption) {
            super(value);
            this.inqualitySignOption = inqualitySignOption;
        }

        @Override
        public boolean test(Employee employee) {
            final int valueDigit = Integer.parseInt(value);
            switch (inqualitySignOption) {
                case GREATER_THAN:
                    return employee.getBirthdayMonthDigit() > valueDigit;
                case GREATER_THAN_OR_EQUAL_TO:
                    return employee.getBirthdayMonthDigit() >= valueDigit;
                case SMALLER_THAN:
                    return employee.getBirthdayMonthDigit() < valueDigit;
                case SMALLER_THAN_OR_EQUAL_TO:
                    return employee.getBirthdayMonthDigit() <= valueDigit;
                default:
                    return employee.getBirthdayMonthDigit() == valueDigit;
            }
        }
    }

    private static class BirthdayDaySearchPredicate extends SearchPredicate {

        private InqualitySignOption inqualitySignOption;

        BirthdayDaySearchPredicate(String value, InqualitySignOption inqualitySignOption) {
            super(value);
            this.inqualitySignOption = inqualitySignOption;
        }

        @Override
        public boolean test(Employee employee) {
            final int valueDigit = Integer.parseInt(value);
            switch (inqualitySignOption) {
                case GREATER_THAN:
                    return employee.getBirthdayDayDigit() > valueDigit;
                case GREATER_THAN_OR_EQUAL_TO:
                    return employee.getBirthdayDayDigit() >= valueDigit;
                case SMALLER_THAN:
                    return employee.getBirthdayDayDigit() < valueDigit;
                case SMALLER_THAN_OR_EQUAL_TO:
                    return employee.getBirthdayDayDigit() <= valueDigit;
                default:
                    return employee.getBirthdayDayDigit() == valueDigit;
            }
        }
    }
}
