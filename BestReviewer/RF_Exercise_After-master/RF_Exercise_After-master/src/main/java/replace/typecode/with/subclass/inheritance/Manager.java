package replace.typecode.with.subclass.inheritance;

public class Manager extends Employee {
    @Override
    public int payAmount() {
        return monthlySalary + bonus;
    }
}
