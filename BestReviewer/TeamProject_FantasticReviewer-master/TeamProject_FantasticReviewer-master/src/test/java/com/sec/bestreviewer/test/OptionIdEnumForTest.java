package com.sec.bestreviewer.test;

import com.sec.bestreviewer.base.OptionId;

public enum OptionIdEnumForTest {
    GreaterThan(OptionId.OPTION_SIGN_GREATER_THAN),
    SmallerThan(OptionId.OPTION_SIGN_SMALLER_THAN),
    GreaterThanEqualsTo(OptionId.OPTION_SIGN_GREATER_THAN_OR_EQUAL_TO),
    SmallerThanEqualsTo(OptionId.OPTION_SIGN_SMALLER_THAN_OR_EQUAL_TO),
    ;
    public final String id;

    OptionIdEnumForTest(String id) {
        this.id = id;
    }
}
