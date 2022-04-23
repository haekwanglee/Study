package com.sec.bestreviewer.store.filter;

import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.ProfilePhoneNumber;
import com.sec.bestreviewer.util.OptionParser;

public class PhoneNumberFilter implements BaseFilter {

    @Override
    public boolean filter(Employee employee, String value, OptionParser optionParser) {
        if (optionParser.getPhoneOption().isMiddleNumberOn()) {
            return compare(employee.getProfilePhoneNumber().getMiddleNumber(), value, optionParser);
        } else if (optionParser.getPhoneOption().isLastNumberOn()) {
            return compare(employee.getProfilePhoneNumber().getLastNumber(), value, optionParser);
        } else {
            // 첫번째 자리 010은 무시하고 검색
            ProfilePhoneNumber newNum = new ProfilePhoneNumber(value);
            return compare(employee.getProfilePhoneNumber().getWithoutFirstNumber(), newNum.getWithoutFirstNumber(), optionParser);
        }
    }

    @Override
    public Employee modify(Employee employee, String value) {
        Employee newEmployee = new Employee(employee);
        newEmployee.setPhoneNumber(value);
        return newEmployee;
    }
}
