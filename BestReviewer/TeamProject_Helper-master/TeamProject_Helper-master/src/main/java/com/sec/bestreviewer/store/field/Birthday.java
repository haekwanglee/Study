package com.sec.bestreviewer.store.field;

public class Birthday implements Field {
    private int year;
    private int month;
    private int day;
    private int birthDay;

    public Birthday(String value) {
        setValue(value);
    }

    @Override
    public void setValue(String value) {
        year = Integer.parseInt(value.substring(0, 4));
        month = Integer.parseInt(value.substring(4, 6));
        day = Integer.parseInt(value.substring(6));
        birthDay = Integer.parseInt(value);
    }

    // Visible for test only
    /*private*/ int getYear() {
        return year;
    }

    // Visible for test only
    /*private*/ int getMonth() {
        return month;
    }

    // Visible for test only
    /*private*/ int getDay() {
        return day;
    }

    @Override
    public boolean equals(String value) {
        return birthDay == Integer.parseInt(value);
    }

    @Override
    public boolean equals(String value, String option) {
        int otherDate = Integer.parseInt(value);
        return getValueByOption(option) == otherDate;
    }

    @Override
    public int compareTo(String value) {
        return compareTo(value, "");
    }

    @Override
    public int compareTo(String value, String option) {
        return getValueByOption(option) - Integer.parseInt(value);
    }

    private int getValueByOption(String option) {
        switch(option) {
            case "-y":
                return year;
            case "-m":
                return month;
            case "-d":
                return day;
            default:
                return birthDay;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(birthDay);
    }
}