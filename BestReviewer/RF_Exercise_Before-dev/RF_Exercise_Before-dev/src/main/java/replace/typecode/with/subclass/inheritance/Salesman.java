package replace.typecode.with.subclass.inheritance;

public class Salesman extends Employee {
    @Override
    public int payAmount() {
        return monthlySalary + commission;
    }
}
