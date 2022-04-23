package com.sec.bestreviewer;

import com.sec.bestreviewer.base.ConditionParameter;
import com.sec.bestreviewer.store.Employee;

public class TokenGroup {
    String type;
    ConditionParameter conditionParameter;
    Employee employee;

    TokenGroup(String type, ConditionParameter conditionParameter) {
        this.type = type;
        this.conditionParameter = conditionParameter;
    }

    TokenGroup(String type, Employee employee) {
        this.type = type;
        this.employee = employee;
    }

    String getType() {
        return type;
    }

    ConditionParameter getConditionParameter() {
        return conditionParameter;
    }

    Employee getEmployee() {
        return employee;
    }
}
