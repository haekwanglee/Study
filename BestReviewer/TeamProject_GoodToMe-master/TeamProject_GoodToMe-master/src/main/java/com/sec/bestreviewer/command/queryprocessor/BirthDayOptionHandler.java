package com.sec.bestreviewer.command.queryprocessor;

import com.sec.bestreviewer.store.Employee;

public class BirthDayOptionHandler implements FieldOptionHandler {
    @Override
    public String process(String option, Employee employee) {
        switch (option) {
            case "-y" :
                return employee.getBirthday().substring(0,4);
            case "-m" :
                return employee.getBirthday().substring(4,6);
            case "-d" :
                return employee.getBirthday().substring(6,8);
        }
        return employee.getBirthday();
    }
}
