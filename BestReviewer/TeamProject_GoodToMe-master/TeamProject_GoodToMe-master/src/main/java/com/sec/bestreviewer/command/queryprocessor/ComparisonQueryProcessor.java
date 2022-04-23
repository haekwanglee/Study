package com.sec.bestreviewer.command.queryprocessor;

import com.sec.bestreviewer.command.querycondition.QueryCondition;

public class ComparisonQueryProcessor {

    public static final String COMPARE_OPTION_GREATER_THAN = "-g";
    public static final String COMPARE_OPTION_GREATER_THAN_EQUALS = "-ge";
    public static final String COMPARE_OPTION_SMALLER_THAN = "-s";
    public static final String COMPARE_OPTION_SMALLER_THAN_EQUALS = "-se";

    public boolean process(QueryCondition queryCondition, String processedValue) {
        String compareOption = queryCondition.getOption3();
        String queryValue = queryCondition.getValue();
        String column = queryCondition.getColumn();

        QueryComparator comparator = getComparator(column);

        switch (compareOption) {
            case COMPARE_OPTION_GREATER_THAN:
                return comparator.greaterThan(processedValue, queryValue);
            case COMPARE_OPTION_GREATER_THAN_EQUALS:
                return comparator.greaterThanEquals(processedValue, queryValue);
            case COMPARE_OPTION_SMALLER_THAN:
                return comparator.smallerThan(processedValue, queryValue);
            case COMPARE_OPTION_SMALLER_THAN_EQUALS:
                return comparator.smallerThanEquals(processedValue, queryValue);
            default:
                return comparator.equals(processedValue, queryValue);
        }
    }

    private QueryComparator getComparator(String column) {
        switch (column) {
            case "employeeNum":
                return new EmployeeNumComparator();
            case "certi":
                return new CertiComparator();
            default:
                return new StringQueryComparator();
        }
    }

}
