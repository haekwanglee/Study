package replace.typecode.with.subclass.delegate;

public class Enginner extends EmployeeType {
    @Override
    int payAmount(Employee employee) {
        return employee.monthlySalary;
    }

    @Override
    int getEmployeeType() {
        return ENGINEER;
    }
}
