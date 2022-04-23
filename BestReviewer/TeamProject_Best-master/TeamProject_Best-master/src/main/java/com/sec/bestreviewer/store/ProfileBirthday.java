package com.sec.bestreviewer.store;



public class ProfileBirthday {

    private String year;
    private String month;
    private String day;

    public ProfileBirthday(String birthday) {
        setBirthday(birthday);
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

    public void setBirthday(String birthday) {
        year = birthday.substring(0, 4);
        month = birthday.substring(4, 6);
        day = birthday.substring(6, 8);
    }

    public String getBirthday() {
        return year + month + day;
    }
}
