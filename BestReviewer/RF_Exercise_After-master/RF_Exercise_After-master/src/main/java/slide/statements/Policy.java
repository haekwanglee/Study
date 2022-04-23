package slide.statements;

public class Policy {
    private final Plan pricingPlan;

    public Policy(Plan pricingPlan) {
        this.pricingPlan = pricingPlan;
    }

    public Plan retrievePricingPlan() {
        return pricingPlan;
    }
}
