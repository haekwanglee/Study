package com.sec.bestreviewer.store.field;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class CertificateTest {

    @ParameterizedTest
    @CsvSource({
            "ADV",
            "PRO",
            "EX"
    })
    public void testEquals(String level) {
        Certificate certi = new Certificate("PRO");
        certi.setValue(level);
        assertTrue(certi.equals(level));
    }

    @ParameterizedTest
    @CsvSource({
            "ADV, ADV, false",
            "ADV, PRO, false",
            "ADV, EX, false",
            "PRO, ADV, true",
            "PRO, PRO, false",
            "PRO, EX, false",
            "EX, ADV, true",
            "EX, PRO, true",
            "EX, EX, false",
    })
    public void testGreaterThan(String source, String target, boolean expected) {
        Certificate certi = new Certificate(source);
        assertEquals(certi.compareTo(target) > 0, expected);
    }

    @ParameterizedTest
    @CsvSource({
            "ADV, ADV, true",
            "ADV, PRO, false",
            "ADV, EX, false",
            "PRO, ADV, true",
            "PRO, PRO, true",
            "PRO, EX, false",
            "EX, ADV, true",
            "EX, PRO, true",
            "EX, EX, true",
    })
    public void testGreaterOrEqualsThan(String source, String target, boolean expected) {
        Certificate certi = new Certificate(source);
        assertEquals(certi.compareTo(target) >= 0, expected);
    }

    @ParameterizedTest
    @CsvSource({
            "ADV, ADV, false",
            "ADV, PRO, true",
            "ADV, EX, true",
            "PRO, ADV, false",
            "PRO, PRO, false",
            "PRO, EX, true",
            "EX, ADV, false",
            "EX, PRO, false",
            "EX, EX, false",
    })
    public void testLessThan(String source, String target, boolean expected) {
        Certificate certi = new Certificate(source);
        assertEquals(certi.compareTo(target) < 0, expected);
    }

    @ParameterizedTest
    @CsvSource({
            "ADV, ADV, true",
            "ADV, PRO, true",
            "ADV, EX, true",
            "PRO, ADV, false",
            "PRO, PRO, true",
            "PRO, EX, true",
            "EX, ADV, false",
            "EX, PRO, false",
            "EX, EX, true",
    })
    public void testLessThanOrEquals(String source, String target, boolean expected) {
        Certificate certi = new Certificate(source);
        assertEquals(certi.compareTo(target) <= 0, expected);
    }

    @ParameterizedTest
    @CsvSource({
            "ADV",
            "PRO",
            "EX"
    })
    public void testToString(String level) {
        Certificate certi = new Certificate(level);
        assertEquals(certi.toString(), level);
    }
}
