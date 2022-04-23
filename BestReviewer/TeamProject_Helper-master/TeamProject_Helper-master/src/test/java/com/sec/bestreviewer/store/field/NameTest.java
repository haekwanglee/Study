package com.sec.bestreviewer.store.field;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {
    @ParameterizedTest
    @CsvSource({
            "UAFBOWU HONG",
            "NSESFBLRK KIM",
            "QSJYRB HONG",
            "TYLNXWQN OH",
    })
    public void testEquals(String name) {
        Name field = new Name("DEFAULT NAME");
        field.setValue(name);
        assertTrue(field.equals(name));
        assertEquals(0, field.compareTo(name));
    }

    @ParameterizedTest
    @CsvSource({
            "UAFBOWU,HONG",
            "NSESFBLRK,KIM",
            "QSJYRB,HONG",
            "TYLNXWQN,OH",
    })
    void testEqualsValueOptionTest(String firstName, String lastName) {
        String fullName = String.format("%s %s", firstName, lastName);
        Name name = new Name(fullName);
        assertTrue(name.equals(firstName, "-f"));
        assertTrue(name.equals(lastName, "-l"));

        assertEquals(name.getFirstName(), firstName);
        assertEquals(name.getLastName(), lastName);
    }

    @ParameterizedTest
    @CsvSource({
            "UAFBOWU,HONG",
            "NSESFBLRK,KIM",
            "QSJYRB,HONG",
            "TYLNXWQN,OH",
    })
    public void testComparesToWithOption(String firstName, String lastName) {
        String fullName = String.format("%s %s", firstName, lastName);
        Name name = new Name(fullName);

        assertEquals(0, name.compareTo(firstName, "-f"));
        assertTrue(name.compareTo(getGreater(firstName), "-f") < 0);
        assertTrue(name.compareTo(getLess(firstName), "-f") > 0);

        assertEquals(0, name.compareTo(lastName, "-l"));
        assertTrue(name.compareTo(getGreater(lastName), "-l") < 0);
        assertTrue(name.compareTo(getLess(lastName), "-l") > 0);
    }

    String getGreater(String value) {
        return value + "A";
    }

    String getLess(String value) {
        return value.substring(0, value.length() - 1);
    }
}
