package extract.function;

import java.util.Enumeration;
import java.util.Vector;

public class Printer {
    Vector<Order> orders = new Vector();

    String printOwing(String name) {
        StringBuffer result = new StringBuffer();

        result.append(printBanner());
        result.append(printDetails(name));

        return result.toString();
    }

    private double getOutstanding() {
        Enumeration elements = orders.elements();
        double outstanding = 0.0;

        // calculate outstanding
        while (elements.hasMoreElements()) {
            Order each = (Order) elements.nextElement();
            outstanding += each.getAmount();
        }
        return outstanding;
    }

    private String printDetails(String name) {
        // print details
        return "name: " + name + "\n" +
                "amount: " + getOutstanding();
    }

    private String printBanner() {
        // print banner
        return "*****************************\n" +
                "****** Customer totals ******\n" +
                "*****************************\n";
    }

}
