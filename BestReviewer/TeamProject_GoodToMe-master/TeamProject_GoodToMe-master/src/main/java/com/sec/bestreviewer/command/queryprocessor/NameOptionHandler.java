package com.sec.bestreviewer.command.queryprocessor;

import com.sec.bestreviewer.store.Employee;

public class NameOptionHandler implements FieldOptionHandler {
    @Override
    public String process(String option, Employee employee) {
        String[] name = employee.getName().split(" ");

        switch(option) {
            case "-f" :
                return name[0];
            case "-l" :
                return name[1];
        }
        return employee.getName();
    }
}
