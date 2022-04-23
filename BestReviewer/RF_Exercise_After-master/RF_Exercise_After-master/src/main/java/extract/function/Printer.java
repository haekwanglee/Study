package extract.function;

import java.util.Enumeration;
import java.util.Vector;

public class Printer {
    Vector<Order> orders = new Vector();

    public String printOwing(String name){
        StringBuffer result = new StringBuffer();

        printBanner(result);
        double outstanding = caluatingOutstanding();
        printDetails(result, name, outstanding);

        return result.toString();
    }

    protected void printDetails(StringBuffer out, String name, double outstanding) {
        out.append("name: " + name + "\n");
        out.append("amount: " + outstanding);
    }

    protected double caluatingOutstanding() {
        Enumeration elements = orders.elements();
        double outstanding = 0.0;

        while(elements.hasMoreElements()){
            Order each = (Order) elements.nextElement();
            outstanding += each.getAmount();
        }
        return outstanding;
    }

    void printBanner(StringBuffer out) {
        out.append("*****************************\n");
        out.append("****** Customer totals ******\n");
        out.append("*****************************\n");
    }

}
