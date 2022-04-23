package com.sec.bestreviewer.data;

import com.sec.bestreviewer.store.EmployeeStore;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchDataTest {

    @Test
    public void testSearchData() {
        SearchData searchData = new SearchData(SearchOption.NONE,InqualitySignOption.NONE,"employeeNum","");
        assertEquals(SearchOption.NONE,searchData.getSearchOption());
        assertEquals(InqualitySignOption.NONE,searchData.getInqualitySignOption());
        assertEquals(EmployeeStore.FIELD_EMPLOYEE_NUMBER, searchData.getKeyColumnName());
        assertEquals("", searchData.getKeyValue());
    }
}
