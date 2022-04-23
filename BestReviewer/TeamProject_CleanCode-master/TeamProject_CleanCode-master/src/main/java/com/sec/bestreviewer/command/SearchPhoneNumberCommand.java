package com.sec.bestreviewer.command;

import com.sec.bestreviewer.data.InqualitySignOption;
import com.sec.bestreviewer.data.SearchOption;
import com.sec.bestreviewer.predicate.PhoneFullNumberSearchPredicateFactory;
import com.sec.bestreviewer.predicate.PhoneLastNumberSearchPredicateFactory;
import com.sec.bestreviewer.predicate.PhoneMiddleNumberSearchPredicateFactory;

public class SearchPhoneNumberCommand implements SearchFilterMaker {
    @Override
    public SearchPredicate createFilter(SearchOption searchOption, InqualitySignOption inqualitySignOption, String value) {
        switch (searchOption) {
            case FULL_NUMBER:
                return PhoneFullNumberSearchPredicateFactory.createSearchPredicate(inqualitySignOption, value);
            case MIDDLE_NUMBER:
                return PhoneMiddleNumberSearchPredicateFactory.createSearchPredicate(inqualitySignOption, value);
            case LAST_NUMBER:
                return PhoneLastNumberSearchPredicateFactory.createSearchPredicate(inqualitySignOption, value);
            default:
                throw new IllegalArgumentException("Illegal options : " + searchOption);
        }
    }
}
