package com.sec.bestreviewer.database.field;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CertiFieldTest {
    public static final String ADV = "ADV";
    public static final String PRO = "PRO";
    public static final String EX = "EX";

    private static final String TEST_FIELD_NAME = "TestField";
    private static final int TEST_FIELD_INDEX = 0;


    private CertiField certiField;

    @BeforeEach
    public void setup() {
        certiField = new CertiField(TEST_FIELD_NAME, TEST_FIELD_INDEX);
    }

    @Test
    public void testStringFieldData() {
        Assertions.assertEquals(TEST_FIELD_NAME, certiField.getName());
        Assertions.assertEquals(TEST_FIELD_INDEX, certiField.getIndex());
    }

    @Test
    public void testCompareTo() {
        Assertions.assertEquals(0, certiField.compare(ADV, ADV));
        Assertions.assertEquals(0, certiField.compare(PRO, PRO));
        Assertions.assertEquals(0, certiField.compare(EX, EX));

        Assertions.assertEquals(1, certiField.compare(PRO, ADV));
        Assertions.assertEquals(1, certiField.compare(EX, ADV));
        Assertions.assertEquals(1, certiField.compare(EX, PRO));

        Assertions.assertEquals(-1, certiField.compare(ADV, PRO));
        Assertions.assertEquals(-1, certiField.compare(ADV, EX));
        Assertions.assertEquals(-1, certiField.compare(PRO, EX));
    }

    @Test
    public void testValidate() {
        Assertions.assertTrue(certiField.validate(ADV));
        Assertions.assertTrue(certiField.validate(PRO));
        Assertions.assertTrue(certiField.validate(EX));

        Assertions.assertFalse(certiField.validate(null));
        Assertions.assertFalse(certiField.validate("1"));
        Assertions.assertFalse(certiField.validate("CL1"));
        Assertions.assertFalse(certiField.validate("CL2"));
    }
}
