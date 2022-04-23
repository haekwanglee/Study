package replace.typecode.with.subclass.delegate;

public class Salesman extends EmployeeType {
    @Override
    int payAmount(Employee employee) {
        return employee.monthlySalary + employee.commission;
    }

    @Override
    int getEmployeeType() {
        return SALESMAN;
    }
}
