package com.sec.bestreviewer.database.field;

public class FieldFactory {
    public static Field create(EmployeeSchema schema) {
        String name = schema.getName();
        int index = schema.getIndex();
        FieldType fieldType = schema.getFieldType();

        return create(name, index, fieldType);
    }

    public static Field create(String name, int index, FieldType fieldType) {
        switch (fieldType) {
            case STRING:
                return new StringField(name, index);
            case CAREER_LEVEL:
                return new CareerLevelField(name, index);
            case CERTI:
                return new CertiField(name, index);
            default:
                throw new UnsupportedOperationException("Unsupported field type");
        }
    }
}
