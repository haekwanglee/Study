package com.sec.bestreviewer.store;

import com.sec.bestreviewer.util.Options;

public class EmployeePhoneNumberField extends Field {

    public EmployeePhoneNumberField(String value) {
        super(value);
    }

    @Override
    public int toCompareTo(String value, String filterOption) {
        String phoneNumber = this.value;
        String[] splitedPhoneNumber = phoneNumber.split("-");
        String middleNumber = splitedPhoneNumber[1];
        String lastNumber = splitedPhoneNumber[2];

        if(filterOption.equals( Options.MIDDLE_NUMBER_OPTION)){
            return middleNumber.compareTo(value);
        }else if(filterOption.equals( Options.LAST_NUMBER_OPTION)){
            return lastNumber.compareTo(value);
        }
        return phoneNumber.compareTo(value);
    }
}
