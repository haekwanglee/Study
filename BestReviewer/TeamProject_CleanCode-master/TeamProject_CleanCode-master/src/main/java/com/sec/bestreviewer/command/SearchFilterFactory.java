package com.sec.bestreviewer.command;

import com.sec.bestreviewer.data.SearchOption;

public class SearchFilterFactory {

    public SearchFilterMaker create(SearchOption option) {
        switch (option) {
            case EMPLOYEE_NUMBER:
                return new SearchEmployeeNumberCommand();
            case FULL_NAME:
            case FIRST_NAME:
            case LAST_NAME:
                return new SearchNameCommand();
            case FULL_BIRTHDAY:
            case BIRTHDAY_YEAR:
            case BIRTHDAY_MONTH:
            case BIRTHDAY_DAY:
                return new SearchBirthdayCommand();
            case CERTI:
                return new SearchCertiCommand();
            case CL:
                return new SearchClCommand();
            case FULL_NUMBER:
            case MIDDLE_NUMBER:
            case LAST_NUMBER:
                return new SearchPhoneNumberCommand();

            default:
                throw new IllegalArgumentException("not supported search option : " + option);
        }
    }
}
