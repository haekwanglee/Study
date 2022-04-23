package com.sec.bestreviewer.store;

import com.sec.bestreviewer.store.field.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

class EmployeeStoreImpl implements EmployeeStore {
    private final List<Employee> employees = new ArrayList<>();

    Function<Employee, Field> getFieldGetter(String fieldName) {
        switch (fieldName) {
            case FIELD_EMPLOYEE_NUMBER:
                return Employee::getEmployeeNumber;
            case FIELD_NAME:
                return Employee::getName;
            case FIELD_CAREER_LEVEL:
                return Employee::getCareerLevel;
            case FIELD_PHONE_NUMBER:
                return Employee::getPhoneNumber;
            case FIELD_BIRTH_DAY:
                return Employee::getBirthday;
            case FIELD_CERTI:
                return Employee::getCerti;
        }
        throw new RuntimeException("Wrong field name");
    }

    Function<Employee, Integer> getFieldCompareToMethod(ConditionParam param) {
        Function<Employee, Field> fieldGetter = getFieldGetter(param.field);
        return employee -> fieldGetter
                .apply(employee)
                .compareTo(param.value, param.fieldOption);
    }

    Function<Integer, Boolean> getCompareMethod(ConditionParam param) {
        switch (param.compareOption) {
            case GREATER_EQUAL:
                return ret -> ret >= 0;
            case GREATER:
                return ret -> ret > 0;
            case SMALL:
                return ret -> ret < 0;
            case SMALL_EQUAL:
                return ret -> ret <= 0;
            case EQUAL:
            default:
                return ret -> ret == 0;
        }
    }

    @Override
    public void add(Employee employee) {
        employees.add(employee);
    }

    @Override
    public int count() {
        return employees.size();
    }

    Function<Employee, Boolean> getComparator(ConditionParam param) {
        Function<Employee, Integer> compareToMethod = getFieldCompareToMethod(param);
        Function<Integer, Boolean> compareResultMethod = getCompareMethod(param);
        return employee -> compareResultMethod.apply(compareToMethod.apply(employee));
    }

    @Override
    public List<Employee> search(ConditionParam param) {
        Function <Employee, Boolean> condition = getComparator(param);
        return employees.stream().parallel()
                .filter(condition::apply)
                .collect(Collectors.toList()) ;
    }

    @Override
    public List<Employee> searchAnd(ConditionParam param1, ConditionParam param2) {
        Function <Employee, Boolean> condition1 = getComparator(param1);
        Function <Employee, Boolean> condition2 = getComparator(param2);
        return employees.stream().parallel()
                .filter(employee -> condition1.apply(employee) && condition2.apply(employee))
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> searchOr(ConditionParam param1, ConditionParam param2) {
        Function <Employee, Boolean> condition1 = getComparator(param1);
        Function <Employee, Boolean> condition2 = getComparator(param2);
        return employees.stream().parallel()
                .filter(employee -> condition1.apply(employee) || condition2.apply(employee))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(List<Employee> employeesToDelete) {
        if (employeesToDelete != null) {
            employees.removeAll(employeesToDelete);
        }
    }

    @Override
    public void modify(List<Employee> employeesToModify, String fieldName, String newValue) {
        Function<Employee, Field> fieldGetter = getFieldGetter(fieldName);
        for (Employee employee: employeesToModify) {
            fieldGetter.apply(employee).setValue(newValue);
        }
    }
}
