package com.sec.bestreviewer.store;

public class CertiField extends Field {
    public CertiField(String value){
        super(value);
    }

    enum CertiLevel {
        ADV,

        PRO,

        EX
    }


    @Override
    public int toCompareTo(String value, String additionalCompareMethod) {

        CertiLevel myValue = CertiLevel.valueOf(this.value);
        CertiLevel valueToCompare = CertiLevel.valueOf(value);
        return myValue.ordinal() - valueToCompare.ordinal();
    }
}
