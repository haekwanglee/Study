package com.sec.bestreviewer.command.queryprocessor;

import com.sec.bestreviewer.command.querycondition.QueryCondition;
import com.sec.bestreviewer.store.Employee;

import java.util.List;

public class QueryProcessor {
    private final List<QueryCondition> queryConditions;
    private final String joinOperator;
    QueryPreprocessor queryPreprocessor = new QueryPreprocessor();
    ComparisonQueryProcessor comparisonQueryProcessor = new ComparisonQueryProcessor();

    public QueryProcessor(List<QueryCondition> queryConditions, String joinOperator) {
        this.queryConditions = queryConditions;
        this.joinOperator = joinOperator;
    }

    public boolean process(Employee employee) {
        boolean processResult;
        processResult = "AND".equals(joinOperator);
        for(QueryCondition queryCondition : queryConditions) {
            String processedValue = queryPreprocessor.process(queryCondition, employee);
            boolean queryResult = comparisonQueryProcessor.process(queryCondition, processedValue);

            if("AND".equals(joinOperator)) {
                processResult &= queryResult;
            }
            else if("OR".equals(joinOperator)) {
                processResult |= queryResult;
            }
            else {
                processResult = queryResult;
            }
        }
        return processResult;
    }
}
