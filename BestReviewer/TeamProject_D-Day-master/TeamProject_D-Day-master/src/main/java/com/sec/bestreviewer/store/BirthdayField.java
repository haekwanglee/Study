package com.sec.bestreviewer.store;

import com.sec.bestreviewer.util.Options;

public class BirthdayField extends Field {
    private int year;
    private int month;
    private int day;

    public BirthdayField(String value) {
        super(value);
        setYYMMDD(value);
    }

    @Override
    public void setField(String value) {
        super.setField(value);
        setYYMMDD(value);
    }

    private void setYYMMDD(String value) {
        year = Integer.parseInt(value.substring(0, 4));
        month = Integer.parseInt(value.substring(4, 6));
        day = Integer.parseInt(value.substring(6));
    }

    @Override
    public int toCompareTo(String value, String filterOption) {
        switch (filterOption) {
            case Options.YEAR_OPTION:
                return year - Integer.parseInt(value);
            case Options.MONTH_OPTION:
                return month - Integer.parseInt(value);
            case Options.DAY_OPTION:
                return day - Integer.parseInt(value);
        }
        return super.toCompareTo(value, filterOption);
    }
}
