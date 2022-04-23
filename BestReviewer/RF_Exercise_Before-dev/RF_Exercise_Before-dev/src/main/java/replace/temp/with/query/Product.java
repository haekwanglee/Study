package replace.temp.with.query;

public class Product {
    public static final double DISCOUNT_FACTOR_95 = 0.95;
    public static final double DISCOUNT_FACTOR_98 = 0.98;
    double quantity;
    double itemPrice;

    Product(double quantity, double itemPrice) {
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }

    public double getPrice() {
        if (getBasePrice() > 1000) {
            return getBasePrice() * DISCOUNT_FACTOR_95;
        }
        return getBasePrice() * DISCOUNT_FACTOR_98;
    }

    private double getBasePrice() {
        return quantity * itemPrice;
    }
}
