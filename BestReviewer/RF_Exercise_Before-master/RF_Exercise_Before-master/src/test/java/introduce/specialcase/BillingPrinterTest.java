package introduce.specialcase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BillingPrinterTest {
    private Company company;
    private Customer customer;
    private BillingPlan plan;

    @BeforeEach
    public void setUp() {
        company = new Company();
        customer = new Customer("Johnny");
        plan = new BillingPlan();
        PaymentHistory history = new PaymentHistory();
        history.setWeeksDelinquentInLastYear(30);
        customer.setPaymentHistory(history);
    }

    @Test
    public void testBilling() {
        customer.setBillingPlan(plan);
        company.setCustomer(customer);
        assertEquals(new BillInfo("Johnny", plan, 30)
                , BillingPrinter.billing(company.getCustomer()));
    }

    @Test
    public void testBilling_nullCustomer() {
        assertEquals(new BillInfo("Occupant", BillingPlan.basic(), 0)
                , BillingPrinter.billing(company.getCustomer()));
    }
}