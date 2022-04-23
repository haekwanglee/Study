package introduce.specialcase;

public class BillingPlan {
    private static final BillingPlan basicPlan = new BillingPlan();

    public static BillingPlan basic() {
        return basicPlan;
    }
}
