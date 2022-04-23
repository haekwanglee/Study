package com.sec.bestreviewer.predicate;

import com.sec.bestreviewer.command.SearchFilterMaker.SearchPredicate;
import com.sec.bestreviewer.data.InqualitySignOption;
import com.sec.bestreviewer.store.Employee;

public class PhoneLastNumberSearchPredicateFactory {

    public static SearchPredicate createSearchPredicate(InqualitySignOption inqualitySignOption,
                                                        String value) {
        switch (inqualitySignOption) {
            case GREATER_THAN:
                return new LastPhoneNumberGreaterPredicate(value);
            case GREATER_THAN_OR_EQUAL_TO:
                return new LastPhoneNumberGreaterEqualPredicate(value);
            case SMALLER_THAN:
                return new LastPhoneNumberSmallerPredicate(value);
            case SMALLER_THAN_OR_EQUAL_TO:
                return new LastPhoneNumberSmallerEqualPredicate(value);
            default:
                return new LastPhoneNumberSearchPredicate(value);
        }
    }

    private static class LastPhoneNumberGreaterPredicate extends SearchPredicate {

        LastPhoneNumberGreaterPredicate(String value) {
            super(value);
        }

        @Override
        public boolean test(Employee employee) {
            return employee.getPhoneLastNumberDigit() > Integer.parseInt(value);
        }
    }

    private static class LastPhoneNumberGreaterEqualPredicate extends SearchPredicate {

        LastPhoneNumberGreaterEqualPredicate(String value) {
            super(value);
        }

        @Override
        public boolean test(Employee employee) {
            return employee.getPhoneLastNumberDigit() >= Integer.parseInt(value);
        }
    }

    private static class LastPhoneNumberSmallerPredicate extends SearchPredicate {

        LastPhoneNumberSmallerPredicate(String value) {
            super(value);
        }

        @Override
        public boolean test(Employee employee) {
            return employee.getPhoneLastNumberDigit() < Integer.parseInt(value);
        }
    }

    private static class LastPhoneNumberSmallerEqualPredicate extends SearchPredicate {

        LastPhoneNumberSmallerEqualPredicate(String value) {
            super(value);
        }

        @Override
        public boolean test(Employee employee) {
            return employee.getPhoneLastNumberDigit() <= Integer.parseInt(value);
        }
    }

    private static class LastPhoneNumberSearchPredicate extends SearchPredicate {

        LastPhoneNumberSearchPredicate(String value) {
            super(value);
        }

        @Override
        public boolean test(Employee employee) {
            return employee.getPhoneLastNumber().equals(value);
        }
    }
}
