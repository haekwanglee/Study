package com.sec.bestreviewer.store;

public class ProfilePhoneNumber {

    private String firstNumber;
    private String middleNumber;
    private String lastNumber;

    public ProfilePhoneNumber(String phoneNumber) {
        setPhoneNumber(phoneNumber);
    }

    public void setPhoneNumber(String phoneNumber) {
        firstNumber = phoneNumber.split("-")[0];
        middleNumber = phoneNumber.split("-")[1];
        lastNumber = phoneNumber.split("-")[2];
    }

    public String getPhoneNumber() {
        return String.format("%s-%s-%s", firstNumber, middleNumber, lastNumber);
    }

    public String getFirstNumber() {
        return firstNumber;
    }

    public String getMiddleNumber() {
        return middleNumber;
    }

    public String getLastNumber() {
        return lastNumber;
    }

    public String getWithoutFirstNumber() {
        return String.format("%s-%s", middleNumber, lastNumber);
    }

}
