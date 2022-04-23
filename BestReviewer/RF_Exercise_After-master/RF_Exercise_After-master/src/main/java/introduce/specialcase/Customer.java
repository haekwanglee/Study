package introduce.specialcase;

public class Customer {
    private String name;
    private BillingPlan billingPlan;
    private PaymentHistory paymentHistory;

    Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public BillingPlan getPlan() {
        return billingPlan;
    }

    public PaymentHistory getHistory() {

        return paymentHistory == null ? new NullPaymentHistory(): paymentHistory;
    }

    public void setBillingPlan(BillingPlan billingPlan) {
        this.billingPlan = billingPlan;
    }

    public void setPaymentHistory(PaymentHistory paymentHistory) {
        this.paymentHistory = paymentHistory;
    }
}
