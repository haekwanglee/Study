package extract.klazz;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    @Test
    public void testTelephoneNumber() {
        Person p = new Person();
        p.setOfficeAreaCode("031");
        p.setOfficeNumber("8031-3112");

        assertEquals("(031) 8031-3112", p.getTelephoneNumber());
    }
}