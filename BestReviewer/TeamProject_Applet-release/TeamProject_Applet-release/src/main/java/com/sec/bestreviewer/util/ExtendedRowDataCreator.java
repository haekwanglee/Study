package com.sec.bestreviewer.util;

import com.sec.bestreviewer.database.Row;
import com.sec.bestreviewer.database.field.EmployeeSchema;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class ExtendedRowDataCreator {
    private static final String NAME_DELIMITER = " ";
    private static final String PHONE_NUM_DELIMITER = "-";
    private static final String DATE_PATTERN = "yyyyMMdd";

    private static final int FIRST_NAME_INDEX = 0;
    private static final int LAST_NAME_INDEX = 1;

    private static final int MIDDLE_NUMBER_INDEX = 1;
    private static final int LAST_NUMBER_INDEX = 2;

    public static Row create(Row row) {
        final List<String> updatedDataList = new LinkedList<>(row.getRow());

        appendNames(row, updatedDataList);
        appendPhoneNumbers(row, updatedDataList);
        appendDates(row, updatedDataList);

        return new Row(updatedDataList);
    }

    private static void appendNames(Row row, List<String> extendedList) {
        final String name = row.getValue(EmployeeSchema.NAME.getIndex());
        final String[] splitName = name.split(NAME_DELIMITER);
        extendedList.add(splitName[FIRST_NAME_INDEX]);
        extendedList.add(splitName[LAST_NAME_INDEX]);
    }

    private static void appendPhoneNumbers(Row row, List<String> updatedDataList) {
        final String phoneNumber = row.getValue(EmployeeSchema.PHONE_NUMBER.getIndex());
        final String[] splitPhoneNumber = phoneNumber.split(PHONE_NUM_DELIMITER);
        updatedDataList.add(splitPhoneNumber[MIDDLE_NUMBER_INDEX]);
        updatedDataList.add(splitPhoneNumber[LAST_NUMBER_INDEX]);
    }

    private static void appendDates(Row row, List<String> updatedDataList) {
        final String birthDay = row.getValue(EmployeeSchema.BIRTHDAY.getIndex());
        LocalDate date = LocalDate.parse(birthDay, DateTimeFormatter.ofPattern(DATE_PATTERN));
        updatedDataList.add(String.valueOf(date.getYear()));
        updatedDataList.add(getDateString(date.getMonthValue()));
        updatedDataList.add(getDateString(date.getDayOfMonth()));
    }

    private static String getDateString(int monthOrDay) {
        String date = String.valueOf(monthOrDay);
        return date.length() == 1
                ? "0" + date
                : date;
    }
}
