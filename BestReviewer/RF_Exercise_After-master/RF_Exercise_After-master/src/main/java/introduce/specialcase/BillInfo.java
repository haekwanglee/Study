package introduce.specialcase;

public class BillInfo {
    String name;
    introduce.specialcase.BillingPlan plan;
    int weeksDelinquent;

    public BillInfo(String name, introduce.specialcase.BillingPlan plan, int weeksDelinquent) {
        this.name = name;
        this.plan = plan;
        this.weeksDelinquent = weeksDelinquent;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof introduce.specialcase.BillInfo) {
            introduce.specialcase.BillInfo other = (introduce.specialcase.BillInfo) obj;
            return name.equals(other.name) && plan.equals(other.plan)
                    && weeksDelinquent == other.weeksDelinquent;
        }
        return false;
    }
}
