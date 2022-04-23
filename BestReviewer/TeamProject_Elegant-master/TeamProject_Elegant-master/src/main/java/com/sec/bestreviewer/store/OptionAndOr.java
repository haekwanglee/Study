package com.sec.bestreviewer.store;

import com.sec.bestreviewer.store.finder.FieldFinder;

import java.util.*;

public class OptionAndOr {
    public static final String OPT_AND = "-a";

    private final List<Employee> employees;
    private final Comparator<Employee> comparator = Comparator.comparing(Employee::getEmployeeNumberValue);

    public OptionAndOr(List<Employee> employees) {
        this.employees = employees;
    }

    List<Employee> doMultiSearch(ModelParams modelParams) {

        List<Employee> list1 = findByCondition(modelParams.getConditionForSearch1());
        List<Employee> list2 = findByCondition(modelParams.getConditionForSearch2());

        if(isAnd(modelParams.getOptionAndOr())){
            return processAnd(list1, list2);
        }
        else{
            return processOr(list1, list2);
        }
    }

    List<Employee> findByCondition(Condition condition){
        String fieldName = condition.getPair().first;
        String value = condition.getPair().second;

        EmployeeFieldType fieldType = EmployeeFieldType.getTypeByFieldName(fieldName);
        FieldFinder fieldFinder = new FieldFinder(fieldType, employees);
        fieldFinder.setOption(new OptionProperty(condition));
        return fieldFinder.find(value);
    }

    private ArrayList<Employee> processOr(List<Employee> list1, List<Employee> list2) {
        Set<Employee> set = new LinkedHashSet<>(list1);
        set.addAll(list2);
        return new ArrayList<>(set);
    }

    private List<Employee> processAnd(List<Employee> list1, List<Employee> list2) {
        List<Employee> mergedList = new ArrayList<>();
        list2.sort(comparator);

        for(Employee employee : list1){
            if (contains(list2, employee)){
                mergedList.add(employee);
            }
        }
        return mergedList;
    }

    private boolean contains(List<Employee> list2, Employee employee) {
        return Collections.binarySearch(list2, employee, comparator) >= 0;
    }

    boolean isAnd(String opt){
        return OPT_AND.equals(opt);
    }
}