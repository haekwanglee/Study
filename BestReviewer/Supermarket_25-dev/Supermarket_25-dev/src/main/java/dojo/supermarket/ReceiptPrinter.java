package dojo.supermarket;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.ProductUnit;
import dojo.supermarket.model.Receipt;
import dojo.supermarket.model.ReceiptItem;

import java.util.Locale;

public abstract class ReceiptPrinter {

    final int columns;
    public ReceiptPrinter(int columns) {
        this.columns = columns;
    }

    public String printReceipt(Receipt receipt) {
        StringBuilder result = new StringBuilder();

        result.append(presentHead());

        for (ReceiptItem item : receipt.getItems()) {
            String receiptItem = presentReceiptItem(item);
            result.append(receiptItem);
        }

        for (Discount discount : receipt.getDiscounts()) {
            String discountPresentation = presentDiscount(discount);
            result.append(discountPresentation);
        }

        result.append(presentMargin());

        result.append(presentTotal(receipt));

        result.append(presentEnding());

        return result.toString();
    }

    abstract String presentReceiptItem(ReceiptItem item);
    abstract String presentDiscount(Discount discount);
    abstract String presentTotal(Receipt receipt);

    abstract String presentMargin();
    abstract String presentHead();
    abstract String presentEnding();
    abstract String formatLineWithWhitespace(String name, String value);

    protected static String presentPrice(double price) {
        return String.format(Locale.UK, "%.2f", price);
    }

    protected static String presentQuantity(ReceiptItem item) {
        return ProductUnit.Each.equals(item.getProduct().getUnit())
                ? String.format("%x", (int)item.getQuantity())
                : String.format(Locale.UK, "%.3f", item.getQuantity());
    }
}
