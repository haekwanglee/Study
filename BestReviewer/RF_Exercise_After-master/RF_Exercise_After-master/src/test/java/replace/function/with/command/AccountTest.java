package replace.function.with.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    @Test
    public void testGamma_yearToDate_greater_than_importantValue1_hundred(){
        Account account = new Account();
        assertEquals(15396520, account.gamma(1000,2,2200));
    }

    @Test
    public void testGamma_yearToDate_less_than_importantValue1_hundred(){
        Account account = new Account();
        assertEquals(14696660, account.gamma(1000,2,2100));
    }
}