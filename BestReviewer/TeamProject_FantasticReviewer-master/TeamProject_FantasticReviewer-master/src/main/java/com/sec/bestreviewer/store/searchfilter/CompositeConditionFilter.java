package com.sec.bestreviewer.store.searchfilter;

import com.sec.bestreviewer.base.ConditionParameter;
import com.sec.bestreviewer.base.OperatorId;
import com.sec.bestreviewer.store.Employee;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompositeConditionFilter implements SearchFilterDelegator {

    private final SearchFilterDelegator filterFirst;
    private final SearchFilterDelegator filterSecond;

    public CompositeConditionFilter(SearchFilterDelegator filterFirst,
                                    SearchFilterDelegator filterSecond) {
        this.filterFirst = filterFirst;
        this.filterSecond = filterSecond;
    }

    @Override
    public List<Employee> search(List<Employee> employees, ConditionParameter param) {
        List<Employee> firstResult = filterFirst.search(employees, param);
        List<Employee> secondResult = filterSecond.search(employees,
                new ConditionParameter(
                        Objects.requireNonNull(param.getSecond()),
                        null,
                        null,
                        OperatorId.NONE
                ));

        switch (param.getOperator()) {
            case OperatorId.AND:
                return intersection(firstResult, secondResult);
            case OperatorId.OR:
                return union(firstResult, secondResult);
        }
        return Collections.emptyList();
    }

    private List<Employee> intersection(List<Employee> firstResult, List<Employee> secondResult) {
        return firstResult.stream()
                .filter(secondResult::contains)
                .collect(Collectors.toList());
    }

    private List<Employee> union(List<Employee> firstResult, List<Employee> secondResult) {
        return Stream.concat(firstResult.stream(), secondResult.stream())
                .distinct()
                .collect(Collectors.toList());
    }
}
