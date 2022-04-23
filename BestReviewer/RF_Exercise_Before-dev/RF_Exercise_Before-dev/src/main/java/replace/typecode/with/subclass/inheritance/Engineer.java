package replace.typecode.with.subclass.inheritance;

public class Engineer extends Employee {
    @Override
    public int payAmount() {
        return monthlySalary;
    }
}
