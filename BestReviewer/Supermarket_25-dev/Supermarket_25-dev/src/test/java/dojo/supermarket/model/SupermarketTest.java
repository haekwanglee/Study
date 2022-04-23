package dojo.supermarket.model;

import dojo.supermarket.HtmlReceiptPrinter;
import dojo.supermarket.OriginReceiptPrinter;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class SupermarketTest {

    // Todo: test all kinds of discounts are applied properly


    @DisplayName("ThreeForTwo discount")
    @ParameterizedTest
    @MethodSource
    public void threeForTwoDiscount(String testProduct,
                                    ProductUnit testUnit,
                                    int testQuantity,
                                    double normalPrice) {
        SupermarketCatalog catalog = new FakeCatalog();
        Product product = new Product(testProduct, testUnit);
        catalog.addProduct(product, normalPrice);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.ThreeForTwo, product, 0);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(product, testQuantity);

        int actualPayQuantity = testQuantity - (testQuantity / 3);
        double expectedTotalPrice = normalPrice * actualPayQuantity;

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(expectedTotalPrice, receipt.getTotalPrice(), 0.01);
        assertNotEquals(Collections.emptyList(), receipt.getDiscounts());
        assertEquals(1, receipt.getItems().size());
        //Approvals.verify(new ReceiptPrinter().printReceipt(receipt));
    }

    static Stream<Arguments> threeForTwoDiscount() {
        return Stream.of(
                // Buy two toothbrushes, get one free. Normal toothbrush price is €0.99
                arguments("toothbrush", ProductUnit.Each, 3, 0.99),
                arguments("toothbrush", ProductUnit.Each, 7, 0.99)
        );
    }

    @DisplayName("TenPercentDiscount discount")
    @ParameterizedTest
    @MethodSource
    public void tenPercentDiscount(String testProduct,
                                   ProductUnit testUnit,
                                   int testQuantity,
                                   double normalPrice,
                                   int specialPercent) {
        SupermarketCatalog catalog = new FakeCatalog();
        Product product = new Product(testProduct, testUnit);
        catalog.addProduct(product, normalPrice);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, product, specialPercent);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(product, testQuantity);

        double actualPayPrice = normalPrice - (normalPrice * specialPercent / 100);
        double expectedTotalPrice = actualPayPrice * testQuantity;

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(expectedTotalPrice, receipt.getTotalPrice(), 0.01);
        assertNotEquals(Collections.emptyList(), receipt.getDiscounts());
        assertEquals(1, receipt.getItems().size());
        //Approvals.verify(new ReceiptPrinter().printReceipt(receipt));
    }

    static Stream<Arguments> tenPercentDiscount() {
        return Stream.of(
                //20% discount on apples, normal price €1.99 per kilo.
                arguments("apples", ProductUnit.Kilo, 3, 1.99, 20),
                //10% discount on rice, normal price €2.49 per bag
                arguments("rice", ProductUnit.Each, 3, 2.49, 10),
                arguments("apples", ProductUnit.Kilo, 5, 1.99, 10)
        );
    }

    @DisplayName("FiveForAmount discount")
    @ParameterizedTest
    @MethodSource
    public void fiveForAmountDiscount(String testProduct,
                                      ProductUnit testUnit,
                                      int testQuantity,
                                      double normalPrice,
                                      double specialPrice) {
        SupermarketCatalog catalog = new FakeCatalog();
        Product product = new Product(testProduct, testUnit);
        catalog.addProduct(product, normalPrice);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.FiveForAmount, product, specialPrice);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(product, testQuantity);

        int normalPayQuantity = testQuantity % 5;
        int specialPayQuantity = testQuantity / 5;
        double expectedTotalPrice = (normalPayQuantity * normalPrice) + (specialPayQuantity * specialPrice);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(expectedTotalPrice, receipt.getTotalPrice(), 0.01);
        assertNotEquals(Collections.emptyList(), receipt.getDiscounts());
        assertEquals(1, receipt.getItems().size());
        //Approvals.verify(new ReceiptPrinter().printReceipt(receipt));
    }

    static Stream<Arguments> fiveForAmountDiscount() {
        return Stream.of(
                // Five tubes of toothpaste for €7.49, normal price €1.79
                arguments("toothpaste", ProductUnit.Each, 5, 1.79, 7.49),
                arguments("toothpaste", ProductUnit.Each, 7, 1.79, 7.49)
        );
    }

    @DisplayName("TwoForAmount discount")
    @ParameterizedTest
    @MethodSource
    public void twoForAmountDiscount(String testProduct,
                                     ProductUnit testUnit,
                                     int testQuantity,
                                     double normalPrice,
                                     double specialPrice) {
        SupermarketCatalog catalog = new FakeCatalog();
        Product product = new Product(testProduct, testUnit);
        catalog.addProduct(product, normalPrice);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.TwoForAmount, product, specialPrice);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(product, testQuantity);

        int normalPayQuantity = testQuantity % 2;
        int specialPayQuantity = testQuantity / 2;
        double expectedTotalPrice = (normalPayQuantity * normalPrice) + (specialPayQuantity * specialPrice);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(expectedTotalPrice, receipt.getTotalPrice(), 0.01);
        assertNotEquals(Collections.emptyList(), receipt.getDiscounts());
        assertEquals(1, receipt.getItems().size());
        //Approvals.verify(new ReceiptPrinter().printReceipt(receipt));
    }
    static Stream<Arguments> twoForAmountDiscount() {
        return Stream.of(
                //Two boxes of cherry tomatoes for €0.99, normal price €0.69 per box.
                arguments("cherry tomatoes", ProductUnit.Each, 3, 0.69, 0.99),
                arguments("cherry tomatoes", ProductUnit.Each, 7, 0.69, 0.99)
        );
    }

    @Test
    @DisplayName("origin printer : Test using approvals.verify()")
    public void originPrinterGoldenMasterTest() {
        Receipt receipt = makeTestReceipt();
        Approvals.verify(new OriginReceiptPrinter().printReceipt(receipt));
    }

    @Test
    @DisplayName("html printer : Test using approvals.verify()")
    public void htmlPrinterGoldenMasterTest() {
        Receipt receipt = makeTestReceipt();
        Approvals.verify(new HtmlReceiptPrinter().printReceipt(receipt));
    }

    private Receipt makeTestReceipt() {
        List<List<Object>> goldenMasterTestParams = Arrays.asList(
                Arrays.asList("apples", ProductUnit.Kilo, 1.99, 2.5, false),
                Arrays.asList("toothbrush", ProductUnit.Each, 0.99, 5.0, true,
                        SpecialOfferType.TenPercentDiscount, 20.0),
                Arrays.asList("cherry tomato", ProductUnit.Each, 0.69, 2.0, true,
                        SpecialOfferType.TwoForAmount, 0.99),
                Arrays.asList("rice", ProductUnit.Each, 2.49, 4.0, true,
                        SpecialOfferType.TenPercentDiscount, 10.0));

        SupermarketCatalog catalog = new FakeCatalog();
        ShoppingCart cart = new ShoppingCart();
        Teller teller = new Teller(catalog);
        for (List<Object> testParamList: goldenMasterTestParams) {
            TestData testParam = new TestData(testParamList);
            catalog.addProduct(testParam.getProduct(), testParam.getPrice());
            cart.addItemQuantity(testParam.getProduct(), testParam.getQuantity());
            if (testParam.isDiscounted()) {
                teller.addSpecialOffer(testParam.getSpecialOfferType(), testParam.getProduct(), testParam.getArgument());
            }
        }

        return teller.checksOutArticlesFrom(cart);
    }

    static class TestData {
        String productName;
        ProductUnit productUnit;
        Double price;
        Double quantity;
        Boolean isDiscounted;
        SpecialOfferType specialOfferType;
        Double argument;

        Product product;

        public TestData(List<Object> paramObjects) {
            this.productName = (String) paramObjects.get(0);
            this.productUnit = (ProductUnit) paramObjects.get(1);
            this.price = (Double) paramObjects.get(2);
            this.quantity = (Double) paramObjects.get(3);
            this.isDiscounted = (Boolean) paramObjects.get(4);
            if (isDiscounted) {
                this.specialOfferType = (SpecialOfferType) paramObjects.get(5);
                this.argument = (Double) paramObjects.get(6);
            }

            this.product = new Product((String) paramObjects.get(0), (ProductUnit) paramObjects.get(1));
        }

        public Product getProduct() {
            return product;
        }

        public Double getPrice() {
            return price;
        }

        public Double getQuantity() {
            return quantity;
        }

        public Boolean isDiscounted() {
            return isDiscounted;
        }

        public SpecialOfferType getSpecialOfferType() {
            return specialOfferType;
        }

        public Double getArgument() {
            return argument;
        }
    }

}