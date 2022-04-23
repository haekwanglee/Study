package com.sec.bestreviewer.option;

import java.util.List;

public class EqualityOption {

    EqualityOptionType optionType;

    public EqualityOption(List<String> options) {
        optionType = EqualityOptionType.EQUAL_TO;
        for (String optVal : options) {
            switch (optVal) {
                case "-g":
                    optionType = EqualityOptionType.GREATER_THAN;
                    break;
                case "-ge":
                    optionType = EqualityOptionType.GREATER_THAN_OR_EQUAL_TO;
                    break;
                case "-s":
                    optionType = EqualityOptionType.SMALLER_THAN;
                    break;
                case "-se":
                    optionType = EqualityOptionType.SMALLER_THAN_OR_EQUAL_TO;
                    break;
            }
        }
    }

    public EqualityOptionType getType() {
        return optionType;
    }

}
