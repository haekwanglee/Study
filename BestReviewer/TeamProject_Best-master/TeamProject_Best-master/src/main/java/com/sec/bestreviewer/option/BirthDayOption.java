package com.sec.bestreviewer.option;


import com.sec.bestreviewer.store.EmployeeStore;

import java.util.List;

public class BirthDayOption {

    public static final String YEAR_OPTION = "-y";
    public static final String MONTH_OPTION = "-m";
    public static final String DAY_OPTION = "-d";

    private boolean isYearOn;
    private boolean isMonthOn;
    private boolean isDayOn;

    public BirthDayOption(List<String> options, String fieldName) {
        if (EmployeeStore.FIELD_BIRTH_DAY.equals(fieldName)) {
            isYearOn = options.contains(YEAR_OPTION);
            isMonthOn = options.contains(MONTH_OPTION);
            isDayOn = options.contains(DAY_OPTION);
        }
//        System.out.println("DateOption: " + "isYearOn=" + isYearOn + ", isMonthOn=" + isMonthOn + ", isDayOn=" + isDayOn);
    }

    public boolean isOptionOn() {
        return isDayOn || isMonthOn || isYearOn;
    }

    public boolean isYearOn() {
        return isYearOn;
    }

    public boolean isMonthOn() {
        return isMonthOn;
    }

    public boolean isDayOn() {
        return isDayOn;
    }
}