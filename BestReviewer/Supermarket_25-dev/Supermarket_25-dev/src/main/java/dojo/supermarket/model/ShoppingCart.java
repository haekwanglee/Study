package dojo.supermarket.model;

import java.util.*;

public class ShoppingCart {

    private final List<ProductQuantity> items = new ArrayList<>();
    Map<Product, Double> productQuantities = new LinkedHashMap<>();
    List<ProductQuantity> getItems() {
        return new ArrayList<>(items);
    }
    void addItem(Product product) {
        this.addItemQuantity(product, 1.0);
    }
    Map<Product, Double> productQuantities() {
        return productQuantities;
    }

    public void addItemQuantity(Product product, double quantity) {
        items.add(new ProductQuantity(product, quantity));
        if (productQuantities.containsKey(product)) {
            productQuantities.put(product, productQuantities.get(product) + quantity);
        } else {
            productQuantities.put(product, quantity);
        }
    }

    void handleOffers(Receipt receipt, Map<Product, Offer> offers, SupermarketCatalog catalog) {
        productQuantities().keySet().stream()
                .filter(offers::containsKey)
                .map(product -> getDiscount(product, offers.get(product), catalog))
                .filter(Objects::nonNull)
                .forEach(receipt::addDiscount);
    }

    private Discount getDiscount(Product p, Offer offer, SupermarketCatalog catalog) {

        double quantity = productQuantities.get(p);
        double unitPrice = catalog.getUnitPrice(p);
        int quantityAsInt = (int) quantity;
        int offerAmount = getOfferAmount(offer);
        int numberOfXs = quantityAsInt / offerAmount;
        DiscountData discountData = new DiscountData(p, quantity, offer, unitPrice, quantityAsInt, numberOfXs);

        Map<SpecialOfferType, Discount> operators = getSpecialOfferTypeDiscountMap(discountData);
        return operators.get(offer.offerType);
    }

    //TODO: make offer type with factory
    private Map<SpecialOfferType, Discount> getSpecialOfferTypeDiscountMap(DiscountData discountData) {
        Map<SpecialOfferType, Discount> operators = new HashMap<>();
        operators.put(SpecialOfferType.ThreeForTwo, getDiscountOfThreeForTwo(discountData));
        operators.put(SpecialOfferType.TwoForAmount, getDiscountOfTwoForAmount(discountData));
        operators.put(SpecialOfferType.TenPercentDiscount, getDiscountOfTenPercentDiscount(discountData));
        operators.put(SpecialOfferType.FiveForAmount, getDiscountOfFiveForAmount(discountData));
        return operators;
    }

    private Discount getDiscountOfFiveForAmount(DiscountData discountData) {
        if (discountData.getQuantityAsInt() < 5) {
            return null;
        }
        double discountTotal = discountData.getUnitPrice() * discountData.getQuantity() -
                        (discountData.getOffer().argument * discountData.getNumberOfXs() +
                                discountData.getQuantityAsInt() % 5 * discountData.getUnitPrice());
        return new Discount(discountData.getP(), "5 for " + discountData.getOffer().argument, discountTotal);
    }

    private Discount getDiscountOfTenPercentDiscount(DiscountData discountData) {
        Discount discount;
        discount = new Discount(discountData.getP(),
                discountData.getOffer().argument + "% off",
                discountData.getQuantity() * discountData.getUnitPrice() * discountData.getOffer().argument / 100.0);
        return discount;
    }

    private Discount getDiscountOfTwoForAmount(DiscountData discountData) {
        if (discountData.getQuantityAsInt() < 2) {
            return null;
        }
        double total = discountData.getOffer().argument * (discountData.getQuantityAsInt() / 2) +
                discountData.getQuantityAsInt() % 2 * discountData.getUnitPrice();
        double discountN = discountData.getUnitPrice() * discountData.getQuantity() - total;
        return new Discount(discountData.getP(), "2 for " + discountData.getOffer().argument, discountN);
    }

    private Discount getDiscountOfThreeForTwo(DiscountData discountData) {
        if (discountData.getQuantityAsInt() <= 2) {
            return null;
        }
        double discountAmount = discountData.getQuantity() * discountData.getUnitPrice() -
                ((discountData.getNumberOfXs() * 2 * discountData.getUnitPrice()) +
                        discountData.getQuantityAsInt() % 3 * discountData.getUnitPrice());
        return new Discount(discountData.getP(), "3 for 2", discountAmount);
    }

    private int getOfferAmount(Offer offer) {
        int offerAmount = 1;
        if (offer.offerType == SpecialOfferType.ThreeForTwo) {
            offerAmount = 3;
        } else if (offer.offerType == SpecialOfferType.TwoForAmount) {
            offerAmount = 2;
        } else if (offer.offerType == SpecialOfferType.FiveForAmount) {
            offerAmount = 5;
        }
        return offerAmount;
    }
}

