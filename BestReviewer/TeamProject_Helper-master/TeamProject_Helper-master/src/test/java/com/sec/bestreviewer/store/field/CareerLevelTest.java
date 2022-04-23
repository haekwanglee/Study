package com.sec.bestreviewer.store.field;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CareerLevelTest {

    @ParameterizedTest
    @ValueSource(strings = {"CL1", "CL2", "CL3", "CL4"})
    void testEquals(String level) {
        CareerLevel careerLevel = new CareerLevel("CL1");
        careerLevel.setValue(level);
        assertTrue(careerLevel.equals(level));
        assertTrue(careerLevel.equals(level, ""));
    }

    @Test
    void testCompareTo() {
        for (int i = 1; i <= 4; i++) {
            CareerLevel careerLevel = new CareerLevel(getCareerLevelString(i));

            assertEquals(0, careerLevel.compareTo(getCareerLevelString(i)));
            assertEquals(0, careerLevel.compareTo(getCareerLevelString(i), ""));
            for (int j = i - 1; j > 0; j--) {
                assertTrue(careerLevel.compareTo(getCareerLevelString(j)) > 0);
                assertTrue(careerLevel.compareTo(getCareerLevelString(j), "") > 0);
            }
            for (int j = i + 1; j <= 4; j++) {
                assertTrue(careerLevel.compareTo(getCareerLevelString(j)) < 0);
                assertTrue(careerLevel.compareTo(getCareerLevelString(j), "") < 0);
            }
        }
    }

    String getCareerLevelString(int level) {
        return String.format("CL%d", level);
    }
}
