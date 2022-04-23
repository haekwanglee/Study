package com.sec.bestreviewer.database.field;

import com.sec.bestreviewer.database.field.StringField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StringFieldTest {

    private static final String TEST_FIELD_NAME = "TestField";
    private static final int TEST_FIELD_INDEX = 0;

    private StringField stringField;

    @BeforeEach
    public void setup() {
        stringField = new StringField(TEST_FIELD_NAME, TEST_FIELD_INDEX);
    }

    @Test
    public void testStringFieldData() {
        Assertions.assertEquals(TEST_FIELD_NAME, stringField.getName());
        Assertions.assertEquals(TEST_FIELD_INDEX, stringField.getIndex());
    }

    @Test
    public void testCompareTo() {
        Assertions.assertEquals(0, stringField.compare("abcd", "abcd"));
        Assertions.assertNotEquals(0, stringField.compare("abcd", "abcde"));
    }

    @Test
    public void testValidate() {
        Assertions.assertTrue(stringField.validate("asdf"));
        Assertions.assertTrue(stringField.validate(""));
        Assertions.assertFalse(stringField.validate(null));
    }
}
