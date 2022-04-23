package com.sec.bestreviewer.store;

public class PhoneNumber {

    private String fullNumber;
    private String middleNumber;
    private String lastNumber;
    private int middleNumberDigit;
    private int lastNumberDigit;
    private static final String HYPHEN = "-";

    public PhoneNumber(String phoneNumber) {
        this.fullNumber = phoneNumber;
        String[] splitPhoneNumber = phoneNumber.split(HYPHEN);
        this.middleNumber = splitPhoneNumber[1];
        this.middleNumberDigit = Integer.parseInt(splitPhoneNumber[1]);
        this.lastNumber = splitPhoneNumber[2];
        this.lastNumberDigit = Integer.parseInt(splitPhoneNumber[2]);
    }

    public String getFullNumber() {
        return fullNumber;
    }

    public String getMiddleNumber() {
        return middleNumber;
    }

    public String getLastNumber() {
        return lastNumber;
    }

    public int getMiddleNumberDigit() {
        return middleNumberDigit;
    }

    public int getLastNumberDigit() {
        return lastNumberDigit;
    }

    public static int getLastEightDigit(String phoneNumber) {
        String[] splitPhoneNumber = phoneNumber.split(HYPHEN);
        return Integer.parseInt(splitPhoneNumber[1]) * 10000 + Integer.parseInt(splitPhoneNumber[2]);
    }
}
