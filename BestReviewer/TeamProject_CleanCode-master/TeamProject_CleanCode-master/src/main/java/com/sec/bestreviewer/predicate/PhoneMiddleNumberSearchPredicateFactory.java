package com.sec.bestreviewer.predicate;

import com.sec.bestreviewer.command.SearchFilterMaker.SearchPredicate;
import com.sec.bestreviewer.data.InqualitySignOption;
import com.sec.bestreviewer.store.Employee;

public class PhoneMiddleNumberSearchPredicateFactory {

    public static SearchPredicate createSearchPredicate(InqualitySignOption inqualitySignOption,
                                                        String value) {
        switch (inqualitySignOption) {
            case GREATER_THAN:
                return new MiddlePhoneNumberGreaterPredicate(value);
            case GREATER_THAN_OR_EQUAL_TO:
                return new MiddlePhoneNumberGreaterEqualPredicate(value);
            case SMALLER_THAN:
                return new MiddlePhoneNumberSmallerPredicate(value);
            case SMALLER_THAN_OR_EQUAL_TO:
                return new MiddlePhoneNumberSmallerEqualPredicate(value);
            default:
                return new MiddlePhoneNumberSearchPredicate(value);
        }
    }

    private static class MiddlePhoneNumberGreaterPredicate extends SearchPredicate {

        MiddlePhoneNumberGreaterPredicate(String value) {
            super(value);
        }

        @Override
        public boolean test(Employee employee) {
            return employee.getPhoneMiddleNumberDigit() > Integer.parseInt(value);
        }
    }

    private static class MiddlePhoneNumberGreaterEqualPredicate extends SearchPredicate {

        MiddlePhoneNumberGreaterEqualPredicate(String value) {
            super(value);
        }

        @Override
        public boolean test(Employee employee) {
            return employee.getPhoneMiddleNumberDigit() >= Integer.parseInt(value);
        }
    }

    private static class MiddlePhoneNumberSmallerPredicate extends SearchPredicate {

        MiddlePhoneNumberSmallerPredicate(String value) {
            super(value);
        }

        @Override
        public boolean test(Employee employee) {
            return employee.getPhoneMiddleNumberDigit() < Integer.parseInt(value);
        }
    }

    private static class MiddlePhoneNumberSmallerEqualPredicate extends SearchPredicate {

        MiddlePhoneNumberSmallerEqualPredicate(String value) {
            super(value);
        }

        @Override
        public boolean test(Employee employee) {
            return employee.getPhoneMiddleNumberDigit() <= Integer.parseInt(value);
        }
    }

    private static class MiddlePhoneNumberSearchPredicate extends SearchPredicate {

        MiddlePhoneNumberSearchPredicate(String value) {
            super(value);
        }

        @Override
        public boolean test(Employee employee) {
            return employee.getPhoneMiddleNumber().equals(value);
        }
    }
}
