package com.sec.bestreviewer.store.field;

public class Name implements Field {
    public static final String OPTION_FIRST_NAME = "-f";
    public static final String OPTION_LAST_NAME = "-l";

    String firstName;
    String lastName;
    String fullName;

    public String getFirstName() {
        return firstName;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Name(String value) {
        setValue(value);
    }

    @Override
    public void setValue(String value) {
        fullName = value;
        String[] names = value.split(" ", 2);
        setFirstName(names[0]);
        setLastName(names[1]);
    }

    String getCompareTarget(String option) {
       switch (option) {
           case OPTION_FIRST_NAME:
               return getFirstName();
           case OPTION_LAST_NAME:
               return getLastName();
           default:
               return fullName;
       }
    }

    @Override
    public boolean equals(String value) {
        return fullName.equals(value);
    }

    @Override
    public boolean equals(String value, String option) {
        String compareTarget = getCompareTarget(option);
        return compareTarget.equals(value);
    }

    @Override
    public int compareTo(String value) {
        return fullName.compareTo(value);
    }

    @Override
    public int compareTo(String value, String option) {
        return getCompareTarget(option).compareTo(value);
    }

    @Override
    public String toString() {
        return fullName;
    }
}
