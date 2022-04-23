package com.sec.bestreviewer.store;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhoneNumberTest {

    @ParameterizedTest
    @CsvSource({
            "010-1234-5678, 12345678",
            "010-9999-9999, 99999999",
            "010-0123-4567, 1234567",
            "010-0012-3456, 123456",
            "010-0001-2345, 12345",
            "010-0000-1234, 1234",
            "010-0000-0123, 123",
            "010-0000-0012, 12",
            "010-0000-0001, 1",
            "010-0000-0000, 0"
    })
    void testGetLastEightDigit(String phoneNumber, int lastEightDigit) {
        assertEquals(lastEightDigit, PhoneNumber.getLastEightDigit(phoneNumber));
    }
}
