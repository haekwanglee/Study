package slide.statements;

import java.util.ArrayDeque;

public class Customer {
    private ArrayDeque<slide.statements.Order> pendingOrders = new ArrayDeque<>();

    void addOrder(slide.statements.Order o) {
        pendingOrders.push(o);
    }

    public slide.statements.Order retrievePeekOrder() {
        return pendingOrders.pop();
    }
}
