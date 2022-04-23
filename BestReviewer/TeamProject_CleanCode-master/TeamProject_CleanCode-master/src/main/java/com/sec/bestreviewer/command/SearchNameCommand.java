package com.sec.bestreviewer.command;

import com.sec.bestreviewer.data.InqualitySignOption;
import com.sec.bestreviewer.data.SearchOption;
import com.sec.bestreviewer.store.Employee;

public class SearchNameCommand implements SearchFilterMaker {
    @Override
    public SearchPredicate createFilter(SearchOption searchOption, InqualitySignOption inqualitySignOption, String value) {
        switch (searchOption) {
            case FULL_NAME:
                return new NameSearchPredicate(value, inqualitySignOption);
            case FIRST_NAME:
                return new FirstNameSearchPredicate(value, inqualitySignOption);
            case LAST_NAME:
                return new LastNameSearchPredicate(value, inqualitySignOption);
            default:
                throw new IllegalArgumentException("Illegal options : " + searchOption);
        }
    }

    private class NameSearchPredicate extends SearchPredicate {
        private InqualitySignOption inqualitySignOption;

        NameSearchPredicate(String value, InqualitySignOption inqualitySignOption) {
            super(value);
            this.inqualitySignOption = inqualitySignOption;
        }

        @Override
        public boolean test(Employee employee) {
            switch (inqualitySignOption) {
                case NONE:
                    return employee.getName().equals(value);
                case GREATER_THAN:
                    return (employee.getName().compareTo(value) > 0 ? true : false);
                case GREATER_THAN_OR_EQUAL_TO:
                    return (employee.getName().compareTo(value) >= 0 ? true : false);
                case SMALLER_THAN:
                    return (employee.getName().compareTo(value) < 0 ? true : false);
                case SMALLER_THAN_OR_EQUAL_TO:
                    return (employee.getName().compareTo(value) <= 0 ? true : false);
                default:
                    throw new IllegalArgumentException("Illegal inqualitySignOption : " + inqualitySignOption);
            }

        }
    }

    private class FirstNameSearchPredicate extends SearchPredicate {
        private InqualitySignOption inqualitySignOption;

        FirstNameSearchPredicate(String value, InqualitySignOption inqualitySignOption) {
            super(value);
            this.inqualitySignOption = inqualitySignOption;
        }

        @Override
        public boolean test(Employee employee) {
            switch (inqualitySignOption) {
                case NONE:
                    return employee.getFirstName().equals(value);
                case GREATER_THAN:
                    return (employee.getFirstName().compareTo(value) > 0 ? true : false);
                case GREATER_THAN_OR_EQUAL_TO:
                    return (employee.getFirstName().compareTo(value) >= 0 ? true : false);
                case SMALLER_THAN:
                    return (employee.getFirstName().compareTo(value) < 0 ? true : false);
                case SMALLER_THAN_OR_EQUAL_TO:
                    return (employee.getFirstName().compareTo(value) <= 0 ? true : false);
                default:
                    throw new IllegalArgumentException("Illegal inqualitySignOption : " + inqualitySignOption);
            }
        }
    }

    private class LastNameSearchPredicate extends SearchPredicate {
        private InqualitySignOption inqualitySignOption;

        LastNameSearchPredicate(String value, InqualitySignOption inqualitySignOption) {
            super(value);
            this.inqualitySignOption = inqualitySignOption;
        }

        @Override
        public boolean test(Employee employee) {
            switch (inqualitySignOption) {
                case NONE:
                    return employee.getLastName().equals(value);
                case GREATER_THAN:
                    return (employee.getLastName().compareTo(value) > 0 ? true : false);
                case GREATER_THAN_OR_EQUAL_TO:
                    return (employee.getLastName().compareTo(value) >= 0 ? true : false);
                case SMALLER_THAN:
                    return (employee.getLastName().compareTo(value) < 0 ? true : false);
                case SMALLER_THAN_OR_EQUAL_TO:
                    return (employee.getLastName().compareTo(value) <= 0 ? true : false);
                default:
                    throw new IllegalArgumentException("Illegal inqualitySignOption : " + inqualitySignOption);
            }
        }
    }
}
