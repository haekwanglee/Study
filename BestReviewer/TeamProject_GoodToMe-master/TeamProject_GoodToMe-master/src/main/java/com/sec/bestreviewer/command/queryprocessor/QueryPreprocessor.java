package com.sec.bestreviewer.command.queryprocessor;

import com.sec.bestreviewer.command.querycondition.QueryCondition;
import com.sec.bestreviewer.store.Employee;

import static com.sec.bestreviewer.store.EmployeeStore.*;

public class QueryPreprocessor {
    public String process(QueryCondition queryCondition, Employee employee) {
        String option = queryCondition.getOption2();
        String column = queryCondition.getColumn();

        FieldOptionHandler optionHandler = getFieldOptionHandler(column);
        return optionHandler.process(option, employee);
    }

    private FieldOptionHandler getFieldOptionHandler(String column) {
        switch(column) {
            case FIELD_NAME:
                return new NameOptionHandler();
            case FIELD_PHONE_NUMBER:
                return new PhoneNumberOptionHandler();
            case FIELD_BIRTH_DAY:
                return new BirthDayOptionHandler();
            case FIELD_CAREER_LEVEL:
                return new CareerLevelOptionHandler();
            case FIELD_EMPLOYEE_NUMBER:
                return new EmployeeNumberOptionHandler();
            case FIELD_CERTIFICATION:
                return new CertificationOptionHandler();
            default:
                throw new RuntimeException("Unsupported field : " + column);
        }
    }
}
