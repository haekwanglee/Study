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

    private double getCharge(Plan pricingPlan, Order order) {
        double charge;
        charge = pricingPlan.base + order.units * pricingPlan.unit;
        charge = charge - getDiscount(pricingPlan, order);
        return charge;
    }

    private double getDiscount(Plan pricingPlan, Order order) {
        double discount;
        double discountableUnits = Math.max(order.units - pricingPlan.discountThreshold, 0);
        discount = discountableUnits * pricingPlan.discountFactor;
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
