package replace.temp.with.query;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    @Test
    public void testPrice_basePriceOverThousand(){
        assertEquals(1900, new Product(4,500).getPrice());
    }


    @Test
    public void testPrice_basePriceEqualsThousand(){
        assertEquals(980, new Product(4,250).getPrice());
    }
}