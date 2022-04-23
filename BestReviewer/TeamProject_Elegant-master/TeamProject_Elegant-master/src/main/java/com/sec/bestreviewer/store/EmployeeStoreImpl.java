package com.sec.bestreviewer.store;

import com.sec.bestreviewer.store.finder.FieldFinder;
import com.sec.bestreviewer.store.updater.FieldUpdater;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class EmployeeStoreImpl implements EmployeeStore {
    private final List<Employee> employees = new ArrayList<>();

    @Override
    public List<Employee> search(ModelParams modelParams) {

        if(modelParams.isOptionAndOr()){
            OptionAndOr optionAndOr = new OptionAndOr(employees);
            return optionAndOr.doMultiSearch(modelParams);
        }
        String fieldName = modelParams.getConditionForSearch1().getPair().first;
        String value = modelParams.getConditionForSearch1().getPair().second;
        EmployeeFieldType fieldType = EmployeeFieldType.getTypeByFieldName(fieldName);

        FieldFinder fieldFinder = new FieldFinder(fieldType, employees);
        fieldFinder.setOption(new OptionProperty(modelParams.getConditionForSearch1()));

        return fieldFinder.find(value);
    }

    @Override
    public List<Employee> delete(ModelParams modelParams) {
        List<Employee> searchedEmployees = search(modelParams);
        employees.removeAll(searchedEmployees);
        return searchedEmployees;
    }

    @Override
    public void add(Employee employee) {
        employees.add(employee);
    }

    @Override
    public int count() {
        return employees.size();
    }

    @Override
    public List<Employee> modify(ModelParams modelParams) {
        List<Employee> searchedEmployees = search(modelParams);
        List<Employee> result = searchedEmployees.stream().map(employee -> new Employee(employee)).collect(Collectors.toList()); // deep copy

        String updateFieldName = modelParams.getConditionForModify().getPair().first;
        String updateFieldValue = modelParams.getConditionForModify().getPair().second;
        EmployeeFieldType fieldType = EmployeeFieldType.getTypeByFieldName(updateFieldName);

        FieldUpdater fieldUpdater = FieldUpdaterFactory.createFieldUpdater(fieldType, searchedEmployees);
        fieldUpdater.update(updateFieldValue);

        return result;
    }
}
