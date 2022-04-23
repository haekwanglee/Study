package com.sec.bestreviewer.command;

import com.sec.bestreviewer.TokenGroup;
import com.sec.bestreviewer.database.Row;
import com.sec.bestreviewer.database.Table;
import com.sec.bestreviewer.database.field.FieldFactory;

import java.util.List;
import java.util.stream.IntStream;

public class ModCommand implements Command {

    private final TokenGroup tokenGroup;

    //MOD,-p, [{ , },cl,CL2}], -a, [{-m,-ge},{birthday,01}], {name,KYUMOK LEE}
//            조건1                        조건2                원하는 값
// 조건1 && 조건2 => 값을 업데이트해라
    public ModCommand(TokenGroup tokenGroup) {
        this.tokenGroup = tokenGroup;
    }

    @Override
    public List<String> execute(Table employeeDatabase) {
        SearchCommand searchCommand = new SearchCommand(tokenGroup);
        List<String> toBeUpdated = searchCommand.execute(employeeDatabase);
        String targetFiledName = tokenGroup.getTargetFieldName();
        final String targetFieldValue = tokenGroup.getTargetFieldValue();
        final int targetFieldIndex = employeeDatabase.getFieldIndexByName(targetFiledName);

        toBeUpdated.stream()
                .map(str-> str.split(","))
                .peek(strArray -> strArray[targetFieldIndex] = targetFieldValue)
                .map(Row::new)
                .forEach(employeeDatabase::update);
        return toBeUpdated;
    }
}
