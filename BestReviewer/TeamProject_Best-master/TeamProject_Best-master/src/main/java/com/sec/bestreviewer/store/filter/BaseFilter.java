package com.sec.bestreviewer.store.filter;

import com.sec.bestreviewer.option.EqualityOptionType;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.util.OptionParser;

public interface BaseFilter {

    boolean filter(Employee employee, String value, OptionParser optionParser);

    default boolean compare(String a, String b, OptionParser optionParser) {
        if (optionParser.getEqualityOption().getType() == EqualityOptionType.GREATER_THAN) {
            return compare(a, b) > 0;
        } else if (optionParser.getEqualityOption().getType() == EqualityOptionType.GREATER_THAN_OR_EQUAL_TO) {
            return compare(a, b) >= 0;
        } else if (optionParser.getEqualityOption().getType() == EqualityOptionType.SMALLER_THAN) {
            return compare(a, b) < 0;
        } else if (optionParser.getEqualityOption().getType() == EqualityOptionType.SMALLER_THAN_OR_EQUAL_TO) {
            return compare(a, b) <= 0;
        } else {
            return compare(a, b) == 0;
        }
    }

    default int compare(String a, String b) {
        return a.compareTo(b);
    }

    Employee modify(Employee employee, String value);
}
