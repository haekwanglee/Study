package com.sec.bestreviewer.data;

import java.util.ArrayList;
import java.util.List;

public class CommandData {
    private CommandType type;
    private PrintOption printOption;
    private AndOrOption andOrOption;
    private List<SearchData> searchDataList;
    private ModifyData modifyData;
    private List<String> addDataList;

    public CommandData() {
        type = CommandType.NONE;
        printOption = PrintOption.NONE;
        andOrOption = AndOrOption.NONE;
        addDataList = new ArrayList<>();
        searchDataList = new ArrayList<SearchData>();
        modifyData = null;
    }

    public CommandType getType() {
        return type;
    }

    public void setType(CommandType type) {
        this.type = type;
    }

    public PrintOption getPrintOption() {
        return printOption;
    }

    public boolean isPrintOn() {
        return printOption == PrintOption.PRINT;
    }

    public void setPrintOption(PrintOption printOption) {
        this.printOption = printOption;
    }

    public AndOrOption getAndOrOption() {
        return andOrOption;
    }

    public void setAndOrOption(AndOrOption andOrOption) {
        this.andOrOption = andOrOption;
    }

    public ModifyData getModifyData() {
        return modifyData;
    }

    public String getModifyKeyName() {
        return modifyData.getColumnName();
    }

    public String getModifyValue() {
        return modifyData.getValue();
    }

    public void setModifyData(ModifyData modifyData) {
        this.modifyData = modifyData;
    }

    public List<String> getAddDataList() {
        return addDataList;
    }

    public void setAddDataList(List<String> addDataList) {
        this.addDataList = addDataList;
    }

    public List<SearchData> getSearchDataList() {
        return searchDataList;
    }

    public String getSearchKeyName(int index) {
        return searchDataList.get(index).getKeyColumnName();
    }

    public String getSearchValue(int index) {
        return searchDataList.get(index).getKeyValue();
    }

    public void setSearchDataList(List<SearchData> searchDataList) {
        this.searchDataList = searchDataList;
    }
}
