package introduce.parameter.object;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    @Test
    public void testFlowBetween() {
        Account account = new Account();
        account.addTransaction(new Transaction(1000, new Date(2019, 7, 16)));
        account.addTransaction(new Transaction(2000, new Date(2019, 10, 25)));
        account.addTransaction(new Transaction(3000, new Date(2007, 9, 1)));
        account.addTransaction(new Transaction(4000, new Date(2010, 6, 30)));

        Date startDate = new Date(2008, 0, 1);
        Date endDate = new Date(2019, 10, 14);
        assertEquals(5000.0, account.getFlowBetween(startDate, endDate));
    }
}