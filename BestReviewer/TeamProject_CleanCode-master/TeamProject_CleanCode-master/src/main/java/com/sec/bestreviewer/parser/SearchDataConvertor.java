package com.sec.bestreviewer.parser;

import com.sec.bestreviewer.data.InqualitySignOption;
import com.sec.bestreviewer.data.SearchData;
import com.sec.bestreviewer.data.SearchOption;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SearchDataConvertor {

    public static final String EMPLOYEE_NUM = "employeeNum";
    public static final String NAME = "name";
    public static final String CL = "cl";
    public static final String PHONE_NUM = "phoneNum";
    public static final String BIRTHDAY = "birthday";
    public static final String CERTI = "certi";

    public static List<SearchData> commandToSearchData(List<String> options, List<String> params) {
        List<SearchData> searchDataList = new ArrayList<SearchData>();

        addOneSearchData(options.subList(1, 3), params.subList(0, 2), searchDataList);
        if (isAndOr(options)) {
            addOneSearchData(options.subList(4, options.size()), params.subList(2, params.size()), searchDataList);
        }

        return searchDataList;
    }

    private static boolean isAndOr(List<String> options) {
        final List<String> compoundOptions = Collections.unmodifiableList(Arrays.asList("-a", "-o"));
        return (options.size() >= 4 && compoundOptions.contains(options.get(3)));
    }

    protected static void addOneSearchData(List<String> options, List<String> params, List<SearchData> searchDataList) {

        SearchOption searchOption = getSearchOption(options.get(0), params.get(0));
        
        if (isOptioningSearch(searchOption)==false)
            return;
        
        InqualitySignOption inqualitySignOption = getInqualitySignOption(options.get(1));
        String keyColumnName = params.get(0);
        String keyValue = params.get(1);

        addSearchDataToList(searchDataList, searchOption, inqualitySignOption, keyColumnName, keyValue);
    }

    private static void addSearchDataToList(List<SearchData> searchDataList, SearchOption searchOption, InqualitySignOption inqualitySignOption, String keyColumnName, String keyValue) {
        searchDataList.add(new SearchData(searchOption, inqualitySignOption, keyColumnName, keyValue));
    }

    private static boolean isOptioningSearch(SearchOption searchOption) {
        return searchOption != SearchOption.NONE;
    }

    private static InqualitySignOption getInqualitySignOption(String option) {
        switch (option) {
            case "-g":
                return InqualitySignOption.GREATER_THAN;
            case "-s":
                return InqualitySignOption.SMALLER_THAN;
            case "-ge":
                return InqualitySignOption.GREATER_THAN_OR_EQUAL_TO;
            case "-se":
                return InqualitySignOption.SMALLER_THAN_OR_EQUAL_TO;
            default:
                return InqualitySignOption.NONE;
        }
    }

    private static SearchOption getSearchOption(String option, String firstParam) {
        switch (firstParam) {
            case EMPLOYEE_NUM:
                return getEmployeeNumSearchOption();
            case NAME:
                return getNameSearchOption(option);
            case CL:
                return getClSearchOption();
            case PHONE_NUM:
                return getPhoneNumSearchOption(option);
            case BIRTHDAY:
                return getBirthdaySearchOption(option);
            case CERTI:
                return getCertiSearchOption();
            default:
                return getNoneSearchOption();
        }
    }


    private static SearchOption getClSearchOption() {
        return SearchOption.CL;
    }

    private static SearchOption getEmployeeNumSearchOption() {
        return SearchOption.EMPLOYEE_NUMBER;
    }

    private static SearchOption getNameSearchOption(String option) {
        switch (option) {
            case "-f":
                return SearchOption.FIRST_NAME;
            case "-l":
                return SearchOption.LAST_NAME;
            default:
                return SearchOption.FULL_NAME;
        }
    }

    private static SearchOption getPhoneNumSearchOption(String option) {
        switch (option) {
            case "-m":
                return SearchOption.MIDDLE_NUMBER;
            case "-l":
                return SearchOption.LAST_NUMBER;
            default:
                return SearchOption.FULL_NUMBER;
        }
    }

    private static SearchOption getBirthdaySearchOption(String option) {
        switch (option) {
            case "-y":
                return SearchOption.BIRTHDAY_YEAR;
            case "-m":
                return SearchOption.BIRTHDAY_MONTH;
            case "-d":
                return SearchOption.BIRTHDAY_DAY;
            default:
                return SearchOption.FULL_BIRTHDAY;
        }
    }

    private static SearchOption getCertiSearchOption() {
        return SearchOption.CERTI;
    }

    private static SearchOption getNoneSearchOption() {
        return SearchOption.NONE;
    }
}
