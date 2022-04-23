package com.sec.bestreviewer.data;

import com.sec.bestreviewer.store.EmployeeStore;

import java.util.HashMap;
import java.util.Map;

public class SearchData {
    private static final String EMPLOYEE_NUMBER = "employeeNum";
    private static final String NAME = "name";
    private static final String CAREER_LEVEL = "cl";
    private static final String PHONE_NUMBER = "phoneNum";
    private static final String BIRTHDAY = "birthday";
    private static final String CERTI = "certi";

    private SearchOption searchOption;
    private InqualitySignOption inqualitySignOption;
    private String keyColumnName;
    private String keyValue;

    private static final Map<String, String> fieldMap = new HashMap<>();

    static {
        fieldMap.put(EMPLOYEE_NUMBER, EmployeeStore.FIELD_EMPLOYEE_NUMBER);
        fieldMap.put(NAME, EmployeeStore.FIELD_NAME);
        fieldMap.put(CAREER_LEVEL, EmployeeStore.FIELD_CAREER_LEVEL);
        fieldMap.put(PHONE_NUMBER, EmployeeStore.FIELD_PHONE_NUMBER);
        fieldMap.put(BIRTHDAY, EmployeeStore.FIELD_BIRTH_DAY);
        fieldMap.put(CERTI, EmployeeStore.FIELD_CERTI);
    }

    public SearchData(SearchOption searchOption, InqualitySignOption inqualitySignOption, String keyColumnName, String keyValue) {
        this.searchOption = searchOption;
        this.inqualitySignOption = inqualitySignOption;
        this.keyColumnName = getConditionMapFromParams(keyColumnName);
        this.keyValue = keyValue;
    }

    public SearchOption getSearchOption() {
        return searchOption;
    }

    public InqualitySignOption getInqualitySignOption() {
        return inqualitySignOption;
    }

    public String getKeyColumnName() {
        return keyColumnName;
    }

    public String getKeyValue() {
        return keyValue;
    }

    private static String getConditionMapFromParams(String key) {
        return fieldMap.get(key);
    }
}
