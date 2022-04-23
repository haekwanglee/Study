package com.sec.bestreviewer.database.field;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CareerLevelFieldTest {

    private static final String TEST_FIELD_NAME = "TestField";
    private static final int TEST_FIELD_INDEX = 0;
    public static final String CL1 = "CL1";
    public static final String CL2 = "CL2";
    public static final String CL3 = "CL3";
    public static final String CL4 = "CL4";

    private CareerLevelField careerLevelField;

    @BeforeEach
    public void setup() {
        careerLevelField = new CareerLevelField(TEST_FIELD_NAME, TEST_FIELD_INDEX);
    }

    @Test
    public void testStringFieldData() {
        Assertions.assertEquals(TEST_FIELD_NAME, careerLevelField.getName());
        Assertions.assertEquals(TEST_FIELD_INDEX, careerLevelField.getIndex());
    }

    @Test
    public void testCompareTo() {
        Assertions.assertEquals(0, careerLevelField.compare(CL1, CL1));
        Assertions.assertEquals(0, careerLevelField.compare(CL2, CL2));
        Assertions.assertEquals(0, careerLevelField.compare(CL3, CL3));
        Assertions.assertEquals(0, careerLevelField.compare(CL4, CL4));

        Assertions.assertEquals(1, careerLevelField.compare(CL2, CL1));
        Assertions.assertEquals(1, careerLevelField.compare(CL3, CL2));
        Assertions.assertEquals(1, careerLevelField.compare(CL4, CL1));

        Assertions.assertEquals(-1, careerLevelField.compare(CL3, CL4));
        Assertions.assertEquals(-1, careerLevelField.compare(CL2, CL3));
        Assertions.assertEquals(-1, careerLevelField.compare(CL1, CL2));
    }

    @Test
    public void testValidate() {
        Assertions.assertTrue(careerLevelField.validate(CL1));
        Assertions.assertTrue(careerLevelField.validate(CL2));
        Assertions.assertTrue(careerLevelField.validate(CL3));
        Assertions.assertTrue(careerLevelField.validate(CL4));

        Assertions.assertFalse(careerLevelField.validate("PRO"));
        Assertions.assertFalse(careerLevelField.validate("1"));
        Assertions.assertFalse(careerLevelField.validate(null));
    }
}
