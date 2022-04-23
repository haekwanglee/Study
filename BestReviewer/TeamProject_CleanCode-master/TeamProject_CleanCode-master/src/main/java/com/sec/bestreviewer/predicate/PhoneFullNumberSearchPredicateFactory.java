package com.sec.bestreviewer.predicate;

import com.sec.bestreviewer.command.SearchFilterMaker.SearchPredicate;
import com.sec.bestreviewer.data.InqualitySignOption;
import com.sec.bestreviewer.store.Employee;

import static com.sec.bestreviewer.store.PhoneNumber.getLastEightDigit;


public class PhoneFullNumberSearchPredicateFactory {

    public static SearchPredicate createSearchPredicate(InqualitySignOption inqualitySignOption,
                                                        String value) {
        switch (inqualitySignOption) {
            case GREATER_THAN:
                return new PhoneNumberSearchGreaterPredicate(value);
            case GREATER_THAN_OR_EQUAL_TO:
                return new PhoneNumberSearchGreaterEqualPredicate(value);
            case SMALLER_THAN:
                return new PhoneNumberSearchSmallerPredicate(value);
            case SMALLER_THAN_OR_EQUAL_TO:
                return new PhoneNumberSearchSmallerEqualPredicate(value);
            default:
                return new PhoneNumberSearchPredicate(value);
        }
    }

    private static class PhoneNumberSearchGreaterPredicate extends SearchPredicate {

        PhoneNumberSearchGreaterPredicate(String value) {
            super(value);
        }

        @Override
        public boolean test(Employee employee) {
            return getLastEightDigit(employee.getPhoneNumber()) > getLastEightDigit(value);
        }
    }

    private static class PhoneNumberSearchGreaterEqualPredicate extends SearchPredicate {

        PhoneNumberSearchGreaterEqualPredicate(String value) {
            super(value);
        }

        @Override
        public boolean test(Employee employee) {
            return getLastEightDigit(employee.getPhoneNumber()) >= getLastEightDigit(value);
        }
    }

    private static class PhoneNumberSearchSmallerPredicate extends SearchPredicate {

        PhoneNumberSearchSmallerPredicate(String value) {
            super(value);
        }

        @Override
        public boolean test(Employee employee) {
            return getLastEightDigit(employee.getPhoneNumber()) < getLastEightDigit(value);
        }
    }

    private static class PhoneNumberSearchSmallerEqualPredicate extends SearchPredicate {

        PhoneNumberSearchSmallerEqualPredicate(String value) {
            super(value);
        }

        @Override
        public boolean test(Employee employee) {
            return getLastEightDigit(employee.getPhoneNumber()) <= getLastEightDigit(value);
        }
    }

    private static class PhoneNumberSearchPredicate extends SearchPredicate {

        PhoneNumberSearchPredicate(String value) {
            super(value);
        }

        @Override
        public boolean test(Employee employee) {
            return employee.getPhoneNumber().equals(value);
        }
    }
}
