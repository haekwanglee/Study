package com.sec.bestreviewer;

import com.sec.bestreviewer.store.ConditionParam;
import com.sec.bestreviewer.util.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConditionParamTest {
    @Test
    public void testBuilder() {
        String key = "key";
        String value = "value";
        String fieldOption = "-f";
        String compareOption = "-g";
        ConditionParam.CompareOption conditionOption = ConditionParam.CompareOption.GREATER;

        Pair<String, String> keyValue = Pair.create(key, value);
        ConditionParam expectedParam = new ConditionParam(key, value, fieldOption, conditionOption);

        ConditionParam actualParam = new ConditionParam.Builder()
                .setKeyValue(Pair.create(key, value))
                .setFieldOption(fieldOption)
                .setCompareOption(compareOption)
                .build();

        assertEquals(expectedParam, actualParam);
    }
}
