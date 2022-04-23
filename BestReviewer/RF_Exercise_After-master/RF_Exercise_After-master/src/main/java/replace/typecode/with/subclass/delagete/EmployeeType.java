package replace.typecode.with.subclass.delagete;

abstract public class EmployeeType {
    //...
    static final int ENGINEER = 0;
    static final int SALESMAN = 1;
    static final int MANAGER = 2;

    public static EmployeeType create(int type) {
        switch(type){
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

    protected abstract int payAmount(Employee employee);

}
