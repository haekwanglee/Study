package com.sec.bestreviewer.store;

import com.sec.bestreviewer.util.Pair;

public class Condition {

    private final Pair<String, String> pair;
    private final OptionAttributeType optionAttribute;
    private final OptionValueType optionValue;

    public Condition(Pair<String, String> pair, String optionAttribute, String optionValue) {
        this.pair = pair;
        this.optionAttribute = OptionAttributeType.getTypeByKeyAndOption(pair.first, optionAttribute);
        this.optionValue = OptionValueType.getTypeByOptionName(optionValue);
    }

    public Pair<String, String> getPair() {
        return pair;
    }

    public OptionAttributeType getOptionAttribute() {
        return optionAttribute;
    }

    public OptionValueType getOptionValue() {
        return optionValue;
    }

}
