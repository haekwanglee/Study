package com.sec.bestreviewer.store.finder.field;

import com.sec.bestreviewer.store.Employee;

public class FieldFirstNameComparator extends FieldNameComparator {
    @Override
    protected String getTargetName(Employee target) {
        return target.getFirstName();
    }
}
