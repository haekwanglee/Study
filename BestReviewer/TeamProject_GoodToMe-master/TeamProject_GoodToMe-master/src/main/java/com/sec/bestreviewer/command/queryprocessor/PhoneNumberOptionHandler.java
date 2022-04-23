package com.sec.bestreviewer.command.queryprocessor;

import com.sec.bestreviewer.store.Employee;

public class PhoneNumberOptionHandler implements FieldOptionHandler {
    @Override
    public String process(String option, Employee employee) {
        String[] phoneNumber = employee.getPhoneNumber().split("-");
        switch (option) {
            case "-m" :
                return phoneNumber[1];
            case "-l" :
                return phoneNumber[2];
        }
        return employee.getPhoneNumber();
    }
}
