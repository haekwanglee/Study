package com.sec.bestreviewer.store.modifier;

import com.sec.bestreviewer.base.FieldId;
import com.sec.bestreviewer.base.Value;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployModifierFactoryTest {
    public static final String TEST_PHONE_NUMBER = "010-9530-6183";
    public static final String CERTI_EX = "EX";
    public static final String TEST_NAME = "Changjin Jeong";
    public static final String CL_4 = "CL4";
    public static final String TEST_BIRTH_DAY = "19001010";

    @Test
    @DisplayName("EmployeeBirthdayModifier 테스트")
    void buildEmployeeBirthdayModifier() {
        Value param = new Value(FieldId.FIELD_BIRTH_DAY, TEST_BIRTH_DAY);
        EmployeeModifier modifier = EmployeeModifierFactory.create(param);
        assertThat(modifier, instanceOf(EmployeeBirthdayModifier.class));
        assertEquals(TEST_BIRTH_DAY, ((EmployeeBirthdayModifier) modifier).getBirthday());
    }

    @Test
    @DisplayName("EmployeeCareerLevelModifier 테스트")
    void buildEmployeeCareerLevelModifier() {
        Value param = new Value(FieldId.FIELD_CAREER_LEVEL, CL_4);
        EmployeeModifier modifier = EmployeeModifierFactory.create(param);
        assertThat(modifier, instanceOf(EmployeeCareerLevelModifier.class));
        assertEquals(CL_4, ((EmployeeCareerLevelModifier) modifier).getCareerLevel());
    }

    @Test
    @DisplayName("EmployeeNameModifier 테스트")
    void buildEmployeeNameModifier() {
        Value param = new Value(FieldId.FIELD_NAME, TEST_NAME);
        EmployeeModifier modifier = EmployeeModifierFactory.create(param);
        assertThat(modifier, instanceOf(EmployeeNameModifier.class));
        assertEquals(TEST_NAME, ((EmployeeNameModifier) modifier).getName());
    }

    @Test
    @DisplayName("EmployeePhoneNumberModifier 테스트")
    void buildEmployeePhoneNumberModifier() {
        Value param = new Value(FieldId.FIELD_PHONE_NUMBER, TEST_PHONE_NUMBER);
        EmployeeModifier modifier = EmployeeModifierFactory.create(param);
        assertThat(modifier, instanceOf(EmployeePhoneNumberModifier.class));
        assertEquals(TEST_PHONE_NUMBER, ((EmployeePhoneNumberModifier) modifier).getPhoneNumber());
    }

    @Test
    @DisplayName("EmployeeCertiModifier 테스트")
    void buildEmployeeCertiModifier() {
        Value param = new Value(FieldId.FIELD_CERTI, CERTI_EX);
        EmployeeModifier modifier = EmployeeModifierFactory.create(param);
        assertThat(modifier, instanceOf(EmployeeCertiModifier.class));
        assertEquals(CERTI_EX, ((EmployeeCertiModifier) modifier).getCerti());
    }
}
