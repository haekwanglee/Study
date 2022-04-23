package com.sec.bestreviewer.util;

import com.sec.bestreviewer.store.EmployeeStore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParamsParser {

    public enum PairIndex {
        FIRST_PAIR(0), SECOND_PAIR(2), THIRD_PAIR(4);

        private final int index;
        PairIndex(int index) {
            this.index = index;
        }
    }

    private static final Map<String, String> fieldMap = new HashMap<>();
    private static final String EMPLOYEE_NUMBER = "employeeNum";
    private static final String NAME = "name";
    private static final String CAREER_LEVEL = "cl";
    private static final String PHONE_NUMBER = "phoneNum";
    private static final String BIRTHDAY = "birthday";
    private static final String CERTI = "certi";

    // mapping table between commands name and DB columns name
    static {
        fieldMap.put(EMPLOYEE_NUMBER, EmployeeStore.FIELD_EMPLOYEE_NUMBER);
        fieldMap.put(NAME, EmployeeStore.FIELD_NAME);
        fieldMap.put(CAREER_LEVEL, EmployeeStore.FIELD_CAREER_LEVEL);
        fieldMap.put(PHONE_NUMBER, EmployeeStore.FIELD_PHONE_NUMBER);
        fieldMap.put(BIRTHDAY, EmployeeStore.FIELD_BIRTH_DAY);
        fieldMap.put(CERTI, EmployeeStore.FIELD_CERTI);
    }

    public static Pair<String, String> parser(List<String> params, PairIndex pairIndex) {
        return getMapFromParams(params, pairIndex.index);
    }

    private static Pair<String, String> getMapFromParams(List<String> params, int index) {
        try {
            final String fieldName = fieldMap.get(params.get(index));
            if (fieldName == null) {
                throw new IllegalArgumentException("Wrong field: " + params.get(index));
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new IllegalArgumentException("Wrong parameters");
        }
        return Pair.create(fieldMap.get(params.get(index)), params.get(index + 1));
    }
}
