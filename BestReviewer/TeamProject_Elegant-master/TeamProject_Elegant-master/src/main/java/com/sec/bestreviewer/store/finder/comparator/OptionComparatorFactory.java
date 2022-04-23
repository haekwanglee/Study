package com.sec.bestreviewer.store.finder.comparator;

import com.sec.bestreviewer.store.OptionValueType;
import com.sec.bestreviewer.store.finder.field.FieldComparator;

public class OptionComparatorFactory {

    public static OptionComparator createOptionComparator(OptionValueType optionValueType, FieldComparator fieldComparator) {
        switch (optionValueType) {
            case VALUE_EQUAL:
                return new EqualOptionComparator(fieldComparator);
            case VALUE_GREATER:
                return new GreaterOptionComparator(fieldComparator);
            case VALUE_GREATER_EQUAL:
                return new GreaterEqualOptionComparator(fieldComparator);
            case VALUE_SMALLER:
                return new SmallerOptionComparator(fieldComparator);
            case VALUE_SMALLER_EQUAL:
                return new SmallerEqualOptionComparator(fieldComparator);
            default:
                throw new IllegalArgumentException("Wrong option type");
        }
    }
}
