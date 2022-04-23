package dojo.supermarket.model;

public class DiscountData {
    private final Product p;
    private final double quantity;
    private final Offer offer;
    private final double unitPrice;
    private final int quantityAsInt;
    private final int numberOfXs;

    public DiscountData(Product p, double quantity, Offer offer, double unitPrice, int quantityAsInt, int numberOfXs) {
        this.p = p;
        this.quantity = quantity;
        this.offer = offer;
        this.unitPrice = unitPrice;
        this.quantityAsInt = quantityAsInt;
        this.numberOfXs = numberOfXs;
    }

    public Product getP() {
        return p;
    }

    public double getQuantity() {
        return quantity;
    }

    public Offer getOffer() {
        return offer;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantityAsInt() {
        return quantityAsInt;
    }

    public int getNumberOfXs() {
        return numberOfXs;
    }
}
