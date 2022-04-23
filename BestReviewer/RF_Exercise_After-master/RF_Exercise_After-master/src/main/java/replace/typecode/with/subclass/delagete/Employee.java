package replace.typecode.with.subclass.delagete;

public class Employee {

    private EmployeeType type;

    public Employee(int  arg){
        setType(arg);
    }

    public int monthlySalary;
    public int commission;
    public int bonus;

    public int payAmount(){
        return type.payAmount(this);
    }

    public void setType(int type) {
        this.type = EmployeeType.create(type);
    }
}
