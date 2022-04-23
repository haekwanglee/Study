package introduce.specialcase;

public class BillingPrinter {
    static BillInfo billing(Customer customer) {
        String customerName;
        if (customer == null) {
            customerName = "Occupant";
        }
        else {
            customerName = customer.getName();
        }

        BillingPlan plan;
        if (customer == null) {
            plan = BillingPlan.basic();
        }
        else {
            plan = customer.getPlan();
        }

        int weeksDelinquent;
        if (customer == null) {
            weeksDelinquent = 0;
        }
        else {
            weeksDelinquent = customer.getHistory().getWeeksDelinquentInLastYear();
        }
        return new BillInfo(customerName, plan, weeksDelinquent);
    }

}
