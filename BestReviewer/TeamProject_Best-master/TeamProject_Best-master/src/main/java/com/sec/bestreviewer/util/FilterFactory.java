package com.sec.bestreviewer.util;

import com.sec.bestreviewer.store.filter.AndFilter;
import com.sec.bestreviewer.store.filter.Filter;
import com.sec.bestreviewer.store.filter.OrFilter;
import com.sec.bestreviewer.store.filter.SingleFilter;

import java.util.List;

import static com.sec.bestreviewer.util.ParamsParser.PairIndex.FIRST_PAIR;
import static com.sec.bestreviewer.util.ParamsParser.PairIndex.SECOND_PAIR;

public class FilterFactory {

    public static Filter buildFilter(List<String> params, OptionParser optionParser) {
        if (optionParser.getAndOption().isAndOn()) {
            Pair<String, String> conditionPair1 = ParamsParser.parser(params, FIRST_PAIR);
            OptionParser optionParser1 = new OptionParser(optionParser.getAndOption().getFirstOptions(), conditionPair1.fieldName);

            Pair<String, String> conditionPair2 = ParamsParser.parser(params, SECOND_PAIR);
            OptionParser optionParser2 = new OptionParser(optionParser.getAndOption().getSecondOptions(), conditionPair2.fieldName);
            return new AndFilter(new SingleFilter(conditionPair1, optionParser1), new SingleFilter(conditionPair2, optionParser2));
        }

        if (optionParser.getOrOption().isOrOn()) {
            Pair<String, String> conditionPair1 = ParamsParser.parser(params, FIRST_PAIR);
            OptionParser optionParser1 = new OptionParser(optionParser.getOrOption().getFirstOptions(), conditionPair1.fieldName);

            Pair<String, String> conditionPair2 = ParamsParser.parser(params, SECOND_PAIR);
            OptionParser optionParser2 = new OptionParser(optionParser.getOrOption().getSecondOptions(), conditionPair2.fieldName);

            return new OrFilter(new SingleFilter(conditionPair1, optionParser1), new SingleFilter(conditionPair2, optionParser2));
        }

        Pair<String, String> conditionPair = ParamsParser.parser(params, FIRST_PAIR);
        return new SingleFilter(conditionPair, optionParser);
    }
}
