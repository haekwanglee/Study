package replace.typecode.with.subclass.delegate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    public static final int MONTHLY_SALARY = 2000;
    public static final int COMMISSION = 500;
    public static final int BONUS = 1000;

    @Test
    public void testEngineerSalary() {
        Employee e = createEmployee(Employee.ENGINEER);
        assertEquals(2000, e.payAmount());
    }

    @Test
    public void testSalesmanSalary() {
        Employee e = createEmployee(Employee.SALESMAN);
        assertEquals(2500, e.payAmount());
    }

    @Test
    public void testManagerSalary() {
        Employee e = createEmployee(Employee.MANAGER);
        assertEquals(3000, e.payAmount());
    }

    @Test
    public void testWrongEmployeeType() {
        Employee e = createEmployee(100);
        assertThrows(RuntimeException.class, () -> e.payAmount(), "Incorrect Employee Code");
    }

    private Employee createEmployee(int type) {
        Employee e = new Employee(type);
        e.monthlySalary = MONTHLY_SALARY;
        e.commission = COMMISSION;
        e.bonus = BONUS;
        return e;
    }
}