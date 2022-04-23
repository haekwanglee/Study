package com.sec.bestreviewer.store.field;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

public class BirthdayTest {

    @ParameterizedTest
    @CsvSource({
            "19861112, 1986, 11, 12",
            "12340103, 1234, 1, 3",
    })
    public void testShouldGetProperYearMonthDay(String birthDay, int expectedYear, int expectedMonth, int expectedDay) {
        Birthday birthday = new Birthday("19890824");
        birthday.setValue(birthDay);
        assertEquals(expectedYear, birthday.getYear());
        assertEquals(expectedMonth, birthday.getMonth());
        assertEquals(expectedDay, birthday.getDay());
    }

    @ParameterizedTest
    @CsvSource({
            "19861112",
            "20000102",
    })
    public void testShouldEqualBirthday(String birthDay) {
        Birthday birthday = new Birthday(birthDay);
        assertTrue(birthday.equals(birthDay));
    }

    @ParameterizedTest
    @CsvSource({
            "19861112, 20000102",
    })
    public void testShouldFalseBirthDay(String birthDay, String birthday2) {
        Birthday birthday = new Birthday(birthDay);
        assertFalse(birthday.equals(birthday2));
    }

    @Test
    public void testShouldEqualBirthDayWithOption() {
        String date = "19861112";
        String year = "1986";
        String month = "11";
        String day = "12";

        Birthday birthday = new Birthday(date);

        assertTrue(birthday.equals(year, "-y"));
        assertTrue(birthday.equals(month, "-m"));
        assertTrue(birthday.equals(day, "-d"));
        assertTrue(birthday.equals(date, ""));
    }

    @Test
    public void testShouldFalseBirthDayWithOption() {
        String date = "19861112";
        String year = "1111";
        String month = "1";
        String day = "2";

        Birthday birthday = new Birthday(date);

        assertFalse(birthday.equals(year, "-y"));
        assertFalse(birthday.equals(month, "-m"));
        assertFalse(birthday.equals(day, "-d"));
    }

    @Test
    public void testShouldPrintBirthDayWithToString() {
        String date = "19861112";
        Birthday birthday = new Birthday(date);
        assertEquals(date, birthday.toString());
    }

    @ParameterizedTest
    @CsvSource({
            "19861111, 19861111, false",
            "19861112, 19861111, true",
            "19861110, 19871111, false",
            "20000101, 19990101, true",
            "19990101, 20000101, false",
    })
    public void testGreaterThanTest(String source, String target, boolean ret) {
        Birthday birthday = new Birthday(source);
        assertEquals(birthday.compareTo(target) > 0, ret);
    }

    @ParameterizedTest
    @CsvSource({
            "19861111, 19861111, true",
            "19861112, 19861111, true",
            "19861110, 19861111, false",
            "20000101, 19991230, true",
            "19991230, 20000101, false",
    })
    public void testGreaterThanOrEqualTest(String source, String target, boolean ret) {
        Birthday birthday = new Birthday(source);
        assertEquals(birthday.compareTo(target) >= 0, ret);
    }

    @ParameterizedTest
    @CsvSource({
            "19861111, 19861111, false",
            "19861112, 19861111, false",
            "19861110, 19871111, true",
            "20000101, 19991230, false",
            "19991230, 20000101, true",
    })
    public void testLessThanTest(String source, String target, boolean ret) {
        Birthday birthday = new Birthday(source);
        assertEquals(birthday.compareTo(target) < 0, ret);
    }

    @ParameterizedTest
    @CsvSource({
            "19861111, 19861111, true",
            "19861112, 19861111, false",
            "19861110, 19871111, true",
            "20000101, 19991230, false",
            "19991230, 20000101, true",
    })
    public void testLessThanOrEqualTest(String source, String target, boolean ret) {
        Birthday birthday = new Birthday(source);
        assertEquals(birthday.compareTo(target) <= 0, ret);
    }


    @Test
    public void testCompareToWithOption() {
        Birthday birthday = new Birthday("19861107");
        assertTrue(birthday.compareTo("1985", "-y") > 0);
        assertTrue(birthday.compareTo("1986", "-y") == 0);
        assertTrue(birthday.compareTo("1987", "-y") < 0);

        assertTrue(birthday.compareTo("10", "-m") > 0);
        assertTrue(birthday.compareTo("11", "-m") == 0);
        assertTrue(birthday.compareTo("12", "-m") < 0);

        assertTrue(birthday.compareTo("06", "-d") > 0);
        assertTrue(birthday.compareTo("07", "-d") == 0);
        assertTrue(birthday.compareTo("08", "-d") < 0);
    }
}
