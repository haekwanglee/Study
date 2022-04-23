package com.sec.bestreviewer.store.modifier;

import com.sec.bestreviewer.base.FieldId;
import com.sec.bestreviewer.base.Value;

public class EmployeeModifierFactory {

    public static EmployeeModifier create(Value param) {
        String fieldName = param.getFieldName();
        String value = param.getValue();
        switch (fieldName) {
            case FieldId.FIELD_NAME:
                return new EmployeeNameModifier(value);
            case FieldId.FIELD_CAREER_LEVEL:
                return new EmployeeCareerLevelModifier(value);
            case FieldId.FIELD_PHONE_NUMBER:
                return new EmployeePhoneNumberModifier(value);
            case FieldId.FIELD_BIRTH_DAY:
                return new EmployeeBirthdayModifier(value);
            case FieldId.FIELD_CERTI:
                return new EmployeeCertiModifier(value);
            default:
                return new EmployeeDefaultModifier();
        }
    }
}
