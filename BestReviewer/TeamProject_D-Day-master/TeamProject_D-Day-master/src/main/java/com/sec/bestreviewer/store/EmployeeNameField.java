package com.sec.bestreviewer.store;

import com.sec.bestreviewer.util.Options;

public class EmployeeNameField extends Field {

    public EmployeeNameField(String value) {
        super(value);
    }

    @Override
    public int toCompareTo(String value, String filterOption) {
        String fullName = this.value;
        String[] splitedName = fullName.split(" ");
        String firstName = splitedName[0];
        String lastName = splitedName[1];

        if (filterOption.equals(Options.FIRST_NAME_OPTION)) {
            return firstName.compareTo(value);
        } else if (filterOption.equals(Options.LAST_NAME_OPTION)) {
            return lastName.compareTo(value);
        }
        return fullName.compareTo(value);
    }
}

