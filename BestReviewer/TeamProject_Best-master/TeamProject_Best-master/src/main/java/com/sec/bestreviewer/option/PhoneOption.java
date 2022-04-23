package com.sec.bestreviewer.option;

import com.sec.bestreviewer.store.EmployeeStore;

import java.util.List;

public class PhoneOption {
    public static final String MIDDLE_NUMBER_OPTION = "-m";
    public static final String LAST_NUMBER_OPTION = "-l";

    private boolean isMiddleNumberOn;
    private boolean isLastNumberOn;

    public PhoneOption(List<String> options, String fieldName) {
        if (EmployeeStore.FIELD_PHONE_NUMBER.equals(fieldName)) {
            isMiddleNumberOn = options.contains(MIDDLE_NUMBER_OPTION);
            isLastNumberOn = options.contains(LAST_NUMBER_OPTION);
        }
//        System.out.println("PhoneOption: " + "isMiddleNumberOn=" + isMiddleNumberOn + ", isLastNumberOn=" + isLastNumberOn);
    }

    public boolean isOptionOn() {
        return isMiddleNumberOn || isLastNumberOn;
    }

    public boolean isMiddleNumberOn() {
        return isMiddleNumberOn;
    }

    public boolean isLastNumberOn() {
        return isLastNumberOn;
    }
}