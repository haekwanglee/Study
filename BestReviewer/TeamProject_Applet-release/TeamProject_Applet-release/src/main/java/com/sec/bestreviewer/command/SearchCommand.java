package com.sec.bestreviewer.command;

import com.sec.bestreviewer.SearchConditionPair;
import com.sec.bestreviewer.TokenGroup;
import com.sec.bestreviewer.database.Row;
import com.sec.bestreviewer.database.Table;
import com.sec.bestreviewer.util.ResultStringFormatter;

import java.util.*;

public class SearchCommand implements Command {

    private static final int FIELD_NOT_FOUND_ERROR = -1;

    private final TokenGroup tokenGroup;

    public SearchCommand(TokenGroup tokenGroup) {
        this.tokenGroup = tokenGroup;
    }

    @Override
    public List<String> execute(Table employeeDatabase) {
        List<Row> employeeList;
        SearchConditionPair conditionParam1 = tokenGroup.getSearchConditionPair().get(0);

        if (tokenGroup.hasOption(TokenGroup.Option.AND)) {
            employeeList = intersection(getMatchedRows(employeeDatabase, conditionParam1),
                    getMatchedRows(employeeDatabase, tokenGroup.getSearchConditionPair().get(1)));
        } else if (tokenGroup.hasOption(TokenGroup.Option.OR)) {
            employeeList = union(getMatchedRows(employeeDatabase, conditionParam1),
                    getMatchedRows(employeeDatabase, tokenGroup.getSearchConditionPair().get(1)));
        } else {
            employeeList = getMatchedRows(employeeDatabase, conditionParam1);
        }

        return ResultStringFormatter.getEmployeeListToFormattedString(employeeList);
    }

    private List<Row> getMatchedRows(Table employeeDatabase, SearchConditionPair conditionPair) {
        String conditionFieldName = conditionPair.getConditionFieldName();
        int fieldIndex = employeeDatabase.getFieldIndexByName(conditionFieldName);
        if (fieldIndex == FIELD_NOT_FOUND_ERROR) {
            return Collections.emptyList();
        }

        String conditionFieldValue = conditionPair.getConditionTokenValue();
        if (conditionPair.hasOptionSpecific(SearchConditionPair.OptionSpecific.GREATER_EQUAL)) {
            return employeeDatabase.queryGreaterThanRows(fieldIndex, conditionFieldValue, true);
        } else if (conditionPair.hasOptionSpecific(SearchConditionPair.OptionSpecific.GREATER)) {
            return employeeDatabase.queryGreaterThanRows(fieldIndex, conditionFieldValue, false);
        } else if (conditionPair.hasOptionSpecific(SearchConditionPair.OptionSpecific.SMALLER_EQUAL)) {
            return employeeDatabase.queryLessThanRows(fieldIndex, conditionFieldValue, true);
        } else if (conditionPair.hasOptionSpecific(SearchConditionPair.OptionSpecific.SMALLER)) {
            return employeeDatabase.queryLessThanRows(fieldIndex, conditionFieldValue, false);
        }

        return employeeDatabase.queryFieldMatchedRows(fieldIndex, conditionFieldValue);
    }

    public List<Row> union(List<Row> list1, List<Row> list2) {
        Set<Row> set = new HashSet<>();

        set.addAll(list1);
        set.addAll(list2);

        return new ArrayList<>(set);
    }

    public List<Row> intersection(List<Row> list1, List<Row> list2) {
        ArrayList<Row> tempList1 = new ArrayList<>(list1);
        tempList1.retainAll(list2);
        return tempList1;
    }
}
