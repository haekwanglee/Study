package com.sec.bestreviewer.database;

import com.sec.bestreviewer.database.field.CareerLevelField;
import com.sec.bestreviewer.database.field.CertiField;
import com.sec.bestreviewer.database.field.Field;
import com.sec.bestreviewer.database.field.StringField;
import com.spun.util.Asserts;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class TableTest {

    private static final List<Field> FIELD_LIST = Arrays.asList(
            new StringField("test1", 0),
            new StringField("test2", 1),
            new StringField("test3", 2),
            new StringField("test4", 3),
            new CertiField("certi", 4),
            new CareerLevelField("cl", 5),
            new StringField("number", 6)
    );
    private static final Row[] TEST_ROWS = {
            new Row("data1", "data1-2", "data_match", "data1-4", "EX", "CL1", "1234"),
            new Row("data2", "data2-2", "data_match", "data2-4", "ADV", "CL2", "1235"),
            new Row("data3", "data3-2", "data_match", "data3-4", "ADV", "CL3", "1236"),
            new Row("data4", "data4-2", "data_match", "data4-4", "ADV", "CL4", "1237"),
    };

    private static final Row DATA_5 = new Row("data5", "data5-2", "data5-3", "data5-4");

    private Table table;

    @BeforeEach
    public void setup() {
        table = new Table(FIELD_LIST, 0);

        Arrays.stream(TEST_ROWS).forEach(table::add);
    }

    @Test
    public void testGetCount() {
        Assertions.assertEquals(TEST_ROWS.length, table.countRows());
    }

    @Test
    public void testGetRow() {
        Assertions.assertEquals(TEST_ROWS[0], table.querySingleRow(TEST_ROWS[0].getValue(0)));
        Assertions.assertEquals(TEST_ROWS[1], table.querySingleRow(TEST_ROWS[1].getValue(0)));
        Assertions.assertEquals(TEST_ROWS[2], table.querySingleRow(TEST_ROWS[2].getValue(0)));
        Assertions.assertEquals(TEST_ROWS[3], table.querySingleRow(TEST_ROWS[3].getValue(0)));
    }

    @Test
    public void testAdd() {
        Asserts.assertTrue("should be empty", table.querySingleRow(DATA_5.getValue(0)) == null);
        table.add(DATA_5);
        Asserts.assertNotNull("should not be null", table.querySingleRow(DATA_5.getValue(0)));
    }

    @Test
    public void testRemove() {
        table.add(DATA_5);
        Asserts.assertNotNull("should not be null", table.querySingleRow(DATA_5.getValue(0)));
        table.remove(DATA_5);
        Asserts.assertTrue("should be empty", table.querySingleRow(DATA_5.getValue(0)) == null);
    }

    @Test
    public void testSingleQuery() {
        Assertions.assertEquals(TEST_ROWS[0], table.querySingleRow(TEST_ROWS[0].getValue(0)));
        Assertions.assertEquals(TEST_ROWS[1], table.querySingleRow(TEST_ROWS[1].getValue(0)));
        Assertions.assertEquals(TEST_ROWS[2], table.querySingleRow(TEST_ROWS[2].getValue(0)));
        Assertions.assertEquals(TEST_ROWS[3], table.querySingleRow(TEST_ROWS[3].getValue(0)));
    }

    @Test
    public void testMatchQuery() {
        Assertions.assertEquals(List.of(TEST_ROWS[0]), table.queryFieldMatchedRows(3, TEST_ROWS[0].getValue(3)));
        Assertions.assertEquals(List.of(TEST_ROWS[1]), table.queryFieldMatchedRows(3, TEST_ROWS[1].getValue(3)));
        Assertions.assertEquals(List.of(TEST_ROWS[2]), table.queryFieldMatchedRows(3, TEST_ROWS[2].getValue(3)));
        Assertions.assertEquals(List.of(TEST_ROWS[3]), table.queryFieldMatchedRows(3, TEST_ROWS[3].getValue(3)));

        final List<Row> matchedRows = table.queryFieldMatchedRows(2, TEST_ROWS[0].getValue(2));
        Assertions.assertEquals(new HashSet<>(Arrays.asList(TEST_ROWS)), new HashSet<>(matchedRows));
    }

    @Test
    void update() {
        final Row row = new Row("data1", "data5-2", "data5-3", "data5-4");
        table.update(row);
        Assertions.assertEquals(row, table.querySingleRow("data1"));
    }

    @Test
    void checkFieldIndexByName() {
        Assertions.assertEquals(0, table.getFieldIndexByName(FIELD_LIST.get(0).getName()));
        Assertions.assertEquals(1, table.getFieldIndexByName(FIELD_LIST.get(1).getName()));
        Assertions.assertEquals(2, table.getFieldIndexByName(FIELD_LIST.get(2).getName()));
        Assertions.assertEquals(3, table.getFieldIndexByName(FIELD_LIST.get(3).getName()));
    }

    @Test
    void checkFieldList() {
        Assertions.assertEquals(FIELD_LIST, table.getFieldList());
    }

    @Test
    void checkRowList() {
        Assertions.assertEquals(new HashSet<>(Arrays.asList(TEST_ROWS)), new HashSet<>(new LinkedList<>(table.getRowList())));
    }

    @ParameterizedTest
    @CsvSource({
            "cl,CL3,2",
            "certi,ADV,0",
            "number,1235,1"
    })
    @DisplayName("-s 에 대한 테스트")
    void testQueryLessThanRows(String fieldName, String standard, int expected) {
        final int clIndex = table.getFieldIndexByName(fieldName);
        final List<Row> lessThan = table.queryLessThanRows(clIndex, standard, false);
        Assertions.assertEquals(expected, lessThan.size());
    }

    @ParameterizedTest
    @CsvSource({
            "cl,CL3,3",
            "certi,ADV,3",
            "number,1235,2"
    })
    @DisplayName("-se에 대한 테스트")
    void testQueryLessThanEqualsRows(String fieldName, String standard, int expected) {
        final int clIndex = table.getFieldIndexByName(fieldName);
        final List<Row> lessThanEquals = table.queryLessThanRows(clIndex, standard, true);
        Assertions.assertEquals(expected, lessThanEquals.size());
    }

    @ParameterizedTest
    @CsvSource({
            "cl,CL3,1",
            "certi,PRO,1",
            "certi,EX,0",
            "number,1236,1"
    })
    @DisplayName("-g 대한 테스트")
    void testQueryGreaterThanRows(String fieldName, String standard, int expected) {
        final int clIndex = table.getFieldIndexByName(fieldName);
        final List<Row> greaterThan = table.queryGreaterThanRows(clIndex, standard, false);
        Assertions.assertEquals(expected, greaterThan.size());
    }

    @ParameterizedTest
    @CsvSource({
            "cl,CL3,2",
            "certi,PRO,1",
            "certi,EX,1",
            "number,1236,2"
    })
    @DisplayName("-ge 대한 테스트")
    void testQueryGreaterThanEqualsRows(String fieldName, String standard, int expected) {
        final int clIndex = table.getFieldIndexByName(fieldName);
        final List<Row> greaterThanEquals = table.queryGreaterThanRows(clIndex, standard, true);
        Assertions.assertEquals(expected, greaterThanEquals.size());
    }
}
