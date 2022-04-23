package com.sec.bestreviewer.command;


import com.sec.bestreviewer.TokenGroup;
import com.sec.bestreviewer.database.Row;
import com.sec.bestreviewer.database.Table;

import java.util.Collections;
import java.util.List;

public class AddCommand implements Command {

    private static final Row employee = new Row();

    public AddCommand(TokenGroup tokenGroup) {
        final List<String> params = tokenGroup.getTargetValuesForAdd();
        employee.clear();
        employee.addAll(params);
    }

    @Override
    public List<String> execute(Table employeeDatabase) {
        employeeDatabase.add(employee);
        return Collections.emptyList();
    }
}