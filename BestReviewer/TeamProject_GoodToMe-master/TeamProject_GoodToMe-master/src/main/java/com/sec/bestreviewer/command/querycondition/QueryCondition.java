package com.sec.bestreviewer.command.querycondition;

import java.util.ArrayList;
import java.util.Arrays;

public class QueryCondition {
    private String[] options = {"", ""};
    private String[] params = {"", "", "", "", "", ""};

    public String getOption2() {
        return options[0];
    }

    public void setOption2(String option2) {
        this.options[0] = option2;
    }

    public String getOption3() {
        return options[1];
    }

    public void setOption3(String option3) {
        this.options[1] = option3;
    }

    public String getColumn() {
        return params[0];
    }

    public void setColumn(String column1) {
        this.params[0] = column1;
    }

    public String getValue() {
        return params[1];
    }

    public void setValue(String value1) {
        this.params[1] = value1;
    }

}
