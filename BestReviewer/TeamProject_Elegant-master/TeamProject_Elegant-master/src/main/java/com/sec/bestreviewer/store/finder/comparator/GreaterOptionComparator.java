package com.sec.bestreviewer.store.finder.comparator;

import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.finder.field.FieldComparator;

public class GreaterOptionComparator extends OptionComparator {

    GreaterOptionComparator(FieldComparator fieldComparator) {
        super(fieldComparator);
    }

    @Override
    public boolean compare(Employee target, String value) {
        return fieldComparator.greaterThan(target, value);
    }
}
