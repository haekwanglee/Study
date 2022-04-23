package com.sec.bestreviewer.store;

public class Birthday {

    private String yearMonthDay;
    private String year;
    private String month;
    private String day;
    private int yearMonthDayDigit;
    private int yearDigit;
    private int monthDigit;
    private int dayDigit;

    public Birthday(String birthday) {
        this.yearMonthDay = birthday;
        this.year = birthday.substring(0, 4);
        this.month = birthday.substring(4, 6);
        this.day = birthday.substring(6, 8);
        this.yearMonthDayDigit = Integer.parseInt(this.yearMonthDay);
        this.yearDigit = Integer.parseInt(this.year);
        this.monthDigit = Integer.parseInt(this.month);
        this.dayDigit = Integer.parseInt(this.day);
    }

    public String getYearMonthDay() {
        return yearMonthDay;
    }

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    public int getYearMonthDayDigit() {
        return yearMonthDayDigit;
    }

    public int getYearDigit() {
        return yearDigit;
    }

    public int getMonthDigit() {
        return monthDigit;
    }

    public int getDayDigit() {
        return dayDigit;
    }
}
