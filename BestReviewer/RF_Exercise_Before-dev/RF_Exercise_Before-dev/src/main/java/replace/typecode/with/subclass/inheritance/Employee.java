package replace.typecode.with.subclass.inheritance;

public abstract  class Employee {
    public int monthlySalary;
    public int commission;
    public int bonus;

    public Employee() {
    }

    public abstract int payAmount();
}

