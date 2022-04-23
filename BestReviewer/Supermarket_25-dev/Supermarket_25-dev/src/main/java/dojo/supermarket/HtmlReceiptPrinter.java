package dojo.supermarket;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Receipt;
import dojo.supermarket.model.ReceiptItem;

public class HtmlReceiptPrinter extends ReceiptPrinter {

    public HtmlReceiptPrinter() {
        this(40);
    }

    public HtmlReceiptPrinter(int columns) {
        super(columns);
    }

    @Override
    String presentReceiptItem(ReceiptItem item) {
        String totalPricePresentation = presentPrice(item.getTotalPrice());
        String name = item.getProduct().getName();

        String line = formatLineWithWhitespace(name, totalPricePresentation);

        if (item.getQuantity() != 1) {
            line += "<tr><td colspan=2>" + presentPrice(item.getPrice()) + " * " + presentQuantity(item) + "</td></tr>\n";
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
        String name = "<b>Total: ";
        String value = presentPrice(receipt.getTotalPrice());
        return formatLineWithWhitespace(name, value) + "<tr><td colspan=2>&nbsp;</td></tr>\n";
    }

    @Override
    String presentMargin() {
        return "";
    }

    @Override
    String presentHead() {
        return"<table>\n";
    }

    @Override
    String presentEnding() {
        return"</table>";
    }

    @Override
    String formatLineWithWhitespace(String name, String value) {
        StringBuilder line = new StringBuilder();

        line.append("<tr><td>");
        line.append(name);
        line.append("</td><td>");
        line.append(value);
        line.append("</td></tr>\n");
        return line.toString();
    }
}