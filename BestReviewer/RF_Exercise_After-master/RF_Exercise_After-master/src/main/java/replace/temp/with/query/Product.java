package replace.temp.with.query;

public class Product {

    double quantity;
    double itemPrice;

    Product(double quantity, double itemPrice){
        this.quantity = quantity;
        this.itemPrice = itemPrice;

    }

    public double getPrice() {
        double discountFactor;

        if(basePrice() > 1000){
            discountFactor = 0.95;
        }
        else{
            discountFactor = 0.98;
        }
        return basePrice() * discountFactor;
    }

    private double basePrice() {
        return quantity * itemPrice;
    }
}