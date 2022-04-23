package com.sec.bestreviewer.command;

import com.sec.bestreviewer.data.InqualitySignOption;
import com.sec.bestreviewer.data.SearchOption;
import com.sec.bestreviewer.store.Employee;

public class SearchClCommand implements SearchFilterMaker {
    @Override
    public SearchPredicate createFilter(SearchOption searchOption, InqualitySignOption inqualitySignOption, String value) {
        return new ClSearchPredicate(value, inqualitySignOption);
    }

    private class ClSearchPredicate extends SearchPredicate {
        private InqualitySignOption inqualitySignOption;

        ClSearchPredicate(String value, InqualitySignOption inqualitySignOption) {
            super(value);
            this.inqualitySignOption = inqualitySignOption;
        }

        @Override
        public boolean test(Employee employee) {
            switch (inqualitySignOption) {
                case NONE:
                    return employee.getCareerLevel().equals(value);
                case GREATER_THAN:
                    return (employee.getCareerLevel().compareTo(value) > 0 ? true : false);
                case GREATER_THAN_OR_EQUAL_TO:
                    return (employee.getCareerLevel().compareTo(value) >= 0 ? true : false);
                case SMALLER_THAN:
                    return (employee.getCareerLevel().compareTo(value) < 0 ? true : false);
                case SMALLER_THAN_OR_EQUAL_TO:
                    return (employee.getCareerLevel().compareTo(value) <= 0 ? true : false);
                default:
                    throw new IllegalArgumentException("Illegal inqualitySignOption : " + inqualitySignOption);
            }
        }
    }
}
