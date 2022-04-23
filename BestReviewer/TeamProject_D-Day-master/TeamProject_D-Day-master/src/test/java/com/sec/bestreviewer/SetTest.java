package com.sec.bestreviewer;

import com.sec.bestreviewer.command.IntersectionSet;
import com.sec.bestreviewer.command.Set;
import com.sec.bestreviewer.command.SetFactory;
import com.sec.bestreviewer.command.UnionSet;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.util.arguments.CommandOptionSeparator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SetTest {

    @Test
    public void testOrOperator() {
        List<Employee> firstResult = new ArrayList<>();
        firstResult.add(new Employee("02234542", "KIM SAMSUNG1", "CL1","010-1234-5678", "20011212", "ADV"));
        firstResult.add(new Employee("06234561", "KIM SAMSUNG2", "CL1","010-1234-5678", "20011212", "ADV"));
        firstResult.add(new Employee("12234571", "KIM SAMSUNG3", "CL1","010-1234-5678", "20011212", "ADV"));

        List<Employee> secondResult = new ArrayList<>();
        secondResult.add(new Employee("12234571", "KIM SAMSUNG3", "CL1","010-1234-5678", "20011212", "ADV"));
        secondResult.add(new Employee("21012345", "KIM SAMSUNG4", "CL1","010-1234-5678", "20011212", "ADV"));

        Set orOperator = SetFactory.createSet(CommandOptionSeparator.OR_COMMAND);
        List<Employee> actualResult = orOperator.execute(firstResult, secondResult);

        firstResult.add(new Employee("21012345", "KIM SAMSUNG4", "CL1","010-1234-5678", "20011212", "ADV"));
        assertArrayEquals(firstResult.toArray(), actualResult.toArray());
    }

    @Test
    public void testAndOperator() {
        List<Employee> firstResult = new ArrayList<>();
        firstResult.add(new Employee("02234542", "KIM SAMSUNG1", "CL1","010-1234-5678", "20011212", "ADV"));
        firstResult.add(new Employee("06234561", "KIM SAMSUNG2", "CL1","010-1234-5678", "20011212", "ADV"));
        firstResult.add(new Employee("12234571", "KIM SAMSUNG3", "CL1","010-1234-5678", "20011212", "ADV"));

        List<Employee> secondResult = new ArrayList<>();
        secondResult.add(new Employee("12234571", "KIM SAMSUNG3", "CL1","010-1234-5678", "20011212", "ADV"));
        secondResult.add(new Employee("21012345", "KIM SAMSUNG4", "CL1","010-1234-5678", "20011212", "ADV"));

        Set andOperator = SetFactory.createSet(CommandOptionSeparator.AND_COMMAND);
        List<Employee> actualResult = andOperator.execute(firstResult, secondResult);

        secondResult.remove(1);
        assertArrayEquals(secondResult.toArray(), actualResult.toArray());
    }

}
