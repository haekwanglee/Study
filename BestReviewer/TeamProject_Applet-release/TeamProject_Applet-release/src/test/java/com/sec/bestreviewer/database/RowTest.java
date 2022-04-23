package com.sec.bestreviewer.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RowTest {

    public static final String[] TEST_DATA = {"test1", "test2", "test3"};

    private Row row;

    @BeforeEach
    public void setup() {
        row = new Row(TEST_DATA);
    }

    @Test
    public void testGetValue() {
        for (int i = 0; i < TEST_DATA.length; i++) {
            Assertions.assertEquals(TEST_DATA[i], row.getValue(i));
        }
    }

    @Test
    public void testEqualsWithNull() {
        Row row = new Row();
        Row nullRow = null;
        Assertions.assertFalse(row.equals(nullRow));
        Assertions.assertFalse(row.equals(new Object()));
    }
}
