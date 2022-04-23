package com.sec.bestreviewer.store.finder;

import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeFieldType;
import com.sec.bestreviewer.store.OptionProperty;
import com.sec.bestreviewer.store.finder.comparator.OptionComparator;
import com.sec.bestreviewer.store.finder.comparator.OptionComparatorFactory;
import com.sec.bestreviewer.store.finder.field.FieldComparator;
import com.sec.bestreviewer.store.finder.field.FieldComparatorFactory;

import java.util.List;
import java.util.stream.Collectors;

public class FieldFinder {
    EmployeeFieldType fieldType;
    List<Employee> employees;

    OptionComparator optionComparator;
    FieldComparator fieldComparator;

    public FieldFinder(EmployeeFieldType fieldType, List<Employee> employees) {
        this.fieldType = fieldType;
        this.employees = employees;
        initDefaultOptionProperty();
    }

    public void setOption(OptionProperty optionProperty) {
        fieldComparator = FieldComparatorFactory.createFieldComparator(fieldType, optionProperty.getOptionAttribute());
        optionComparator = OptionComparatorFactory.createOptionComparator(optionProperty.getOptionValue(), fieldComparator);
    }

    public List<Employee> find(String value) {
        return employees.stream()
                .filter(employee -> optionComparator.compare(employee, value))
                .collect(Collectors.toList());
    }

    private void initDefaultOptionProperty() {
        OptionProperty defaultOptionProperty = new OptionProperty();
        setOption(defaultOptionProperty);
    }
}
