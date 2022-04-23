package replace.typecode.with.subclass.delegate;

public class Employee {
    private EmployeeType employeeType;
    public Employee(int arg) {
        setEmployeeType(arg);
    }

    public int monthlySalary;
    public int commission;
    public int bonus;
    public int payAmount() {
        return employeeType.payAmount(this);
    }

    public int getEmployeeType() {
        return employeeType.getEmployeeType();
    }

    public void setEmployeeType(int type) {
        this.employeeType = EmployeeType.create(type);
    }
}
