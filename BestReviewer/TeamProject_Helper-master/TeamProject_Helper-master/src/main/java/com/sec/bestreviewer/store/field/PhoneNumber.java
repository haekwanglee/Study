package com.sec.bestreviewer.store.field;

public class PhoneNumber implements Field {
    private int midNumber;
    private int lastNumber;
    private int fullNumberInt;
    private String fullNumber;

    public PhoneNumber(String value) {
        setValue(value);
    }

    @Override
    public void setValue(String value) {
        midNumber = Integer.parseInt(value.split("-")[1]);
        lastNumber = Integer.parseInt(value.split("-")[2]);
        fullNumberInt = extractNumberOnly(value);
        fullNumber = value;
    }

    @Override
    public boolean equals(String value) {
        return equals(value, "");
    }

    @Override
    public boolean equals(String value, String option) {
        return getProperValueByOption(option) == extractNumberOnly(value);
    }

    @Override
    public String toString() {
        return fullNumber;
    }

    @Override
    public int compareTo(String otherValue) {
        return compareTo(otherValue, "");
    }

    @Override
    public int compareTo(String otherValue, String option) {
        return getProperValueByOption(option) - extractNumberOnly(otherValue);
    }

    private int getProperValueByOption(String option) {
        switch(option) {
            case "-m":
                return midNumber;
            case "-l":
                return lastNumber;
            default:
                return fullNumberInt;
        }
    }

    private int extractNumberOnly(String value) {
        return Integer.parseInt(value.replaceAll("-",""));
    }
}
