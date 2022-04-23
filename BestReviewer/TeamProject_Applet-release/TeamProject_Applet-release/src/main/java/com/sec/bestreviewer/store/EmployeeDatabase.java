package com.sec.bestreviewer.store;

import com.sec.bestreviewer.database.Row;
import com.sec.bestreviewer.database.Table;
import com.sec.bestreviewer.database.field.EmployeeSchema;
import com.sec.bestreviewer.database.field.FieldFactory;
import com.sec.bestreviewer.util.ExtendedRowDataCreator;

import java.util.Arrays;
import java.util.stream.Collectors;

public class EmployeeDatabase extends Table {


    public EmployeeDatabase() {
        super(
                Arrays.stream(EmployeeSchema.values())
                        .map(FieldFactory::create)
                        .collect(Collectors.toList()),
                EmployeeSchema.getPrimaryKeyIndex()
        );
    }

    @Override
    public boolean add(Row row) {
        return super.add(ExtendedRowDataCreator.create(row));
    }

    @Override
    public boolean update(Row row) {
        return super.update(ExtendedRowDataCreator.create(row));
    }

    @Override
    public int getFieldIndexByName(String fieldName) {
        return EmployeeSchema.getIndexByName(fieldName);
    }
}
