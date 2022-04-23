package replace.typecode.with.subclass.delegate;

public class Manager extends EmployeeType {
    @Override
    int payAmount(Employee employee) {
        return employee.monthlySalary + employee.bonus;
    }

    @Override
    int getEmployeeType() {
        return MANAGER;
    }
}
