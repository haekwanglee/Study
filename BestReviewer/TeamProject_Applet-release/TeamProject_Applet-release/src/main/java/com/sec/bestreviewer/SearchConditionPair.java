package com.sec.bestreviewer;

import com.sec.bestreviewer.database.field.EmployeeSchema;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class SearchConditionPair {

    private final List<String> options;
    private final List<String> params;

    SearchConditionPair(List<String> options, List<String> params) {
        this.options = options;
        this.params = params;
    }

    List<String> getOptions() {
        return options;
    }

    public List<String> getParams() {
        return params;
    }

    public boolean hasOptionSpecific(OptionSpecific option) {
        return option.hasOption(getOptions(), params.get(0));
    }

    public String getConditionTokenName() { //cl
        return params.get(0);
    }

    public String getConditionTokenValue() { //CL2
        return params.get(1);
    }

    public enum OptionSpecific {
        FIRST_OF_NAME((options, fieldParam) ->
                fieldParam.equals(EmployeeSchema.NAME.getName()) && options.contains("-f")),
        LAST_OF_NAME((options, fieldParam) ->
                fieldParam.equals(EmployeeSchema.NAME.getName()) && options.contains("-l")),

        MIDDLE_OF_PHONENUM((options, fieldParam) ->
                fieldParam.equals(EmployeeSchema.PHONE_NUMBER.getName()) && options.contains("-m")),
        LAST_OF_PHONENUM((options, fieldParam) ->
                fieldParam.equals(EmployeeSchema.PHONE_NUMBER.getName()) && options.contains("-l")),

        YEAR_OF_BIRTH((options, fieldParam) ->
                fieldParam.equals(EmployeeSchema.BIRTHDAY.getName()) && options.contains("-y")),
        MONTH_OF_BIRTH((options, fieldParam) ->
                fieldParam.equals(EmployeeSchema.BIRTHDAY.getName()) && options.contains("-m")),
        DAY_OF_BIRTH((options, fieldParam) ->
                fieldParam.equals(EmployeeSchema.BIRTHDAY.getName()) && options.contains("-d")),

        GREATER((options, fieldParam) -> options.contains("-g")),
        GREATER_EQUAL((options, fieldParam) -> options.contains("-ge")),
        SMALLER((options, fieldParam) -> options.contains("-s")),
        SMALLER_EQUAL((options, fieldParam) -> options.contains("-se"));

        private final BiFunction<List<String>, String, Boolean> checkOperator;

        OptionSpecific(BiFunction<List<String>, String, Boolean> checkOperator) {
            this.checkOperator = checkOperator;
        }

        public boolean hasOption(List<String> optionList, String fieldParam) {
            return checkOperator.apply(optionList, fieldParam);
        }
    }

    private static Map<OptionSpecific, String> operatorOptionToFieldName = new HashMap<>();

    static {
        operatorOptionToFieldName.put(SearchConditionPair.OptionSpecific.FIRST_OF_NAME,
                EmployeeSchema.NAME_FIRST.getName());
        operatorOptionToFieldName.put(SearchConditionPair.OptionSpecific.LAST_OF_NAME,
                EmployeeSchema.NAME_LAST.getName());
        operatorOptionToFieldName.put(SearchConditionPair.OptionSpecific.MIDDLE_OF_PHONENUM,
                EmployeeSchema.PHONE_NUMBER_MIDDLE.getName());
        operatorOptionToFieldName.put(SearchConditionPair.OptionSpecific.LAST_OF_PHONENUM,
                EmployeeSchema.PHONE_NUMBER_LAST.getName());
        operatorOptionToFieldName.put(SearchConditionPair.OptionSpecific.YEAR_OF_BIRTH,
                EmployeeSchema.BIRTH_DAY_YEAR.getName());
        operatorOptionToFieldName.put(SearchConditionPair.OptionSpecific.MONTH_OF_BIRTH,
                EmployeeSchema.BIRTH_DAY_MONTH.getName());
        operatorOptionToFieldName.put(SearchConditionPair.OptionSpecific.DAY_OF_BIRTH,
                EmployeeSchema.BIRTH_DAY_DAY.getName());
    }

    public String getConditionFieldName() {
        for (OptionSpecific optionKey : operatorOptionToFieldName.keySet()) {
            if (hasOptionSpecific(optionKey)) {
                return operatorOptionToFieldName.get(optionKey);
            }
        }
        return getConditionTokenName();
    }
}
