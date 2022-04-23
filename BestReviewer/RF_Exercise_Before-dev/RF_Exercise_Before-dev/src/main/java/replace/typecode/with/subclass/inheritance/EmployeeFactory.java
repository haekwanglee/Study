package replace.typecode.with.subclass.inheritance;

public class EmployeeFactory {

    public static Employee createEmployee(int type) {
        switch (type) {
            case EmployeeType.ENGINEER:
                return new Engineer();
            case EmployeeType.SALESMAN:
                return new Salesman();
            case EmployeeType.MANAGER:
                return new Manager();
            default:
                throw new RuntimeException("Incorrect Employee Code");
        }
    }
}

