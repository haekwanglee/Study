package message.chain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ZipCodeTest {

    private Order o;

    @BeforeEach
    public void setUp() {
        o = new Order();
        Customer c = new Customer("Kent");
        c.setAddress(new Address("06366"));
        o.setCustomer(c);
    }

    @Test
    public void testZipCode() {
        assertEquals("06366", o.getCustomer().getAddress().getZipCode());
    }
}
