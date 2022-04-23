package com.sec.bestreviewer.store.filter;

import com.sec.bestreviewer.store.EmployeeStore;

import java.util.Arrays;

public enum FilterType {

    EMPLOYEE_NUMBER(EmployeeStore.FIELD_EMPLOYEE_NUMBER) {
        @Override
        public BaseFilter createFilter() {
            return new EmployeeNumberFilter();
        }
    },
    NAME(EmployeeStore.FIELD_NAME) {
        @Override
        public BaseFilter createFilter() {
            return new NameFilter();
        }
    },
    PHONE_NUMBER(EmployeeStore.FIELD_PHONE_NUMBER) {
        @Override
        public BaseFilter createFilter() {
            return new PhoneNumberFilter();
        }
    },
    BIRTH_DAY(EmployeeStore.FIELD_BIRTH_DAY) {
        @Override
        public BaseFilter createFilter() {
            return new BirthdayFilter();
        }
    },
    CAREER_LEVEL(EmployeeStore.FIELD_CAREER_LEVEL) {
        @Override
        public BaseFilter createFilter() {
            return new CareerLevelFilter();
        }
    },
    CERTI(EmployeeStore.FIELD_CERTI) {
        @Override
        public BaseFilter createFilter() {
            return new CertiFilter();
        }
    },
    NONE("");

    String filedName;
    FilterType(String filedName) {
        this.filedName = filedName;
    }

    public static FilterType forFieldName(String fieldName) {
        return Arrays.stream(FilterType.values())
                .filter(filterType -> filterType.filedName.equals(fieldName))
                .findFirst()
                .orElse(NONE);
    }

    public BaseFilter createFilter() {
        return new DefaultFilter();
    }
}
