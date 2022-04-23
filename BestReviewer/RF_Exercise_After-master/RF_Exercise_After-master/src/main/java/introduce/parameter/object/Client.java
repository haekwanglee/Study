package introduce.parameter.object;

import java.util.Date;

public class Client {
    // Somewhere in client code…
    introduce.parameter.object.Account account = new introduce.parameter.object.Account();

    void drawFlowChartBetween(Date startDate, Date endDate) {
        double flow = account.getFlowBetween(startDate, endDate);
        // ...
    }
}
