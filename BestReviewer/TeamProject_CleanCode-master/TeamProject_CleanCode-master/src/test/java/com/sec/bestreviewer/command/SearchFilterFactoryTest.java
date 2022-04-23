package com.sec.bestreviewer.command;

import com.sec.bestreviewer.data.SearchOption;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SearchFilterFactoryTest {

    @Test
    void testGetNameFilter() {
        SearchFilterMaker command = new SearchFilterFactory().create(SearchOption.FULL_NAME);
        assertTrue(command instanceof SearchNameCommand);
    }

    @Test
    void testGetBirthdayFilter() {
        SearchFilterMaker command = new SearchFilterFactory().create(SearchOption.FULL_BIRTHDAY);
        assertTrue(command instanceof SearchBirthdayCommand);
    }

    @Test
    void testGetCertiFilter() {
        SearchFilterMaker command = new SearchFilterFactory().create(SearchOption.CERTI);
        assertTrue(command instanceof SearchCertiCommand);
    }

    @Test
    void testGetClFilter() {
        SearchFilterMaker command = new SearchFilterFactory().create(SearchOption.CL);
        assertTrue(command instanceof SearchClCommand);
    }

    @Test
    void testGetPhoneNumberFilter() {
        SearchFilterMaker command = new SearchFilterFactory().create(SearchOption.FULL_NUMBER);
        assertTrue(command instanceof SearchPhoneNumberCommand);
    }

    @Test
    void testGetEmployeeNumberFilter() {
        SearchFilterMaker command = new SearchFilterFactory().create(SearchOption.EMPLOYEE_NUMBER);
        assertTrue(command instanceof SearchEmployeeNumberCommand);
    }
}