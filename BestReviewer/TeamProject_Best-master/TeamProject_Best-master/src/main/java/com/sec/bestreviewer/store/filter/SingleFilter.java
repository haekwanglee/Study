package com.sec.bestreviewer.store.filter;

import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.util.OptionParser;
import com.sec.bestreviewer.util.Pair;

public class SingleFilter implements Filter {

    private final Pair<String, String> conditionPair;
    private final OptionParser optionParser;

    public SingleFilter(Pair<String, String> conditionPair, OptionParser optionParser) {
        this.conditionPair = conditionPair;
        this.optionParser = optionParser;
    }

    @Override
    public boolean predicate(Employee employee) {
        return FilterType.forFieldName(conditionPair.fieldName).createFilter().filter(employee, conditionPair.value, optionParser);
    }
}
