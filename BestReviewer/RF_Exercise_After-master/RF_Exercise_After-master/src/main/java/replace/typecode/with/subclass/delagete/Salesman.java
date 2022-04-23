package replace.typecode.with.subclass.delagete;

public class Salesman extends EmployeeType {

    @Override
    protected int payAmount(Employee employee) {
        return employee.monthlySalary + employee.commission;

    }
}
