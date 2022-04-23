package introduce.specialcase;

public class NullPaymentHistory extends PaymentHistory {
    @Override
    public int getWeeksDelinquentInLastYear() {
        return 0;
    }
}
