package replace.typecode.with.subclass.delegate;

abstract public class EmployeeType {
    static final int ENGINEER = 0;
    static final int SALESMAN = 1;
    static final int MANAGER = 2;

    public static EmployeeType create(int type) {
        switch (type) {
            case ENGINEER:
                return new Enginner();
            case SALESMAN:
                return new Salesman();
            case MANAGER:
                return new Manager();
            default:
                throw new RuntimeException("Incorrect Employee Code");
        }
    }

    abstract  int payAmount(Employee employee);

    abstract int getEmployeeType();
}
