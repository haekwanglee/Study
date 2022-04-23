package com.sec.bestreviewer.command;

import com.sec.bestreviewer.data.InqualitySignOption;
import com.sec.bestreviewer.data.SearchOption;
import com.sec.bestreviewer.store.Certi;
import com.sec.bestreviewer.store.Employee;

public class SearchCertiCommand implements SearchFilterMaker {
    @Override
    public SearchPredicate createFilter(SearchOption searchOption, InqualitySignOption inqualitySignOption, String value) {
        return new CertiSearchPredicate(value, inqualitySignOption);
    }

    private static class CertiSearchPredicate extends SearchPredicate {

        private final InqualitySignOption inqualitySignOption;

        CertiSearchPredicate(String value, InqualitySignOption inqualitySignOption) {
            super(value);
            this.inqualitySignOption = inqualitySignOption;
        }

        @Override
        public boolean test(Employee employee) {
            final int employeeCerti = Certi.valueOf(employee.getCerti()).ordinal();
            final int valueCerti = Certi.valueOf(value).ordinal();

            switch (inqualitySignOption) {
                case GREATER_THAN:
                    return employeeCerti > valueCerti;
                case GREATER_THAN_OR_EQUAL_TO:
                    return employeeCerti >= valueCerti;
                case SMALLER_THAN:
                    return employeeCerti < valueCerti;
                case SMALLER_THAN_OR_EQUAL_TO:
                    return employeeCerti <= valueCerti;
                default:
                    return employeeCerti == valueCerti;
            }
        }
    }
}
