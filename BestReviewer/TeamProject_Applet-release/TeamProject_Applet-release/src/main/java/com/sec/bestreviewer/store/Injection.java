package com.sec.bestreviewer.store;

import com.sec.bestreviewer.database.Table;

public class Injection {

    public static Table provideEmployeeDatabase() {
        return new EmployeeDatabase();
    }
}
