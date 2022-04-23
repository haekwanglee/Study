package introduce.specialcase;

public class BillingPrinter {
    static BillInfo billing(introduce.specialcase.Customer customer) {
        String customerName;
        customerName = customer.getName();
        BillingPlan plan = customer.getPlan();
        int weeksDelinquent = customer.getHistory().getWeeksDelinquentInLastYear();

        return new BillInfo(customerName, plan, weeksDelinquent);
    }

}
