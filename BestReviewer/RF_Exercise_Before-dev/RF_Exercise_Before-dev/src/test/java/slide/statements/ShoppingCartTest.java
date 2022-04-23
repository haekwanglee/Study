package slide.statements;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class ShoppingCartTest {
    @Test
    public void testCheckout() {
        Plan plan = new Plan();
        plan.base = 1000;
        plan.discountFactor = 10;
        plan.discountThreshold = 6;

        Policy p = new Policy(plan);
        Order o = new Order();
        o.units = 10;
        o.isRepeat = true;

        Customer c = new Customer();
        c.addOrder(o);
        ShoppingCart cart = spy(new ShoppingCart(p, c));

        cart.checkout();
        double expected = 1000 - ((10-6) * 10 + 20);
        verify(cart, times(1)).chargeOrder(anyDouble());
        verify(cart).chargeOrder(expected);
    }
}