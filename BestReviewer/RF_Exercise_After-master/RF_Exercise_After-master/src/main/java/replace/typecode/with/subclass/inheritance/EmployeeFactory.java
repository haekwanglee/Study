package replace.typecode.with.subclass.inheritance;

public class EmployeeFactory {
    // ...
    static final int ENGINEER = 0;
    static final int SALESMAN = 1;
    static final int MANAGER = 2;

    public static Employee createEmployee(int type) {
        switch (type) {
            case ENGINEER:
                return new Engineer();
            case SALESMAN:
                return new Salesman();
            case MANAGER:
                return new Manager();
            default:
                throw new RuntimeException("Incorrect Employee Code");
        }
    }
}
