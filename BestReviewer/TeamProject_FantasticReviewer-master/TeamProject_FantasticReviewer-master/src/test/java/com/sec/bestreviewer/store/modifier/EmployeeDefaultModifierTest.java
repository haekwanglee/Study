package com.sec.bestreviewer.store.modifier;

import com.sec.bestreviewer.base.Value;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.test.CommandGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

class EmployeeDefaultModifierTest {

    public static final String NOT_DEFINED = "NOT_DEFINED";

    @Test
    void modify() {
        List<Employee> employee = new CommandGenerator().generateEmployees(1);
        Value param = new Value(NOT_DEFINED, NOT_DEFINED);
        EmployeeModifier modifier = EmployeeModifierFactory.create(param);
        assertThat(modifier, instanceOf(EmployeeDefaultModifier.class));
        modifier.modify(employee.get(0));
    }
}