package com.sec.bestreviewer.store.field;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeNumberTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "02712468",
            "03249872",
            "03717863",
            "09752663",
            "93728322",
            "94526445",
            "99398134",
    })
    public void testEquals(String number) {
        EmployeeNumber field = new EmployeeNumber("90000000");
        field.setValue(number);
        assertTrue(field.equals(number));
        assertTrue(field.equals(number, ""));
        assertEquals(field.toString(), number);
    }

    @ParameterizedTest
    @ValueSource(ints = {
            2712468,
            3249872,
            3717863,
            9752663,
            93728322,
            94526445,
            99398134,
    })
    public void testCompareMethods(int number) {
        String numberString = String.format("%08d", number);
        EmployeeNumber field = new EmployeeNumber(numberString);
        assertEquals(0, field.compareTo(numberString));
        assertEquals(0, field.compareTo(numberString, ""));

        assertTrue(field.compareTo(String.format("%08d", number - 1)) > 0);
        assertTrue(field.compareTo(String.format("%08d", number - 1), "") > 0);

        assertTrue(field.compareTo(String.format("%08d", number + 1)) < 0);
        assertTrue(field.compareTo(String.format("%08d", number + 1), "") < 0);
    }

    @ParameterizedTest
    @CsvSource({
            "93728322,02712468",
            "94526445,03249872",
            "99398134,03717863",
    })
    public void testCompareDifferentCentury(String low, String high) {
        EmployeeNumber field = new EmployeeNumber(low);
        assertTrue(field.compareTo(high) < 0);
    }
}
