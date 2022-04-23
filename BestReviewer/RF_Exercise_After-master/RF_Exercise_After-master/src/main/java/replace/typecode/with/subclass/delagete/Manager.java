package replace.typecode.with.subclass.delagete;

public class Manager extends EmployeeType {

    @Override
    protected int payAmount(Employee employee) {
        return employee.monthlySalary + employee.bonus;
    }
}
