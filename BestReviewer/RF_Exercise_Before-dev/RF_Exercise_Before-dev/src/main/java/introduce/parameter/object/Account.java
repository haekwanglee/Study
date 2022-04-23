package introduce.parameter.object;

import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

class Account {
    // ...
    private Vector transactions = new Vector();

    void addTransaction(introduce.parameter.object.Transaction t) {
        transactions.add(t);
    }
    public double getFlowBetween(DateRange dateRange) {
        Date start = dateRange.getStartDate();
        Date end = dateRange.getEndDate();

        double result = 0;
        Enumeration e = transactions.elements();
        while (e.hasMoreElements()) {
            introduce.parameter.object.Transaction each = (introduce.parameter.object.Transaction) e.nextElement();
            if (each.getDate().compareTo(start) >= 0 && each.getDate().compareTo(end) <= 0) {
                result += each.getValue();
            }
        }
        return result;
    }
}
