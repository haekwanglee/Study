package com.sec.bestreviewer.store.searchfilter;

import com.sec.bestreviewer.base.*;

import java.util.Objects;

public class SearchFilterFactory {
    public static SearchFilterDelegator create(ConditionParameter param) {
        if (!OperatorId.NONE.equals(param.getOperator())) {
            return createCompositeConditionFilter(param);
        }

        return createSearchFilter(param.getFirst());
    }

    public static SearchFilterDelegator createSearchFilter(ConditionValue condition) {
        String fieldName = condition.getFieldName();
        switch (fieldName) {
            case FieldId.FIELD_EMPLOYEE_NUMBER:
                return new EmployeeNumberFilter();
            case FieldId.FIELD_NAME:
                return createNameFilter(condition);
            case FieldId.FIELD_CAREER_LEVEL:
                return new CareerLevelFilter();
            case FieldId.FIELD_PHONE_NUMBER:
                return createPhoneNumberFilter(condition);
            case FieldId.FIELD_BIRTH_DAY:
                return createBirthDayFilter(condition);
            case FieldId.FIELD_CERTI:
                return new CertiFilter();
        }

        return new DefaultFilter();
    }

    private static SearchFilterDelegator createNameFilter(ConditionValue condition) {
        switch (condition.getOption2()) {
            case OptionId.OPTION_NAME_FIRST:
                return new FirstNameFilter();
            case OptionId.OPTION_NAME_LAST:
                return new LastNameFilter();
        }
        return new NameFilter();
    }

    private static SearchFilterDelegator createPhoneNumberFilter(ConditionValue condition) {
        switch (condition.getOption2()) {
            case OptionId.OPTION_PHONE_NUMBER_MIDDLE:
                return new MiddlePhoneNumberFilter();
            case OptionId.OPTION_PHONE_NUMBER_LAST:
                return new LastPhoneNumberFilter();
        }
        return new PhoneNumberFilter();
    }

    private static SearchFilterDelegator createBirthDayFilter(ConditionValue condition) {
        switch (condition.getOption2()) {
            case OptionId.OPTION_BIRTHDAY_YEAR:
                return new BirthdayYearFilter();
            case OptionId.OPTION_BIRTHDAY_MONTH:
                return new BirthdayMonthFilter();
            case OptionId.OPTION_BIRTHDAY_DAY:
                return new BirthdayDayFilter();
        }
        return new BirthdayFilter();
    }

    private static SearchFilterDelegator createCompositeConditionFilter(ConditionParameter param) {
        return new CompositeConditionFilter(
                createSearchFilter(param.getFirst()),
                createSearchFilter(Objects.requireNonNull(param.getSecond()))
        );
    }
}
