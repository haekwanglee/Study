package com.sec.bestreviewer.store;

public class ProfileName {

    private String firstName;
    private String lastName;

    public ProfileName(String name) {
        setName(name);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }

    public void setName(String name) {
        firstName = name.split(" ")[0];
        lastName = name.split(" ")[1];
    }
}
