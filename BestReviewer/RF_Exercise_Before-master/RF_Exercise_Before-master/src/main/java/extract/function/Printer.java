package extract.function;

import java.util.Enumeration;
import java.util.Vector;

public class Printer {
    Vector<Order> orders = new Vector();

    String printOwing(String name) {
        StringBuffer result = new StringBuffer();

        Enumeration elements = orders.elements();
        double outstanding = 0.0;

        // print banner
        result.append("*****************************\n");
        result.append("****** Customer totals ******\n");
        result.append("*****************************\n");

        // calculate outstanding
        while (elements.hasMoreElements()) {
            Order each = (Order) elements.nextElement();
            outstanding += each.getAmount();
        }

        // print details
        result.append("name: " + name + "\n");
        result.append("amount: " + outstanding);

        return result.toString();
    }

}
