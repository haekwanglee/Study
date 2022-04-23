package introduce.specialcase;

public class BillingPlan {
    private static final introduce.specialcase.BillingPlan basicPlan = new introduce.specialcase.BillingPlan();

    public static introduce.specialcase.BillingPlan basic() {
        return basicPlan;
    }
}
