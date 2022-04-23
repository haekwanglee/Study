package com.sec.bestreviewer.store.searchfilter;

import com.sec.bestreviewer.base.ConditionParameter;
import com.sec.bestreviewer.base.OptionId;
import com.sec.bestreviewer.store.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

abstract class SearchFilterSearcher implements SearchFilterDelegator {

    @Override
    public List<Employee> search(List<Employee> employees, ConditionParameter param) {
        String value = param.getFirst().getValue();
        String sign = param.getFirst().getOption3();

        if (OptionId.OPTION_NONE.equals(sign) || OptionId.OPTION_SPACE.equals(sign)) {
            return searchEquals(employees, value);
        }

        return searchInequalitySign(employees, param);
    }

    abstract protected List<Employee> searchEquals(List<Employee> employees, String value);

    protected List<Employee> searchInequalitySign(List<Employee> employees,
                                                  ConditionParameter param) {

        String value = param.getFirst().getValue();
        String sign = param.getFirst().getOption3();

        return employees.stream()
                .filter(getSignPredicate(sign, value))
                .collect(Collectors.toList());
    }

    private Predicate<Employee> getSignPredicate(String sign, String value) {
        switch (sign) {
            case OptionId.OPTION_SIGN_GREATER_THAN:
                return it -> (getIndexKeyComparator().compare(getIndexKey(it), value) > 0);
            case OptionId.OPTION_SIGN_GREATER_THAN_OR_EQUAL_TO:
                return it -> (getIndexKeyComparator().compare(getIndexKey(it), value) >= 0);
            case OptionId.OPTION_SIGN_SMALLER_THAN:
                return it -> (getIndexKeyComparator().compare(getIndexKey(it), value) < 0);
            case OptionId.OPTION_SIGN_SMALLER_THAN_OR_EQUAL_TO:
                return it -> (getIndexKeyComparator().compare(getIndexKey(it), value) <= 0);
        }

        return it -> true;
    }

    protected Comparator<String> getIndexKeyComparator() {
        return String::compareTo;
    }

    abstract protected String getIndexKey(Employee employee);
}
