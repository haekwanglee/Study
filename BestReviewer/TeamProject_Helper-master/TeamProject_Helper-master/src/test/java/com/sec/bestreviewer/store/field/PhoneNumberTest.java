package com.sec.bestreviewer.store.field;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneNumberTest {

    @ParameterizedTest
    @CsvSource({
            "010-1234-5678",
            "010-5678-1234",
    })
    public void testSetValue(String number) {
        PhoneNumber phoneNumber = new PhoneNumber("010-1234-1234");
        phoneNumber.setValue(number);
        assertTrue(phoneNumber.equals(number));
    }

    @ParameterizedTest
    @CsvSource({
            "010-1234-5678",
            "010-5678-1234",
    })
    public void testEquals(String number) {
        PhoneNumber phoneNumber = new PhoneNumber(number);
        assertTrue(phoneNumber.equals(number));
    }

    @ParameterizedTest
    @CsvSource({
            "010-1234-5678, 1234",
    })
    public void testShouldMidNumberEqual(String number, String midNum) {
        PhoneNumber phoneNumber = new PhoneNumber(number);
        assertTrue(phoneNumber.equals(midNum, "-m"));
    }

    @ParameterizedTest
    @CsvSource({
            "010-1234-5678, 5678",
    })
    public void testShouldMidNumberNotEqual(String number, String midNum) {
        PhoneNumber phoneNumber = new PhoneNumber(number);
        assertFalse(phoneNumber.equals(midNum, "-m"));
    }

    @ParameterizedTest
    @CsvSource({
            "010-1234-5678, 5678",
    })
    public void testShouldLastNumberEqual(String number, String lastNum) {
        PhoneNumber num1 = new PhoneNumber(number);
        assertTrue(num1.equals(lastNum, "-l"));
    }

    @ParameterizedTest
    @CsvSource({
            "010-1234-5678, 1234",
    })
    public void testShouldLastNumberNotEqual(String number, String lastNum) {
        PhoneNumber num1 = new PhoneNumber(number);
        assertFalse(num1.equals(lastNum, "-l"));
    }

    @ParameterizedTest
    @CsvSource({
            "010-1111-0001, 010-1111-0000, true",
            "010-1111-0000, 010-1111-0000, false",
            "010-1111-0000, 010-1111-0001, false",
    })
    public void testGreaterThan(String source, String target, boolean expected) {
        PhoneNumber sourceNum = new PhoneNumber(source);
        assertEquals(sourceNum.compareTo(target) > 0, expected);
    }

    @ParameterizedTest
    @CsvSource({
            "010-1111-0001, 010-1111-0000, true",
            "010-1111-0000, 010-1111-0000, true",
            "010-1111-0000, 010-1111-0001, false",
            "010-0001-0000, 010-0000-0000, true",
    })
    public void testGreaterThanOrEquals(String source, String target, boolean expected) {
        PhoneNumber sourceNum = new PhoneNumber(source);
        assertEquals(sourceNum.compareTo(target) >= 0, expected);
    }

    @ParameterizedTest
    @CsvSource({
            "010-1111-0001, 010-1111-0000, false",
            "010-1111-0000, 010-1111-0000, false",
            "010-1111-0000, 010-1111-0001, true",
            "010-0001-0000, 010-0000-0000, false",
    })
    public void lessThan(String source, String target, boolean expected) {
        PhoneNumber sourceNum = new PhoneNumber(source);
        assertEquals(sourceNum.compareTo(target) < 0, expected);
    }

    @ParameterizedTest
    @CsvSource({
            "010-1111-0001, 010-1111-0000, false",
            "010-1111-0000, 010-1111-0000, true",
            "010-1111-0000, 010-1111-0001, true",
            "010-0001-0000, 010-0000-0000, false",
    })
    public void testLessThanOrEquals(String source, String target, boolean expected) {
        PhoneNumber sourceNum = new PhoneNumber(source);
        assertEquals(sourceNum.compareTo(target) <= 0, expected);
    }

    @ParameterizedTest
    @CsvSource({
            "010-1111-0000, 0000, -m, true",
            "010-1111-0000, 1111, -m, false",
            "010-1111-0000, 2222, -m, false",
            "010-0000-1111, 0000, -l, true",
            "010-0000-1111, 1111, -l, false",
            "010-0000-1111, 2222, -l, false",
    })
    public void testGreaterThanWithOption(String source, String target, String option, boolean result) {
        PhoneNumber sourceNum = new PhoneNumber(source);
        assertEquals(sourceNum.compareTo(target, option) > 0, result);
    }

    @ParameterizedTest
    @CsvSource({
            "010-1111-0000, 0000, -m, true",
            "010-1111-0000, 1111, -m, true",
            "010-1111-0000, 2222, -m, false",
            "010-0000-1111, 0000, -l, true",
            "010-0000-1111, 1111, -l, true",
            "010-0000-1111, 2222, -l, false",
    })
    public void testGreaterThanOrEqualsWithOption(String source, String target, String option, boolean result) {
        PhoneNumber sourceNum = new PhoneNumber(source);
        assertEquals(sourceNum.compareTo(target, option) >= 0, result);
    }

    @ParameterizedTest
    @CsvSource({
            "010-1111-0000, 0000, -m, false",
            "010-1111-0000, 1111, -m, false",
            "010-1111-0000, 2222, -m, true",
            "010-0000-1111, 0000, -l, false",
            "010-0000-1111, 1111, -l, false",
            "010-0000-1111, 2222, -l, true",
    })
    public void testLessThanWithOption(String source, String target, String option, boolean result) {
        PhoneNumber sourceNum = new PhoneNumber(source);
        assertEquals(sourceNum.compareTo(target, option) < 0, result);
    }

    @ParameterizedTest
    @CsvSource({
            "010-1111-0000, 0000, -m, false",
            "010-1111-0000, 1111, -m, true",
            "010-1111-0000, 2222, -m, true",
            "010-0000-1111, 0000, -l, false",
            "010-0000-1111, 1111, -l, true",
            "010-0000-1111, 2222, -l, true",
    })
    public void testLessThanOrEqualsWithOption(String source, String target, String option, boolean result) {
        PhoneNumber sourceNum = new PhoneNumber(source);
        assertEquals(sourceNum.compareTo(target, option) <= 0, result);
    }

    @Test
    public void testToString() {
        String stringNumber = "010-1234-5678";
        PhoneNumber number = new PhoneNumber(stringNumber);
        assertEquals(number.toString(), stringNumber);
    }
}
