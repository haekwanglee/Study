package com.sec.bestreviewer.option;

import com.sec.bestreviewer.store.EmployeeStore;

import java.util.List;

public class NameOption {

    public static final String FIRST_NAME_OPTION = "-f";
    public static final String LAST_NAME_OPTION = "-l";

    private boolean isFirstNameOn;
    private boolean isLastNameOn;

    public NameOption(List<String> options, String fieldName) {
        if (EmployeeStore.FIELD_NAME.equals(fieldName)) {
            isFirstNameOn = options.contains(FIRST_NAME_OPTION);
            isLastNameOn = options.contains(LAST_NAME_OPTION);
        }
//        System.out.println("NameOption: " + "isFirstName=" + isFirstNameOn + ", isLastName=" + isLastNameOn);
    }

    public boolean isOptionOn() {
        return isFirstNameOn || isLastNameOn;
    }

    public boolean isFirstNameOn() {
        return isFirstNameOn;
    }

    public boolean isLastNameOn() {
        return isLastNameOn;
    }
}