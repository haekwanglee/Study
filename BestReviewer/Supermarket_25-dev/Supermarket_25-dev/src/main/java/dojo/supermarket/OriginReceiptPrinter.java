package dojo.supermarket;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Receipt;
import dojo.supermarket.model.ReceiptItem;

public class OriginReceiptPrinter extends ReceiptPrinter{

    public OriginReceiptPrinter() {
        this(40);
    }

    public OriginReceiptPrinter(int columns) {
        super(columns);
    }

    @Override
    String presentReceiptItem(ReceiptItem item) {
        String totalPricePresentation = presentPrice(item.getTotalPrice());
        String name = item.getProduct().getName();

        String line = formatLineWithWhitespace(name, totalPricePresentation);

        if (item.getQuantity() != 1) {
            line += "  " + presentPrice(item.getPrice()) + " * " + presentQuantity(item) + "\n";
        }
        return line;
    }

    @Override
    String presentDiscount(Discount discount) {
        String name = discount.getDescription() + "(" + discount.getProduct().getName() + ")";
        String value = "-" + presentPrice(discount.getDiscountAmount());

        return formatLineWithWhitespace(name, value);
    }

    @Override
    String presentTotal(Receipt receipt) {
        String name = "Total: ";
        String value = presentPrice(receipt.getTotalPrice());
        return formatLineWithWhitespace(name, value);
    }

    @Override
    String presentMargin() {
        return "\n";
    }

    @Override
    String presentHead() {
        return "";
    }

    @Override
    String presentEnding() {
        return "";
    }

    @Override
    String formatLineWithWhitespace(String name, String value) {
        StringBuilder line = new StringBuilder();
        line.append(name);
        int whitespaceSize = this.columns - name.length() - value.length();
        for (int i = 0; i < whitespaceSize; i++) {
            line.append(" ");
        }
        line.append(value);
        line.append('\n');
        return line.toString();
    }
}
