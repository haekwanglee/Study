package com.sec.bestreviewer.store.finder.field;


import com.sec.bestreviewer.store.Employee;

abstract public class FieldComparator {

    abstract public boolean equals(Employee target, String value);

    abstract public boolean greaterThan(Employee target, String value);

    abstract public boolean greaterThanOrEqualTo(Employee target, String value);

    abstract public boolean smallerThan(Employee target, String value);

    abstract public boolean smallerThanOrEqualTo(Employee target, String value);

}
