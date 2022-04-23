package com.sec.bestreviewer.store.searchfilter;

import com.sec.bestreviewer.base.ConditionParameter;
import com.sec.bestreviewer.base.ConditionValue;
import com.sec.bestreviewer.base.OperatorId;
import com.sec.bestreviewer.base.OptionId;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.test.CommandGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DefaultFilterTest {

    @Test
    void searchUnknownFieldReturnEmpty() {
        // prepare
        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        "unknown_field",
                        "unknown_value",
                        OptionId.OPTION_NONE,
                        OptionId.OPTION_NONE,
                        OptionId.OPTION_NONE
                ),
                null,
                null,
                OperatorId.NONE
        );

        // invoke
        List<Employee> ret = new DefaultFilter().search(new CommandGenerator().generateEmployees(5), param);

        // validate
        assertEquals(0, ret.size());
    }
}