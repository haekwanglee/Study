package com.sec.bestreviewer.database;

import com.sec.bestreviewer.database.field.*;
import com.sec.bestreviewer.store.EmployeeDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

public class EmployeeDatabaseTest {

    private Table table;

    @BeforeEach
    public void setup() {
        table = new EmployeeDatabase();
    }

    @Test
    void checkConstructor() {
        Assertions.assertEquals(13, table.getFieldList().size());
        Assertions.assertEquals(0, table.getPrimaryKeyIndex());
    }

    @Test
    void checkUnsupportedFieldType() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            FieldFactory.create("random", 0, FieldType.UNKNOWN);
        });
    }

    @Test
    void checkFieldType() {
        Assertions.assertTrue(table.getFieldList().get(table.getFieldIndexByName("cl")) instanceof CareerLevelField);
        Assertions.assertTrue(table.getFieldList().get(table.getFieldIndexByName("certi")) instanceof CertiField);
        Assertions.assertTrue(table.getFieldList().get(table.getFieldIndexByName("name")) instanceof StringField);
    }

    @ParameterizedTest
    @MethodSource("getEnumStream")
    void checkFieldIndexByName(EmployeeSchema schema) {
        Assertions.assertEquals(schema.getIndex(), table.getFieldIndexByName(schema.getName()));
    }

    private static Stream<EmployeeSchema> getEnumStream() {
        return Arrays.stream(EmployeeSchema.values());
    }

    @Test
    void checkExtendedRow() {
        final Row row = new Row("91385769","KEUNYOUNG PARK","CL2","010-9777-6055","19980906");
        table.update(row);
        Assertions.assertEquals(table.querySingleRow("91385769").toString(),
                "91385769,KEUNYOUNG PARK,CL2,010-9777-6055,19980906,KEUNYOUNG,PARK,9777,6055,1998,09,06");
    }
}
