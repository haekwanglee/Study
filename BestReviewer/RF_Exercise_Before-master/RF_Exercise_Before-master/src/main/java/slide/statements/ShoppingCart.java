package slide.statements;

public class ShoppingCart {
    private final Policy policy;
    private final Customer customer;

    public ShoppingCart(Policy policy, Customer customer) {
        this.policy = policy;
        this.customer = customer;
    }

    public void checkout() {
        Plan pricingPlan = retrievePricingPlan();
        Order order = retrieveOrder();
        double baseCharge = pricingPlan.base;
        double charge;
        double chargePerUnit = pricingPlan.unit;
        int units = order.units;
        double discount;
        charge = baseCharge + units * chargePerUnit;
        double discountableUnits = Math.max(units - pricingPlan.discountThreshold, 0);
        discount = discountableUnits * pricingPlan.discountFactor;
        if (order.isRepeat) discount += 20;
        charge = charge - discount;
        chargeOrder(charge);
    }

    void chargeOrder(double charge) {
        System.out.println("total charge=" + charge);
    }

    private Order retrieveOrder() {
        return customer.retrievePeekOrder();
    }

    private Plan retrievePricingPlan() {
        return policy.retrievePricingPlan();
    }
}
