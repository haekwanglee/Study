package com.sec.bestreviewer.command;

import com.sec.bestreviewer.data.InqualitySignOption;
import com.sec.bestreviewer.data.SearchOption;
import com.sec.bestreviewer.store.Employee;

import java.util.function.Predicate;

public interface SearchFilterMaker {

    SearchPredicate createFilter(SearchOption searchOption, InqualitySignOption inqualitySignOption, String value);

    abstract class SearchPredicate implements Predicate<Employee> {

        protected String value;

        protected SearchPredicate(String value) {
            this.value = value;
        }
    }
}
