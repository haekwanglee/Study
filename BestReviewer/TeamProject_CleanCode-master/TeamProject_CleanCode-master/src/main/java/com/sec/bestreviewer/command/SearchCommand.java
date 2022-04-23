package com.sec.bestreviewer.command;

import com.sec.bestreviewer.CommandFactory;
import com.sec.bestreviewer.command.SearchFilterMaker.SearchPredicate;
import com.sec.bestreviewer.data.*;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.util.ResultStringFormatter;

import java.util.List;
import java.util.function.Predicate;

import static com.sec.bestreviewer.CommandExecutor.MAX_RESULT_NUMBER;

public class SearchCommand extends Command {
    private final SearchFilterFactory factory;

    public SearchCommand(CommandData commandData) {
        super(commandData);
        factory = new SearchFilterFactory();
    }

    @Override
    public List<String> execute(EmployeeStore employeeStore) {
        final List<Employee> employeeList = getSearchedEmployees(employeeStore);
        if (PrintOption.PRINT.equals(commandData.getPrintOption())) {
            return ResultStringFormatter.getEmployeeListToFormattedString(
                    employeeList, CommandFactory.CMD_SCH, MAX_RESULT_NUMBER);
        }
        return ResultStringFormatter.getEmployeeListToFormattedString(employeeList, CommandFactory.CMD_SCH);
    }

    List<Employee> getSearchedEmployees(EmployeeStore employeeStore) {
        return employeeStore.search(createFilter());
    }

    SearchPredicate createFilter(SearchOption searchOption,
                                 InqualitySignOption inqualitySignOption,
                                 String value) {
        return factory.create(searchOption).createFilter(searchOption, inqualitySignOption, value);
    }

    Predicate<Employee> createFilter() {
        List<SearchData> searchData = commandData.getSearchDataList();
        int size = searchData.size();
        AndOrOption option = commandData.getAndOrOption();

        if (size == 1) {
            return createFilterAt(searchData, 0);
        } else if (size == 2) {
            return createComplexFilter(searchData, option);
        }

        throw new IllegalArgumentException("not supported search size : " + size);
    }

    private Predicate<Employee> createComplexFilter(List<SearchData> searchData, AndOrOption option) {
        SearchPredicate first = createFilterAt(searchData, 0);
        SearchPredicate second = createFilterAt(searchData, 1);

        if (AndOrOption.AND.equals(option)) {
            return first.and(second);
        } else if (AndOrOption.OR.equals(option)) {
            return first.or(second);
        }

        throw new IllegalArgumentException("not supported Options : " + option);
    }

    private SearchPredicate createFilterAt(List<SearchData> searchDataList, int index) {
        SearchData searchData = searchDataList.get(index);
        return createFilter(
                searchData.getSearchOption(),
                searchData.getInqualitySignOption(),
                searchData.getKeyValue());
    }
}
