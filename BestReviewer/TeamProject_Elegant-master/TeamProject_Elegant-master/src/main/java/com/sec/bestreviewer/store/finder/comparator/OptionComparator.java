package com.sec.bestreviewer.store.finder.comparator;

import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.finder.field.FieldComparator;

public abstract class OptionComparator {
    protected final FieldComparator fieldComparator;

    OptionComparator(FieldComparator fieldComparator) {
        this.fieldComparator = fieldComparator;
    }

    public abstract boolean compare(Employee target, String value);
}
