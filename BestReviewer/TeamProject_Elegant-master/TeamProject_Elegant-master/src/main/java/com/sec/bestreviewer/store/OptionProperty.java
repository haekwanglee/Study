package com.sec.bestreviewer.store;

public class OptionProperty {

    private OptionAttributeType optionAttribute;
    private OptionValueType optionValue;

    public OptionProperty() {
        optionAttribute = OptionAttributeType.DEFAULT;
        optionValue = OptionValueType.VALUE_EQUAL;
    }

    public OptionProperty(Condition condition) {
        this.optionAttribute = condition.getOptionAttribute();
        this.optionValue = condition.getOptionValue();
    }

    public void setOptionAttribute(OptionAttributeType optionAttribute) {
        this.optionAttribute = optionAttribute;
    }

    public OptionAttributeType getOptionAttribute() {
        return optionAttribute;
    }

    public OptionValueType getOptionValue() {
        return optionValue;
    }

}
