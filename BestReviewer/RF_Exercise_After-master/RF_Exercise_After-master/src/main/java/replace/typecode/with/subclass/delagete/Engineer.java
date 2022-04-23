package replace.typecode.with.subclass.delagete;

public class Engineer extends EmployeeType {

    @Override
    protected int payAmount(Employee employee) {
        return employee.monthlySalary;

    }
}
