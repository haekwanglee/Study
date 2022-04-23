package introduce.specialcase;

public class UnknownCustomer extends Customer {
    UnknownCustomer() {
        super("");
    }

    @Override
    public String getName() {
        return "Occupant";
    }

    @Override
    public BillingPlan getPlan() {
        return BillingPlan.basic();
    }
}
