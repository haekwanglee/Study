package com.sec.bestreviewer.store;

import com.sec.bestreviewer.database.Row;
import com.sec.bestreviewer.util.ExtendedRowDataCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDatabaseTest {

    @Test
    void addAdditionalRowDataTest() {
        final EmployeeDatabase employeeDatabase = new EmployeeDatabase();
        final Row originalRow = new Row(
                "14003533",
                "Yun Minhyeok",
                "CL3",
                "010-2775-9828",
                "19891121",
                "PRO"
        );

        final Row updatedRow = new Row(
                "14003533",
                "Yun Minhyeok",
                "CL3",
                "010-2775-9828",
                "19891121",
                "PRO",
                "Yun",
                "Minhyeok",
                "2775",
                "9828",
                "1989",
                "11",
                "21"
        );
        final Row testRow = ExtendedRowDataCreator.create(originalRow);
        Assertions.assertEquals(updatedRow, testRow);
    }
}