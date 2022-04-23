package introduce.parameter.object;

public class Client {
    // Somewhere in client code…
    introduce.parameter.object.Account account = new introduce.parameter.object.Account();

    void drawFlowChartBetween(DateRange dateRange) {
        double flow = account.getFlowBetween(dateRange);
        // ...
    }
}
