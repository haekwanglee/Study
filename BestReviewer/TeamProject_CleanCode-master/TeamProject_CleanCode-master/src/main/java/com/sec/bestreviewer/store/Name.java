package com.sec.bestreviewer.store;

public class Name {

    private String fullName;
    private String firstName;
    private String lastName;

    public Name(String name) {
        this.fullName = name;
        String[] splitName = name.split(" ");
        this.firstName = splitName[0];
        this.lastName = splitName[1];
    }

    public Name(Name name){
        this.fullName = name.getFullName();
        this.firstName = name.getFirstName();
        this.lastName = name.getLastName();
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }
}
