package replace.typecode.with.subclass.delagete;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    public static final int MONTHLY_SALARY = 2000;
    public static final int COMMISSION = 500 ;
    public static final int BONUS = 1000;

    @Test
    public void testEngineerSalary(){
        Employee e = createEmployee(EmployeeType.ENGINEER);
        assertEquals(2000, e.payAmount());
    }

    @Test
    public void testSalesmanSalary(){
        Employee e = createEmployee(EmployeeType.SALESMAN);
        assertEquals(2500, e.payAmount());
    }

    @Test
    public void testManagerSalary(){
        Employee e = createEmployee(EmployeeType.MANAGER);
        assertEquals(3000, e.payAmount());
    }

    @Test
    public void testWrongEmployee(){
        assertThrows(RuntimeException.class,()-> new Employee(100), "Incorrect Employee Code");
    }

    private Employee createEmployee(int type) {
        Employee e = new Employee(type);
        e.monthlySalary = MONTHLY_SALARY;
        e.commission = COMMISSION;
        e.bonus = BONUS;

        return e;
    }
}
