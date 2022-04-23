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

        double charge = getCharge(pricingPlan, order);
        chargeOrder(charge);
    }

    protected double getCharge(Plan pricingPlan, Order order) {
        int units = order.units;
        double charge = pricingPlan.base + units * pricingPlan.unit;
        double discount = getDiscount(pricingPlan, order);
        charge = charge - discount;

        return charge;
    }

    protected double getDiscount(Plan pricingPlan, Order order) {
        int units = order.units;
        double discountableUnits = Math.max(units - pricingPlan.discountThreshold, 0);
        double discount = discountableUnits * pricingPlan.discountFactor;
        if (order.isRepeat) discount += 20;
        return discount;
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
